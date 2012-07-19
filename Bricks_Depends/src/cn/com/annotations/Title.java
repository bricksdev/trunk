/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.annotations;

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
public @interface Title {

    static final String _DEFAULT_TITLE = "DefaultWeb";

    class DEFAULT implements Title {

        private String title = _DEFAULT_TITLE;

        public DEFAULT() {
        }

        public DEFAULT(String title) {
            this.title = title;
        }

        @Override
        public String title() {
            return title;
        }

        @Override
        public Class<? extends Annotation> annotationType() {
            return Title.class;
        }
    };

    String title() default _DEFAULT_TITLE;
}
