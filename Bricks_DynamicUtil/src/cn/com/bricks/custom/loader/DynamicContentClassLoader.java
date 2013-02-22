/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.bricks.custom.loader;

import cn.com.bricks.dynamic.loader.util.DynamicDeployUtil;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 上下文类加载器
 *
 * @author kete
 */
public class DynamicContentClassLoader extends ClassLoader {

    private ClassLoader parent = null;
    private Map<String, Class<?>> _CLASS_CACHE = new HashMap<String, Class<?>>();

    public DynamicContentClassLoader() {
        super();
    }

    public DynamicContentClassLoader(ClassLoader parent) {
        this.parent = parent;
    }

    /**
     * 获取父loader
     * @return 
     */
    public ClassLoader getParentLoader(){
        return parent;
    }
    
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // 判断当前类如果需要载将进行重载
        if (DynamicDeployUtil.isReloadResource(name)) {
            return null;
        }
        Class cl = _CLASS_CACHE.get(name);
        return cl;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        Class cl = this.findClass(name);
        if (cl == null) {
            cl = this.loadClass(name, false);
        }
        return cl;
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {

        // 当前类需要重载，进行处理
        if (DynamicDeployUtil.isReloadResource(name)) {
            try {
                byte[] bytes = DynamicDeployUtil.findResource(name);
                Class cl = super.defineClass(name, bytes, 0, bytes.length);
                _CLASS_CACHE.put(name, cl);
                return cl;
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(DynamicContentClassLoader.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(DynamicContentClassLoader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Class cl = parent.loadClass(name);
        return cl;
    }

    @Override
    protected URL findResource(String name) {
        return parent.getResource(name);
    }

    @Override
    public InputStream getResourceAsStream(String name) {
        return parent.getResourceAsStream(name);
    }

    @Override
    public URL getResource(String name) {
        return parent.getResource(name);
    }
}
