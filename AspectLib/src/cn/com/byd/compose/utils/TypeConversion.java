package cn.com.byd.compose.utils;

import cn.com.byd.utils.RefObjectUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import java.util.Map;

public abstract class TypeConversion {

    private final static String VALUE_OF = "valueOf";

    /**
     *获取参数转换后的实体对象
     * @param targetName
     * @param values
     * @return
     */
    public static Object converCreateBean(String targetName, Map<String, Object> values) {
        Object instance = RefObjectUtil.loadClass(targetName);
        setFieldValue(instance, values);
        return instance;
    }

    private static void setFieldValue(Object obj, Map<String, Object> values) {
        if (values == null || values.isEmpty()) {
            return;
        }
        Field field = null;
        for (String fieldName : values.keySet()) {
            field = RefObjectUtil.findField(obj.getClass(), fieldName);
            RefObjectUtil.makeAccessible(field);
            RefObjectUtil.setField(field, obj, values.get(fieldName));
        }
    }

    public static Object converObject(Class classType, Object value) {
        Method method = RefObjectUtil.findMethod(classType, VALUE_OF, new Class[]{Object.class});
        return method == null ? value : RefObjectUtil.invokeMethod(method, RefObjectUtil.newInstance(classType), new Object[]{value});
    }
}
