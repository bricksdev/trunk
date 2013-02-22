/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.bricks.dynamic.weaving.print.impl;

import cn.com.bricks.dynamic.loader.util.OnlineUnitTestState;
import cn.com.bricks.dynamic.weaving.print.IResultPrinter;
import cn.com.bricks.dynamic.weaving.xml.utils.TranslateXMLOperator;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * 默认的输出器
 *
 * @author kete
 */
public class DefaultXMLPrinter implements IResultPrinter {

    private static final ThreadLocal<Stack<Document>> _STACK_OUT = new ThreadLocal<Stack<Document>>() {
        @Override
        protected Stack<Document> initialValue() {
            Stack<Document> outStack = new Stack<Document>();

            return outStack;
        }
    };
    private static final ThreadLocal<Integer> _NODE_COUNT = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 1;
        }
    };
    private static final ThreadLocal<Map<String, OutputInfo>> _OUT_PUT = new ThreadLocal<Map<String, OutputInfo>>() {
        @Override
        protected Map<String, OutputInfo> initialValue() {
            return new HashMap<String, OutputInfo>();
        }
    };

    /**
     * <pre>
     * 输出输入输出参数信息，可以输出发生异常信息节点，及指定节点数
     * <br/>
     * 如果当前需要输出异常且指定了节点可能不能产生想要的输出
     * </pre>
     *
     * @param output
     * @param isErrorInfo true:只输出错误信息；false:输出所有节点信息
     * @param nodeNumber -1:为默认值，输出所有节点，>0输出的节点信息，超出将不产生输出
     */
    @Override
    public synchronized void output(OutputStream output, boolean isErrorInfo, int nodeNumber) {
        // 空stack不处理
        if (_STACK_OUT.get().empty()) {
            return;
        }
        try {
            Document doc = _STACK_OUT.get().pop();
            Element rootElement = TranslateXMLOperator.createElement("table", doc, new String[]{"border"}, new String[]{"1"});
            Element theader = TranslateXMLOperator.appendChildElement(rootElement, "tr");
            TranslateXMLOperator.appendChildElement(theader, "td", "Class Name");
            TranslateXMLOperator.appendChildElement(theader, "td", "Method Singure");
            TranslateXMLOperator.appendChildElement(theader, "td", "Node Number");
            TranslateXMLOperator.appendChildElement(theader, "td", "Start Time(ms)");
            TranslateXMLOperator.appendChildElement(theader, "td", "End Time(ms)");
            TranslateXMLOperator.appendChildElement(theader, "td", "Runtime(ms)");
            TranslateXMLOperator.appendChildElement(theader, "td", "Pamaters");
            TranslateXMLOperator.appendChildElement(theader, "td", "Exception");
            TranslateXMLOperator.appendChildElement(theader, "td", "Result");
            doc.appendChild(rootElement);
//            OutputInfo info = null;
            Element tContent = null;
            Element tdTable = null;
            OutputInfo[] objects = _OUT_PUT.get().values().toArray(new OutputInfo[0]);

            // 执行排序保证对应的节点
            Arrays.sort(objects, new OutputInfoComparator());
            int idx = 0;
            for (OutputInfo info : objects) {
//                info = entry.getValue();
                idx++;
                // 如果当前只输出异常节点，异常元数中不存在信息，将继续下一条处理
                if (isErrorInfo && info.getExceptionElement() == null) {
                    continue;
                }
                if (nodeNumber == -1 || (nodeNumber > -1 && nodeNumber == idx)) {
                    tContent = TranslateXMLOperator.appendChildElement(rootElement, "tr");
                    TranslateXMLOperator.appendChildElement(tContent, "td", info.getClassName());
                    TranslateXMLOperator.appendChildElement(tContent, "td", info.getMethodName());
                    TranslateXMLOperator.appendChildElement(tContent, "td", String.valueOf(info.getNodeNumber()));
                    TranslateXMLOperator.appendChildElement(tContent, "td", String.valueOf(info.getStart()));
                    TranslateXMLOperator.appendChildElement(tContent, "td", String.valueOf(info.getEnd()));
                    TranslateXMLOperator.appendChildElement(tContent, "td", String.valueOf(info.getEnd() - info.getStart()));
                    tdTable = TranslateXMLOperator.appendChildElement(tContent, "td");
                    tdTable.appendChild(info.getParamenterElement());
                    tdTable = TranslateXMLOperator.appendChildElement(tContent, "td");
                    if (info.getExceptionElement() != null) {
                        tdTable.appendChild(info.getExceptionElement());
                    }
                    tdTable = TranslateXMLOperator.appendChildElement(tContent, "td");
                    if (info.getResultElement() != null) {
                        tdTable.appendChild(info.getResultElement());
                    }
                }
            }
            TranslateXMLOperator.output(output, doc);

        } finally {
//            _OUT_PUT.clear();
        }
    }

    @Override
    public void exportParameters(String className, String methodName, List obs) {
        // 如果前一输出没有输出完成将调用异常处理
//        if (!_STACK_OUT.get().empty()) {
//            exceptions(className, methodName);
//        }

        // 判断是否为测试线程，进行在线测试的堆栈及调用参数，返回结果集的输出
        if (OnlineUnitTestState.isTestState()) {
            StringBuilder builder = new StringBuilder(className).append("\t")
                    .append(methodName);
            Element element = null;

            OutputInfo info = new OutputInfo();

            info.setStart(System.currentTimeMillis());
            info.setNodeNumber(_NODE_COUNT.get());
            _NODE_COUNT.set(_NODE_COUNT.get() + 1);
            info.setClassName(className);
            info.setMethodName(methodName);
            _OUT_PUT.get().put(builder.toString(), info);
            Document doc = null;
            if (_STACK_OUT.get().empty()) {

                // 保存对应的节点输出信息
                doc = TranslateXMLOperator.createDocument();
                // 将文档入栈
                _STACK_OUT.get().add(doc);

            } else {
                doc = _STACK_OUT.get().peek();
            }
            element = TranslateXMLOperator.createElement("table", doc, new String[]{"border"}, new String[]{"1"});
            info.setParamenterElement(element);
            TranslateXMLOperator.turnCollectionToElement(obs, element);
        }
    }

    /**
     * 输出异常信息，不输出到标准信息里
     *
     * @param ex
     */
    @Override
    public void exception(Throwable ex) {
        // TODO 不进行处理
    }

    @Override
    public void exportReturn(String className, String methodName, final Object obs) {
        // 判断是否为测试线程，进行在线测试的堆栈及调用参数，返回结果集的输出
        if (OnlineUnitTestState.isTestState()) {
            StringBuilder builder = new StringBuilder(className).append("\t")
                    .append(methodName);

            _OUT_PUT.get().get(builder.toString()).setEnd(System.currentTimeMillis());
            Document doc = _STACK_OUT.get().peek();
            Element element = TranslateXMLOperator.createElement("table", doc, new String[]{"border"}, new String[]{"1"});
            _OUT_PUT.get().get(builder.toString()).setResultElement(element);
            TranslateXMLOperator.turnCollectionToElement(new ArrayList() {
                private static final long serialVersionUID = 1L;

                {
                    add(obs);
                }
            }, element);
        }
    }

    @Override
    public void exceptions(String className, String methodName, final Throwable exception) {
        // 判断是否为测试线程，进行在线测试的堆栈及调用参数，返回结果集的输出
        if (OnlineUnitTestState.isTestState()) {
            StringBuilder builder = new StringBuilder(className).append("\t")
                    .append(methodName);

            _OUT_PUT.get().get(builder.toString()).setEnd(System.currentTimeMillis());
            _OUT_PUT.get().get(builder.toString()).setException(exception);
            Document doc = _STACK_OUT.get().peek();
            Element element = TranslateXMLOperator.createElement("table", doc, new String[]{"border"}, new String[]{"1"});
            _OUT_PUT.get().get(builder.toString()).setExceptionElement(element);
            TranslateXMLOperator.turnCollectionToElement(new ArrayList() {
                private static final long serialVersionUID = 1L;

                {
                    add(exception.getMessage());
                }
            }, element);
        }
    }
}

/**
 * 排序输出信息
 *
 * @author kete
 */
class OutputInfoComparator implements Comparator<OutputInfo> {

    @Override
    public int compare(OutputInfo o1, OutputInfo o2) {
        int n1 = o1.getNodeNumber();
        int n2 = o2.getNodeNumber();

        return n1 >= n2 ? (n1 == n2) ? 0 : 1 : -1;
    }
}

class OutputInfo implements Comparable<OutputInfo> {

    /**
     * 开始时间
     */
    private long start;
    /**
     * 结束时间
     */
    private long end;
    /**
     * 类文件
     */
    private String className;
    /**
     * 方法
     */
    private String methodName;
    /**
     * 当前参数元素
     */
    private Element parameterElement = null;
    /**
     * 当前返回结果元素
     */
    private Element resultElement = null;
    /**
     * 当前异常元素
     */
    private Element exceptionElement = null;
    /**
     * 节点处理数据
     */
    private int nodeNumber = 0;
    /**
     * 异常信息
     */
    private Throwable exception;

    /**
     * 开始时间
     *
     * @return the start
     */
    public long getStart() {
        return start;
    }

    /**
     * 开始时间
     *
     * @param start the start to set
     */
    public void setStart(long start) {
        this.start = start;
    }

    /**
     * 结束时间
     *
     * @return the end
     */
    public long getEnd() {
        return end;
    }

    /**
     * 结束时间
     *
     * @param end the end to set
     */
    public void setEnd(long end) {
        this.end = end;
    }

    /**
     * 节点处理数据
     *
     * @return the nodeNumber
     */
    public int getNodeNumber() {
        return nodeNumber;
    }

    /**
     * 节点处理数据
     *
     * @param nodeNumber the nodeNumber to set
     */
    public void setNodeNumber(int nodeNumber) {
        this.nodeNumber = nodeNumber;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("NODE:");
        builder.append(nodeNumber).append("\t");
        builder.append("START\t").append(start).append("\tEND\t").append(end).append("\t").append("\n");
        if (exception != null) {
            builder.append(exception.getCause());
            exception.printStackTrace();
        }
        return builder.toString();
    }

    /**
     * 异常信息
     *
     * @return the exception
     */
    public Throwable getException() {
        return exception;
    }

    /**
     * 异常信息
     *
     * @param exception the exception to set
     */
    public void setException(Throwable exception) {
        this.exception = exception;
    }

    /**
     * 类文件
     *
     * @return the className
     */
    public String getClassName() {
        return className;
    }

    /**
     * 类文件
     *
     * @param className the className to set
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * 方法
     *
     * @return the methodName
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * 方法
     *
     * @param methodName the methodName to set
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    /**
     * 当前元素
     *
     * @return the element
     */
    public Element getParamenterElement() {
        return parameterElement;
    }

    /**
     * 当前元素
     *
     * @param element the element to set
     */
    public void setParamenterElement(Element element) {
        this.parameterElement = element;
    }

    /**
     * 当前返回结果元素
     *
     * @return the resultElement
     */
    public Element getResultElement() {
        return resultElement;
    }

    /**
     * 当前返回结果元素
     *
     * @param resultElement the resultElement to set
     */
    public void setResultElement(Element resultElement) {
        this.resultElement = resultElement;
    }

    /**
     * 当前异常元素
     *
     * @return the exceptionElement
     */
    public Element getExceptionElement() {
        return exceptionElement;
    }

    /**
     * 当前异常元素
     *
     * @param exceptionElement the exceptionElement to set
     */
    public void setExceptionElement(Element exceptionElement) {
        this.exceptionElement = exceptionElement;
    }

    @Override
    public int compareTo(OutputInfo o) {

        return this.nodeNumber >= o.nodeNumber ? (this.nodeNumber == o.nodeNumber ? 0 : 1) : -1;
    }
}
