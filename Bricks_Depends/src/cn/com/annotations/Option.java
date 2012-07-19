/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.annotations;

import cn.com.annotations.iconst.AnnotationsConst;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 列表生成数据字典扩展处理项
 * @author kete
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Option {

    static final String _DEFAULT_DISPLAY = " ";

    String typeCode() default AnnotationsConst._DEFAULT_BLANK;

    String partitionCode() default AnnotationsConst._DEFAULT_BLANK;

    String[] value() default {AnnotationsConst._DEFAULT_BLANK};

    String[] display() default {_DEFAULT_DISPLAY};
}
