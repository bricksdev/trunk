/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.annotations;

import cn.com.annotations.enums.SubmitType;
import cn.com.annotations.iconst.AnnotationsConst;
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

    /**
     *
     * @return
     */
    String id() default AnnotationsConst._DEFAULT_BLANK;

    /**
     * 页面元素国际化的标签ID
     * @return
     */
    String label() default AnnotationsConst._DEFAULT_BLANK;

    /**
     * 点击事件
     * @return
     */
    String onclick() default AnnotationsConst._DEFAULT_BLANK;

    SubmitType submitType() default SubmitType.SUBMIT;
}
