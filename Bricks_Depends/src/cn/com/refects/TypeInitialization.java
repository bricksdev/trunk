/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.refects;

import cn.com.utils.DataProviderConfig;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kete
 */
public class TypeInitialization {

    /**
     * 通用类型初始化
     */
    private static final Map<Class<?>, Object> COMMON_OBJECT = Collections.unmodifiableMap(new HashMap<Class<?>, Object>() {

        {
            put(Integer.TYPE, 1);
            put(Long.TYPE, 1);
            put(Double.TYPE, 1);
            put(Short.TYPE, 1);
            put(Character.TYPE, 'A');
            put(Float.TYPE, 1);
            put(char.class, 'A');
            put(int.class, 1);
            put(long.class, 1);
            put(double.class, 1);
            put(float.class, 1);
            put(String.class, "test data");
            put(BigDecimal.class, BigDecimal.TEN);
            put(Date.class, new Date());

        }
    });


    /**
     * 判断是否通用数据类型
     * @param clazz
     * @return
     */
    public static boolean isCommonType(Class<?> clazz) {
        return COMMON_OBJECT.containsKey(clazz);
    }

    /**
     * 初始化通用数据类型
     * @param clazz
     * @return
     */
    public static Object initializeCommonType(Class<?> clazz) {

        return COMMON_OBJECT.get(clazz);
    }

    /**
     * 初始化泛型类型列表
     * @param type
     * @return
     */
    public static List<Object> initializeGenericList(Type type) throws ClassNotFoundException {
        Class tType = TypeContentRefecter.getGenericClass(type);
        return initializeList(tType);
    }

    /**
     * 初始化普通类
     * @param clazz
     * @return
     */
    public static Object initializeObject(Class<?> clazz) throws ClassNotFoundException {

        List<Field> fields = TypeContentRefecter.getAllFields(clazz);

        Object value = InstanceCreator.getInstance(clazz.getName());

        // 如果为空将不进行处理
        if (value == null) {
            return null;
        }
        // 封装数据
        for (Field f : fields) {
            if (isCommonType(f.getType())) {
                TypeContentRefecter.setValue(value, f.getName(), new Class[]{f.getType()}, initializeCommonType(f.getType()));
            } else {

                TypeContentRefecter.setValue(value, f.getName(), new Class[]{f.getType()}, invoke(f));
            }
        }

        return value;
    }

    /**
     * 模拟返回的数据
     */
    private static Object invoke(Field method) throws ClassNotFoundException {


        // 处理数组类型初始化
        if (method.getType().isArray()) {
            return TypeInitialization.initializeList(method.getType().getComponentType()).toArray();
        }

        // 处理列表类初始化
        if ((method.getType().isInterface() && method.getType().equals(List.class))) {
            Class genericClass = TypeContentRefecter.getGenericClass(method.getGenericType());
            // 防止内存溢出，对于自身引用的对象，将不进行深度的数据模拟
            if(genericClass.equals(method.getDeclaringClass())){
                return null;
            }

            return TypeInitialization.initializeList(genericClass);
        }

        // 验证可处理的返回类型初始化
        boolean isMaybe = true;
        // 不处理映射类型初始化
        if (method.getType().isInterface() && method.getType().equals(Map.class)) {
            isMaybe = false;
        }

        // 处理一般类型初始化
        if (!method.getType().isInterface()) {
            return TypeInitialization.initializeObject(method.getType());
        }

        if (!isMaybe) {
            throw new RuntimeException("don't operat field type :" + method.getType().getName());
        }
        return null;
    }

    /**
     * 初始化列表数据类型
     * @param clazz
     * @return
     */
    public static List<Object> initializeList(Class<?> clazz) throws ClassNotFoundException {
        List<Object> values = new ArrayList<Object>();

        for (int i = 0; i < DataProviderConfig.INSTANCE.getCount(); i++) {
            if (isCommonType(clazz)) {
                values.add(COMMON_OBJECT.get(clazz));
            } else {
                values.add(initializeObject(clazz));
            }
        }

        return values;
    }
}
