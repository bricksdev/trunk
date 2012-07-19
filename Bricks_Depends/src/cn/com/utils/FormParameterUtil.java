/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.utils;

import cn.com.convert.BricksConverter;
import cn.com.exceptions.AppException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kete
 */
public abstract class FormParameterUtil {

    /**
     * 获取参数中对象
     * @param key
     * @param parameters
     * @return
     */
    public static Object getObject(String key, Map parameters) {
        Object[] obj = (Object[]) parameters.get(key);
        if (obj == null) {
            return null;
        } else if (obj.length >= 1) {
            return obj[0];
        } else {
            return null;
        }
    }

    /**
     * 获取参数中字符
     * @param key
     * @param parameters
     * @return
     */
    public static String getString(String key, Map parameters) {
        String[] obj = (String[]) parameters.get(key);
        if (obj == null) {
            return null;
        } else if (obj.length >= 1) {
            return obj[0];
        } else {
            return null;
        }
    }

    /**
     * 获取参数中的字符串
     * @param key
     * @param parameters
     * @return
     */
    public static Object getParam(String key, Class<?> type, Map parameters, int idx, String partten) throws AppException {

        Object obj = parameters.get(key);
        if (obj == null) {
            return null;
        } else if (obj instanceof String[]) {
            String[] strs = (String[]) obj;
            if (strs.length >= idx) {
                return BricksConverter.getInstance().convertStringToObject(strs[idx], type, partten);
            } else {
                return null;
            }
        } else {
            return obj.toString();
        }
    }

    /**
     * 获取参数中的字符串
     * @param key
     * @param parameters
     * @return
     */
    public static List getParamList(String key, Class<?> type, Map parameters) {

        return null;
    }

    /**
     * 获取参数中的字符串
     * @param key
     * @param parameters
     * @return
     */
    public static Object[] getParamArray(String key, Class<?> type, Map parameters, String partten) throws AppException {
        Object obj = parameters.get(key);
        Object[] objs = null;
        if (obj == null) {
            return null;
        } else if (obj instanceof String[]) {
            String[] strs = (String[]) obj;
            objs = new Object[strs.length];
            int idx = 0;
            for (String c : strs) {
                objs[idx++] = BricksConverter.getInstance().convertStringToObject(c, type, partten);
            }
        }
        return objs;
    }
}
