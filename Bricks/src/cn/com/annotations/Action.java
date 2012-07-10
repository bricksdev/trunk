/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.annotations;

import cn.com.annotations.enums.SubmitType;
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
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Action {
    final static String _DEFAULT_VALUE = "";

    /**
     *
     * @return
     */
    String id() default _DEFAULT_VALUE;

    /**
     * 页面元素国际化的标签ID
     * @return
     */
    String label() default _DEFAULT_VALUE;

    /**
     * 点击事件
     * @return
     */
    String onclick() default _DEFAULT_VALUE;

    SubmitType submitType() default SubmitType.SUBMIT;
}
