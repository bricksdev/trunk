/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.annotations;

import cn.com.annotations.enums.ElementEnum;
import cn.com.annotations.iconst.AnnotationsConst;
import cn.com.national.BricksNationality;
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
    String label() default AnnotationsConst._DEFAULT_BLANK;

    /**
     * 是否只读
     * @return
     */
    boolean readonly() default false;

    /**
     * 类型
     * @return
     */
    ElementEnum type() default ElementEnum.TEXT;

//    /**
//     * 元素组内容，设定元素的定位
//     * @return
//     */
//    String groups() default AnnotationsConst._DEFAULT_BLANK;

    /**
     * 元素样式
     * @return
     */
    String cssClass() default AnnotationsConst._DEFAULT_BLANK;

    /**
     * 长度(-1非限制)
     * @return
     */
    int maxLength() default -1;

    /**
     * 格式化
     * @return
     */
    Formatter format() default @Formatter;

    /**
     * 默认值
     * @return
     */
    String defaultValue() default AnnotationsConst._DEFAULT_BLANK;

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
    String cssStyle() default AnnotationsConst._DEFAULT_BLANK;

    /**
     * 是否必须项目
     * @return
     */
    boolean required() default false;

    String dir() default AnnotationsConst._DEFAULT_BLANK;

    String lang() default AnnotationsConst._DEFAULT_BLANK;

    /**
     * tab索引
     * @return
     */
    String tabIndex() default AnnotationsConst._DEFAULT_BLANK;

    String onclick() default AnnotationsConst._DEFAULT_BLANK;

    String ondblclick() default AnnotationsConst._DEFAULT_BLANK;

    String onmousedown() default AnnotationsConst._DEFAULT_BLANK;

    String onmouseup() default AnnotationsConst._DEFAULT_BLANK;

    String onmouseover() default AnnotationsConst._DEFAULT_BLANK;

    String onmousemove() default AnnotationsConst._DEFAULT_BLANK;

    String onmouseout() default AnnotationsConst._DEFAULT_BLANK;

    String onkeypress() default AnnotationsConst._DEFAULT_BLANK;

    String onkeyup() default AnnotationsConst._DEFAULT_BLANK;

    String onkeydown() default AnnotationsConst._DEFAULT_BLANK;

    String onfocus() default AnnotationsConst._DEFAULT_BLANK;

    String onblur() default AnnotationsConst._DEFAULT_BLANK;

    String onchange() default AnnotationsConst._DEFAULT_BLANK;

    String accesskey() default AnnotationsConst._DEFAULT_BLANK;

    boolean disabled() default false;

    String alt() default AnnotationsConst._DEFAULT_BLANK;

    /**
     * 元素的长度
     * @return
     */
    String length() default AnnotationsConst._DEFAULT_BLANK;

    /**
     * 列表内容的设定
     * @return
     */
    Option source() default @Option;

    /**
     * 获取国际化的标签信息
     */
    class LabelContent {

        public static String getLabelContent(String label) {
            return BricksNationality.getValue(label);
        }
    }
}
