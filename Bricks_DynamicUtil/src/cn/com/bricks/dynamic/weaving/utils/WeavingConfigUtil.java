/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.bricks.dynamic.weaving.utils;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author kete
 */
public enum WeavingConfigUtil {

    INSTANCE;
    private Pattern _PATTERN_JARS;
    private Pattern _NO_WEAVING_PKGS_CLASS;
    private Pattern _NO_WEAVING_METHOD;
    private final static String _NO_WEAVING_CONFIG_CLASSPKS = "no-class-pkgs";
    private final static String _WEAVING_CONFIG_JARS = "weaving-jars";
    private final static String _NO_WEAVING_CONFIG_METHOD = "no-weaving-method";
    private final static String _WEAVING_CONFIG_REPLACE_METHOD = "replace-method";
    private final static String _WEAVING_CONFIG_REPLACE_CLASS = "replace-class";
    private final static String _WEAVING_CONFIG_DEBUG = "debug";
    private final static String _WEAVING_CONFIG_WEAVING_CLASSLOADER = "weaving-classloader";
    private String _REPLACE_METHOD;
    private String _REPLACE_CLASS;
    private String _WEAVING_LOADER;
    private boolean isDebug = true;

    private WeavingConfigUtil() {
        try {
            Properties _CONFIG = new Properties();
            _CONFIG.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config/weaving_config.properties"));

            _PATTERN_JARS = Pattern.compile(_CONFIG.getProperty(_WEAVING_CONFIG_JARS));
            _NO_WEAVING_PKGS_CLASS = Pattern.compile(_CONFIG.getProperty(_NO_WEAVING_CONFIG_CLASSPKS));
            _NO_WEAVING_METHOD = Pattern.compile(_CONFIG.getProperty(_NO_WEAVING_CONFIG_METHOD));
            _REPLACE_METHOD = _CONFIG.getProperty(_WEAVING_CONFIG_REPLACE_METHOD);
            _REPLACE_CLASS = _CONFIG.getProperty(_WEAVING_CONFIG_REPLACE_CLASS);
            isDebug = Boolean.parseBoolean(_CONFIG.getProperty(_WEAVING_CONFIG_DEBUG, "true"));
            _WEAVING_LOADER = _CONFIG.getProperty(_WEAVING_CONFIG_WEAVING_CLASSLOADER, "");
        } catch (IOException ex) {
            Logger.getLogger(WeavingConfigUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * 判断是否不需要注入的类与包
     *
     * @param content
     * @return
     */
    public boolean isNoWeavingClassAndPkgs(String content) {
        return _NO_WEAVING_PKGS_CLASS.matcher(content).find();
    }

    /**
     * 判断是否不需要注入的方法
     *
     * @param content
     * @return
     */
    public boolean isNoWeavingMethod(String content) {
        return _NO_WEAVING_METHOD.matcher(content).find();
    }

    /**
     * 判断是否需要注入的包
     *
     * @param content
     * @return
     */
    public boolean isWeavingJar(String content) {
        return _PATTERN_JARS.matcher(content).find();
    }

    /**
     * 验证是否需要替换的方法
     *
     * @param className
     * @param methodName
     * @return
     */
    public boolean isReplaceMethod(String className, String methodName) {

        return (className.replace("/", ".") + "." + methodName).equals(_REPLACE_METHOD);
    }

    /**
     * 验证是否需要替换类
     *
     * @param className
     * @return
     */
    public boolean isReplaceClass(String className) {

        return (className.replace("/", ".")).equals(_REPLACE_CLASS);
    }

    /**
     * 是否为DEBUG模式
     *
     * @return
     */
    public boolean isDebug() {
        return isDebug;
    }

    /**
     * 判断是否为植入的classloader
     *
     * @param loader
     * @return
     */
    public boolean isWeavingLoader(ClassLoader loader) {
        return loader != null && loader.getClass().getName().equals(_WEAVING_LOADER);
    }
}
