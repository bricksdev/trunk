/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.template.facade;

import cn.com.annotations.parser.AnnotationParser;
import cn.com.template.factory.TemplateFactory;
import cn.com.template.form.FormStrategy;
import cn.com.wapps.permission.Permission;

/**
 * 给外界调用的门面
 *
 * @author xy
 */
public class TemplateFacade {


    /**
     * 获取Html元素
     * @param parser  注解信息
     * @return
     */
    public static String getHTML(AnnotationParser parser, Permission permission) {
        String htmlStr = "";
        //选择策略模板
        FormStrategy tempalte = TemplateFactory.getFormTemplate(parser.getForm().template());
        //通过策略模板解析得到Html
        htmlStr = tempalte.process(parser, permission);
        return htmlStr;
    }
}
