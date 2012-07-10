/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.annotations;

/**
 *
 * @author kete
 */
public @interface Link {

    /**
     * popup用
     * @return
     */
    String display() default "";

    /**
     * 弹出的POPUP连接表
     * @return
     */
    String url() default "";

    /**
     * 传入参数列表
     * @return
     */
    String[] parameters() default {};

    /**
     *需要设定的值
     */
    String[] settingValues() default {};
}
