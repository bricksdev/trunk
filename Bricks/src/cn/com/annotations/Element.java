/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.annotations;

import cn.com.annotations.enums.ElementTagType;
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
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Element {

    /**
     * 同页面元素ID，为空时表示同bean属性
     * @return
     */
    String id();

    /**
     * 页面元素国际化的标签ID
     * @return
     */
    String label();

    /**
     * 是否只读
     * @return
     */
    boolean readonly() default false;

    /**
     * 类型
     * @return
     */
    ElementTagType type() default ElementTagType.TEXT;

    /**
     * 元素样式
     * @return
     */
    String cssClass() default "";

    /**
     * 长度(-1非限制)
     * @return
     */
    int maxLength() default -1;

    /**
     * 格式化
     * @return
     */
    String formate() default "";

    /**
     * 默认值
     * @return
     */
    String defaultValue() default "";

    /**
     * 元素对应链接
     * @return
     */
    Link link() default @Link;

    /**
     * 弹出框链接
     * @return
     */
    Link popupLink() default @Link;

    /**
     * 特定样式
     * @return
     */
    String cssStyle() default "";

    /**
     * 是否必须项目
     * @return
     */
    boolean required() default false;

    String dir() default "";

    String lang() default "";

    /**
     * tab索引
     * @return
     */
    String tabIndex() default "";

    String onclick() default "";

    String ondblclick() default "";

    String onmousedown() default "";

    String onmouseup() default "";

    String onmouseover() default "";

    String onmousemove() default "";

    String onmouseout() default "";

    String onkeypress() default "";

    String onkeyup() default "";

    String onkeydown() default "";

    String onfocus() default "";

    String onblur() default "";

    String onchange() default "";

    String accesskey() default "";

    boolean disabled() default false;

    String alt() default "";

    /**
     * 列表内容的设定
     * @return
     */
    SelectOption source() default @SelectOption;
}
