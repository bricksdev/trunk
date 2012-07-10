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
 * <p>
 * 生成自定义标签，设定页面与beans的实际对应关系
 * <br/>
 * 可以为空，代表当前类的元素属性都需要转换成HTML元素
 * </p>
 * @author jhq1022749
 */
@Inherited
@Target(ElementType.TYPE)
@Retention(value= RetentionPolicy.RUNTIME)
public @interface Groups {

    Group[] groups();
}
