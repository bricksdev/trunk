/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.utils;

import cn.com.refects.TypeContentRefecter;
import cn.com.refects.TypeInitialization;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * json转换类，将bean的属性作成分级处理的机制
 * @author kete
 */
public class JSONObjectUtils {

    /**
     * 封装JSON格式,无前缀
     * @param obj
     * @return
     */
    public static String wrapperJSON(Object obj) {
        return wrapperJSON(obj, null);
    }

    /**
     * 封装JSON格式,属性带前缀key=prefix.(obj.field | map.key)
     * @param obj
     * @param prefix
     * @return
     */
    public static String wrapperJSON(Object obj, String prefix) {

        // 返回空字符
        if (obj == null) {
            return "{}";
        }

        // 转换指定对象
        if (obj instanceof Map) {
            return wrapperMap((Map) obj, prefix);
        } else if (obj instanceof List) {
            return wrapperList((List) obj, prefix);
        } else if (obj instanceof Object[]) {
            return wrapperArrays((Object[]) obj, prefix);
        }

        // 开始转换对象
        StringBuilder builder = new StringBuilder("{");

        List<Field> fields = TypeContentRefecter.getAllFields(obj.getClass());
        int idx = 0;
        for (Field f : fields) {
            if (idx++ > 0) {
                builder.append(",");
            }
            builder.append("\"");
            builder.append(StringUtil.joinString(".", prefix, f.getName())).append("\"").append(":");
            Object value = TypeContentRefecter.invokeValue(obj, f.getName());
            // 操作对象转换
            operatFromObject(value, builder, StringUtil.joinString(".", prefix, f.getName()));

        }

        return builder.append("}").toString();
    }

    private static void operatFromObject(Object value, StringBuilder builder, String prefix) {

        if (value == null) {
            builder.append("\"");
            builder.append("");
            builder.append("\"");
        } else if (TypeInitialization.isCommonType(value.getClass())) {
            builder.append("\"");
            // 替换“，‘引号导致json转换失败
            builder.append(String.valueOf(value).replaceAll("\"", "\\\\\\\\\"").replaceAll("'", "\\\\'"));
            builder.append("\"");
        } else if (value instanceof List) {
            builder.append(wrapperList((List) value, prefix));
        } else if (value.getClass().isArray()) {
            builder.append(wrapperArrays((Object[]) value, prefix));
        } else if (value instanceof Map) {
            builder.append(wrapperMap((Map) value, prefix));
        } else {
            builder.append(wrapperJSON(value, prefix));
        }

    }

    private static String wrapperMap(Map<Object, Object> entry, String prefix) {
        StringBuilder builder = new StringBuilder("{");
        if (entry != null && !entry.isEmpty()) {
            int idx = 0;
            for (Map.Entry e : entry.entrySet()) {
                if (idx++ > 0) {
                    builder.append(",");
                }
                operatFromObject(e.getValue(), builder, StringUtil.joinString(".", prefix, e.getKey().toString()));
            }
        }

        return builder.append("}").toString();
    }

    private static String wrapperList(List array, String prefix) {
        StringBuilder builder = new StringBuilder("[");

        if (array != null && !array.isEmpty()) {
            int idx = 0;
            for (Object obj : array) {
                if (idx++ > 0) {
                    builder.append(",");
                }
                operatFromObject(obj, builder, prefix);
            }
        }

        return builder.append("]").toString();
    }

    private static String wrapperArrays(Object[] array, String prefix) {
        StringBuilder builder = new StringBuilder("[");

        if (array != null && array.length > 0) {
            int idx = 0;
            for (Object obj : array) {
                if (idx++ > 0) {
                    builder.append(",");
                }
                operatFromObject(obj, builder, prefix);
            }
        }
        return builder.append("]").toString();
    }
}
