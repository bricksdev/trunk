/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.template.form;

import cn.com.annotations.parser.AnnotationParser;
import cn.com.wapps.permission.Permission;

/**
 *Bean策略接口   将注解信息解析成HTML元素
 * @author xy
 */
public interface FormStrategy {

    /**
     * @param parser 注解信息
     * @return
     */
    public String process(AnnotationParser parser, Permission permission);
}
