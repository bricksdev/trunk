/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.annotations;

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
public @interface Template {
    static final String _DEFAULT_TEMPLATE = AnnotationsConst._DEFAULT_BLANK;

    class DEFAULT implements Template {

        private String template = AnnotationsConst._DEFAULT_BLANK;

        public DEFAULT() {
        }

        public DEFAULT(String template) {
            this.template = template;

        }

        @Override
        public Class<? extends Annotation> annotationType() {
            return Template.class;
        }

        @Override
        public String template() {
            return this.template;
        }
    }

    String template() default _DEFAULT_TEMPLATE;
}
