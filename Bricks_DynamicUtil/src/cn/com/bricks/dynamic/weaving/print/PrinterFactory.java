/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.bricks.dynamic.weaving.print;

import cn.com.bricks.dynamic.weaving.print.impl.DefaultXMLPrinter;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kete
 */
public abstract class PrinterFactory {

    /**
     * 设定默认的输出器
     */
    private static IResultPrinter _PRINTER = new DefaultXMLPrinter();

    /**
     * 注册输出器
     *
     * @param printer
     */
    public static void registerPrint(IResultPrinter printer) {
        _PRINTER = printer;
    }

    /**
     * 输出到默认的系统流中
     */
    public static void output() {
        output(System.out, false, -1);
    }

    /**
     * 输出结果
     *
     * @param errors 是否仅输出异常节点 true 为输出异常节点
     * @return
     */
    public static String outputString(boolean errors) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        _PRINTER.output(out, errors, -1);
        return out.toString();
    }

    /**
     * 输出结果
     *
     * @param node 输出对应的节点内容
     * @return
     */
    public static String outputString(int node) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        _PRINTER.output(out, false, node);
        return out.toString();
    }

    /**
     * 输出结果
     *
     * @return
     */
    public static String outputString() {
        return outputString(-1);
    }

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
    public static void output(OutputStream output, boolean isErrorInfo, int nodeNumber) {
        _PRINTER.output(output, isErrorInfo, nodeNumber);
    }

    /**
     * 导出当前方法参数，供无参调用
     *
     * @param className
     * @param methodName
     */
    public static void exportParameters(String className, String methodName) {
        exportParameters(className, methodName, new ArrayList() {
            private static final long serialVersionUID = 1L;

            {
                add("No Arguements");
            }
        });
    }

    /**
     * 导出当前方法参数，供有参调用
     *
     * @param className
     * @param methodName
     * @param obs
     */
    public static void exportParameters(String className, String methodName, List obs) {
        _PRINTER.exportParameters(className, methodName, obs);
    }

    /**
     * 导出当前方法,参数，返回值,供返回void调用
     *
     * @param className
     * @param methodName
     */
    public static void exportReturn(String className, String methodName) {
        exportReturn(className, methodName, "No Return");
    }

    /**
     * 导出当前方法,参数,返回值，供存在返回值调用
     *
     * @param className
     * @param methodName
     * @param obs
     */
    public static void exportReturn(String className, String methodName, final Object obs) {
        _PRINTER.exportReturn(className, methodName, obs);
    }

    /**
     * 输出异常信息
     *
     * @param ex
     */
    public static void exception(Throwable ex) {
        _PRINTER.exception(ex);
    }

    /**
     * 输出异常信息
     *
     * @param className
     * @param methodName
     * @param exception
     */
    public static void exceptions(String className, String methodName, final Throwable exception) {
        _PRINTER.exceptions(className, methodName, exception);
    }
}
