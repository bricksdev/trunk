/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.refects.utils;

import cn.com.codes.BricksMessagesCodes;
import cn.com.exceptions.AppException;
import cn.com.refects.TypeContentRefecter;
import java.lang.reflect.Field;
import java.util.List;

/**
 *
 * @author kete
 */
public abstract class BeanUtils {

    /**
     * 属性内容复制
     * @param dist NOT NULL
     * @param source NOT NULL
     * @throws AppException
     */
    public static void copyField(Object dist, Object source) throws AppException {

        // 目标不能为空
        if (dist == null) {
            throw new AppException(BricksMessagesCodes._E00014);
        }
        // 源不能为空
        if (source == null) {
            throw new AppException(BricksMessagesCodes._E00013);
        }

        List<Field> distFields = TypeContentRefecter.getAllFields(dist.getClass());
        if (distFields != null) {
            Object value = null;
            for (Field f : distFields) {
                value = TypeContentRefecter.invokeValue(source, f.getName());
                TypeContentRefecter.setValue(dist, f.getName(), new Class[]{f.getType()}, value);
            }
        }
    }
}
