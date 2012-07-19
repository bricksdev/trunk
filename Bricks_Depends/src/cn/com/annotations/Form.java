/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.annotations;

import cn.com.annotations.enums.FormMethodModel;
import cn.com.annotations.iconst.AnnotationsConst;
import java.lang.annotation.Annotation;
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

    abstract class DEFAULT implements Form {

        @Override
        public String id() {
            return __DEFAULT_FORM_NAME;
        }

        @Override
        public String cssClass() {
            return AnnotationsConst._DEFAULT_BLANK;
        }

        @Override
        public String enctype() {
            return __DEFAULT_ENCTYPE;
        }

        @Override
        public FormMethodModel method() {
            return FormMethodModel.POST;
        }

        @Override
        public Class<? extends Annotation> annotationType() {
            return Form.class;
        }

        @Override
        public String label() {
            return AnnotationsConst._DEFAULT_BLANK;
        }

        @Override
        public Action[] actions() {
            return new Action[]{};
        }
    }


    String id() default __DEFAULT_FORM_NAME;

    String cssClass() default AnnotationsConst._DEFAULT_BLANK;

    String action() default AnnotationsConst._DEFAULT_BLANK;

    /**
     * 文件上传用，multipart/form-data
     * @return
     */
    String enctype() default __DEFAULT_ENCTYPE;

    FormMethodModel method() default FormMethodModel.POST;

    String label() default AnnotationsConst._DEFAULT_BLANK;

    /**
     * 事件
     * @return
     */
    Action[] actions() default {};
}
