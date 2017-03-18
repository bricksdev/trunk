/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.bricks.dynamic.weaving.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kete
 */
public enum WeavingConfigUtil {

    INSTANCE;
    private final static String WEAVING_CONFIG_REPLACE_METHOD = "replace-method";
    private final static String WEAVING_CONFIG_REPLACE_CLASS = "replace-class";
    private final static String WEAVING_CONFIG_DEBUG = "debug";
    private String replaceMethod;
    private String replaceClass;
    private boolean isDebug;

    private WeavingConfigUtil() {
        try {
            Properties _CONFIG = new Properties();
            _CONFIG.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config/weaving_config.properties"));

            replaceMethod = _CONFIG.getProperty(WEAVING_CONFIG_REPLACE_METHOD);
            replaceClass = _CONFIG.getProperty(WEAVING_CONFIG_REPLACE_CLASS);
            isDebug = Boolean.valueOf(_CONFIG.getProperty(WEAVING_CONFIG_DEBUG, "false"));
        } catch (IOException ex) {
            Logger.getLogger(WeavingConfigUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * 验证是否需要替换的方法
     *
     * @param className
     * @param methodName
     * @return
     */
    public boolean isReplaceMethod(String className, String methodName) {

        return (className.replace("/", ".") + "." + methodName).equals(replaceMethod);
    }

    /**
     * 验证是否需要替换类
     *
     * @param className
     * @return
     */
    public boolean isReplaceClass(String className) {

        return (className.replace("/", ".")).equals(replaceClass);
    }

    /**
     * 是否debug
     *
     * @return
     */
    public boolean isDebug() {
        return isDebug;
    }
}
