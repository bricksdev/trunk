/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.annotations;

import cn.com.annotations.enums.FormatType;
import cn.com.annotations.iconst.AnnotationsConst;

/**
 *
 * @author kete
 */
public @interface Formatter {


    /**
     * 格式化类型
     * @return
     */
    FormatType type() default FormatType.NORMAL;

    /**
     * 格式化格式
     * @return
     */
    String patten() default AnnotationsConst._DEFAULT_BLANK;
}
