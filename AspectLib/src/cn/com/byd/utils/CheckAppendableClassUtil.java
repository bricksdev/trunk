package cn.com.byd.utils;

import java.util.Collection;
import java.util.Map;

public enum CheckAppendableClassUtil {
    INSTANCE;

    /**
     *判定当前类型是否为集合类型
     * @param clazz
     * @return
     */
    public boolean isCollection(Class clazz) {
        Class[] classes = clazz.getInterfaces();
        boolean isCollection = false;
        for (Class clz : classes) {
            if (clz == Collection.class) {
                isCollection = true;
                break;
            }
        }
        return isCollection;
    }

    /**
     *判定是否为映射类型
     * @param clazz
     * @return
     */
    public boolean isMap(Class clazz) {
        Class[] classes = clazz.getInterfaces();
        boolean isCollection = false;
        for (Class clz : classes) {
            if (clz == Map.class) {
                isCollection = true;
                break;
            }
        }
        return isCollection;
    }

}
