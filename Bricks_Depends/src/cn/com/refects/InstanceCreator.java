/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.refects;

import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author kete
 */
public class InstanceCreator {

    private static final String CLASS_NAME_IMPL = "Impl";
    private static final String PACKAGE_IMPL = ".impl.";

    /**
     * 实类化,取默认的实现包名，及默认的实现类名
     * 默认包名：clazz.getPackage()+".impl"
     * 默认的类名：clazz.getSimpleName()+"Impl"
     * @param clazz
     * @return
     */
    public static Object instance(Class<?> clazz) {

        return instance(clazz, PACKAGE_IMPL, CLASS_NAME_IMPL, null);

    }

    /**
     * 获取类默认构造函数的实体对象
     * @param className
     * @return
     */
    public static Object getInstance(String className) throws ClassNotFoundException {

        Class<?> clazz = Class.forName(className);

        return instance(clazz, ".", "", null);
    }

    /**
     * 实类化,取默认的实现包名，及默认的实现类名
     * 默认包名：clazz.getPackage()+".impl"
     * 默认的类名：clazz.getSimpleName()+"Impl"
     * @param clazz
     * @return
     */
    public static Object instance(Class<?> clazz, Object[] parameters) {

        return instance(clazz, PACKAGE_IMPL, CLASS_NAME_IMPL, parameters);
    }

    /**
     * 实类化,取指定的实现包名，及指定的实现类名
     * @param clazz
     * @param packageSuffix
     * @param nameSuffix
     * @return
     */
    public static Object instance(Class<?> clazz, String packageSuffix, String nameSuffix, Object[] parameters) {

        String className = new StringBuffer().append(clazz.getPackage().getName()).append(packageSuffix).append(clazz.getSimpleName()).append(nameSuffix).toString();
        try {
            Class implClazz = Class.forName(className);

            // 构造函数的参数
            Class[] parameterTypes = null;
            if (parameters != null) {
                parameterTypes = new Class[parameters.length];
                int idx = 0;
                for (Object obj : parameters) {
                    parameterTypes[idx++] = obj.getClass();
                }
            }

//            if (parameterTypes == null) {
//                return implClazz.newInstance();
//            }

            return implClazz.getConstructor(parameterTypes).newInstance(parameters);

        } catch (InstantiationException ex) {
//            Logger.getLogger(InstanceCreator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
//            Logger.getLogger(InstanceCreator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
//            Logger.getLogger(InstanceCreator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
//            Logger.getLogger(InstanceCreator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
//            Logger.getLogger(InstanceCreator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
//            Logger.getLogger(InstanceCreator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(InstanceCreator.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
