/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.refects;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kete
 */
public class TypeContentGetter {

    private static final String _GET = "get";
    private static final String _SET = "set";

    /**
     * 获取对象的值
     * @param obj
     * @param fieldName
     * @return
     */
    public static Object invokeValue(Object obj, String fieldName) {
        return invoke(obj, _GET + upperFirstChar(fieldName), null, null);
    }

    /**
     * 设定对象的值
     * @param obj
     * @param fieldName
     * @param args 方法参数
     * @return
     */
    public static void setValue(Object obj, String fieldName, Class[] paramterTypes, Object... args) {
        invoke(obj, _SET + upperFirstChar(fieldName), args, paramterTypes);
    }

    /**
     * 反射方法调用
     * @param obj
     * @param methodName
     * @param args
     */
    private static Object invoke(Object obj, String methodName, Object[] args, Class[] paramterTypes) {
        Object value = null;
        try {
            // 方法反射
            Method method = obj.getClass().getMethod(methodName, paramterTypes);
            value = method.invoke(obj, args);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(TypeContentGetter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(TypeContentGetter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TypeContentGetter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(TypeContentGetter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(TypeContentGetter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return value;
    }

    /**
     * 首字母大写
     * @param value
     * @return
     */
    private static String upperFirstChar(String value) {
        return value.substring(0, 1).toUpperCase() + value.substring(1);
    }

    /**
     * 获取当前类下所有的属性，包含继承属性
     * @param curClass
     * @return
     */
    public static List<Field> getAllFields(Class curClass) {
        List<Field> fields = new ArrayList<Field>(15);
        fields.addAll(Arrays.asList(curClass.getDeclaredFields()));
        Class superClass = curClass.getSuperclass();
        if (!superClass.equals(Object.class)) {
            fields.addAll(getAllFields(superClass));
        }
        return fields;

    }

    /**
     * 得到当前类下所有公共方法，包括继承
     * @param curClass
     * @return
     */
    public static List<Method> getAllMethods(Class curClass) {
        List<Method> methods = new ArrayList<Method>(5);
        methods.addAll(Arrays.asList(curClass.getMethods()));
        Class superClass = curClass.getSuperclass();
        if (!superClass.equals(Object.class)) {
            methods.addAll(getAllMethods(superClass));
        }
        return methods;
    }
}
