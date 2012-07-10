/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.annotations;

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
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Grid {
    final static String __TABLE_ID = "table1";


    String id() default __TABLE_ID;

    /**
     * 表格数据包装类
     * @return
     */
    Class contextClass();

    /**
     * 是否可选择
     * @return
     */
    boolean selectabled() default true;

    /**
     * 是否多项选择
     * @return
     */
    boolean isMultiple() default true;

    /**
     * 是否需要追加按钮
     * @return
     */
    boolean addabled() default false;

    /**
     * 是否需要删除按钮
     * @return
     */
    boolean deletabled() default false;

    boolean copyabled() default false;

    Element[] columns();

    Action[] actions() default @Action;
}
