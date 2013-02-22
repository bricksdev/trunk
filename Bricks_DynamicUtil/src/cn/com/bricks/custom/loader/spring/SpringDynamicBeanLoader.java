/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.bricks.custom.loader.spring;

import cn.com.bricks.dynamic.loader.util.DynamicDeployUtil;
import cn.com.bricks.dynamic.loader.util.LoaderUtil;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.support.XmlWebApplicationContext;

/**
 *
 * @author kete
 */
public abstract class SpringDynamicBeanLoader {

    private final static String _SPRING_CONFIG_NAME = "springreload.properties";

    /**
     * reload spring special bean
     */
    public synchronized static void refreshBean() {
        // 只有需要重新加载时才进行SPRING指定bean加载
        if (DynamicDeployUtil.isReload() && DynamicDeployUtil.isReloadResource(_SPRING_CONFIG_NAME)) {
            try {
//                ClassLoader loader = LoaderUtil.getCurrentLoaderParentLoader();
//                Thread.currentThread().setContextClassLoader(loader);
                XmlWebApplicationContext xmlWebApplicationContext = (XmlWebApplicationContext) ContextLoader.getCurrentWebApplicationContext();
                DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) xmlWebApplicationContext.getBeanFactory();
                Properties pros = new Properties();
                defaultListableBeanFactory.setBeanClassLoader(LoaderUtil.getClassLoader());
                pros.load(new ByteArrayInputStream(DynamicDeployUtil.findResource(_SPRING_CONFIG_NAME)));
                for (String key : pros.stringPropertyNames()) {
                    BeanDefinition beanDefinition = defaultListableBeanFactory.getBeanDefinition(key);
                    beanDefinition.setBeanClassName(pros.getProperty(key));
                    defaultListableBeanFactory.registerBeanDefinition(key, beanDefinition);
                }
            } catch (IOException ex) {
                Logger.getLogger(SpringDynamicBeanLoader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
