/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.cache;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * 第三方缓存管理类
 * <br/>
 * 负责管理自动刷新缓存的处理
 *
 * @author kete
 */
public enum BricksCacheManager {

    INSTANCE;

    private BricksCacheManager() {
        // 缓存文件配置
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("data/CacheConfig.xml");
        if (is != null) {
            cacheManager = CacheManager.create(is);
        }
        // 如果没有配置文件，将获取默认的配置处理
        if (cacheManager == null) {
            cacheManager = CacheManager.getInstance();
        }
    }
    private CacheManager cacheManager = null;

    /**
     * 创建指定名快速缓存，设定默认的参数，不自动释放内存
     *
     * @param cacheName
     *
     * @return
     */
    private Cache addCache(String cacheName) {

        Cache cache = null;
        // 如果存在将返回已存在的缓存
        if (cacheManager.cacheExists(cacheName)) {
            cache = cacheManager.getCache(cacheName);
        } else {
            // 设定默认的系统缓存配置
            cache = new Cache(cacheName, 1000, true, true, 10 * 60 * 1000L, 30 * 60 * 1000L);
            cacheManager.addCache(cache);
        }

        return cache;
    }

    /**
     * 向缓存中追加缓存元素
     *
     * @param cacheName
     * param key
     * param value
     */
    public void addCacheElement(String cacheName, Object key, Object value) {
        this.addCache(cacheName).put(new Element(key, value));
    }
    
    /**
     * 在缓存中删除元素
     * @param cacheName
     * @param key 
     */
    public void removeCacheElement(String cacheName,String key){
        if(this.containsKeyInCache(cacheName, key)){
            cacheManager.getCache(cacheName).remove(key);
        }
    }

    /**
     * 是否存在缓存
     *
     * @param cacheName
     *
     * @return
     */
    public boolean existsCache(String cacheName) {
        return cacheManager.cacheExists(cacheName);
    }

    /**
     * 判断是否当前key存在缓存中
     * @param cacheName
     * @param key
     * @return
     */
    public boolean containsKeyInCache(String cacheName, String key) {
        return cacheManager.cacheExists(cacheName) ? cacheManager.getCache(cacheName).isKeyInCache(key) : false;
    }

    /**
     * 获取指定缓存名称的健列表
     *
     * @param cacheName
     *
     * @return
     */
    public List getKeys(String cacheName) {
        return cacheManager.getCache(cacheName).getKeys();
    }

    /**
     * 获取指定缓存名及指定健的缓存内容
     *
     * @param cacheName
     * param key 键
     *
     * @return
     */
    public Object getValue(String cacheName, Object key) {
        return cacheManager.getCache(cacheName).get(key).getObjectValue();
    }

    /**
     * 获取当前缓存中的所有的值
     * @param cacheName
     * @return
     */
    public List getValues(String cacheName) {
        List keys = cacheManager.getCache(cacheName).getKeys();
        List values = new ArrayList(keys.size());

        for (Object object : keys) {
            values.add(getValue(cacheName, object));
        }

        return values;
    }

    /**
     * 移除指定缓存
     *
     * @param cacheName
     */
    public void removeCache(String cacheName) {
        cacheManager.removeCache(cacheName);
    }
}
