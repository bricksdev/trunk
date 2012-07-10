/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.annotations;

import cn.com.annotations.enums.FormMethodModel;
import cn.com.annotations.enums.FormTemplateType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author kete
 */
@Inherited
@Target(ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Form {

    final static String __DEFAULT_FORM_NAME = "__form1";
    final static String __DEFAULT_ENCTYPE = "application/x-www-form-urlencoded";
    final static String __DEFAULT_TITLE = "WMS";
    final static String __DEFAULT_SUFFIX = ".jsp";
    final static String __DEFAULT_CSS = "";
    final static String __DEFAULT_ACTION = "";

    String id() default __DEFAULT_FORM_NAME;

    String cssClass() default __DEFAULT_CSS;

    String action() default __DEFAULT_ACTION;

    FormTemplateType template() default FormTemplateType.WMS;

    /**
     * 文件上传用，multipart/form-data
     * @return
     */
    String enctype() default __DEFAULT_ENCTYPE;

    FormMethodModel method() default FormMethodModel.POST;

    /**
     * 页面title
     * @return
     */
    String pageTitle() default __DEFAULT_TITLE;

    String label() default __DEFAULT_ACTION;
}
