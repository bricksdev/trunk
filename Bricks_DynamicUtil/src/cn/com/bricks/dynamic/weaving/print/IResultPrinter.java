/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.bricks.dynamic.weaving.print;

import java.io.OutputStream;
import java.util.List;

/**
 * 输出test结果类
 *
 * @author kete
 */
public interface IResultPrinter {

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
    void output(OutputStream output, boolean isErrorInfo, int nodeNumber);

    /**
     * 导出当前方法参数，供有参调用
     *
     * @param className
     * @param methodName
     * @param obs
     */
    void exportParameters(String className, String methodName, List obs);

    /**
     * 输出异常信息
     *
     * @param ex
     */
    void exception(Throwable ex);

    /**
     * 导出当前方法,参数,返回值，供存在返回值调用
     *
     * @param className
     * @param methodName
     * @param obs
     */
    void exportReturn(String className, String methodName, final Object obs);

    /**
     * 输出异常信息
     *
     * @param className
     * @param methodName
     * @param exception
     */
    void exceptions(String className, String methodName, final Throwable exception);
}
