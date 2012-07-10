/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * <p>
 * 生成自定义标签，设定页面与beans的实际对应关系
 * </p>
 * @author jhq1022749
 */
@Target(ElementType.TYPE)
public @interface Group {

    final static String __DEFAULT_GROUP_NAME = "__default";

    String name() default __DEFAULT_GROUP_NAME;

    Element[] elements() default {};

    Grid[] grids() default {};

    Action[] actions() default {};

    Form form() default @Form(action=Form.__DEFAULT_ACTION);
}
