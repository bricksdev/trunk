/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.bricks.dynamic.loader.util;

import cn.com.bricks.custom.loader.DynamicContentClassLoader;

/**
 *
 * @author kete
 */
public class LoaderUtil {

    private volatile static ClassLoader classloader = new DynamicContentClassLoader(Thread.currentThread().getContextClassLoader());

    /**
     * 返回当前线程loader
     *
     * @return
     */
    public static synchronized ClassLoader getClassLoader() {
        if (DynamicDeployUtil.isReload()) {
            classloader = new DynamicContentClassLoader(Thread.currentThread().getContextClassLoader());
            // 重置重载标记
            DynamicDeployUtil.setLoaderFlag(false);
        }
        setCurrentClassLoader();
        return classloader;
    }

    /**
     * 获取当前loader的父loader
     *
     * @return
     */
    public static synchronized ClassLoader getCurrentLoaderParentLoader() {
        ClassLoader currentLoader = Thread.currentThread().getContextClassLoader();
        if (currentLoader instanceof DynamicContentClassLoader && currentLoader.equals(classloader)) {
            return ((DynamicContentClassLoader) currentLoader).getParentLoader();
        }
        return currentLoader.getParent();
    }
    /**
     * 设定当前线程类加载
     */
    public synchronized static void setCurrentClassLoader(){
        Thread.currentThread().setContextClassLoader(classloader);
    }
    
}
