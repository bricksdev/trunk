/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author kete
 */
public class InstanceCache {

    /**
     * 类缓存
     */
    private final static Map<Class<?>, Object> _CACHE = new ConcurrentHashMap<Class<?>, Object>(15, 1.0f);

    /**
     * 保存
     * @param iservice
     * @param instance
     */
    public synchronized static void put(Class<?> iservice, Object instance) {

        if(instance == null){
            return;
        }

        _CACHE.put(iservice, instance);
    }

    /**
     * 获取实类对象
     * @param iservice
     * @return
     */
    public synchronized static Object getInstace(Class<?> iservice) {

        return _CACHE.get(iservice);

    }
}
