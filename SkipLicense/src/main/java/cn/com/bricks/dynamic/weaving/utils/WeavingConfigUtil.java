/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.bricks.dynamic.weaving.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *
 * @author kete
 */
public enum WeavingConfigUtil {

    INSTANCE;
    private final static String WEAVING_CONFIG_REPLACES = "replaces";
    private final static String WEAVING_CONFIG_DEBUG = "debug";
    private final Map<String, List<String>> REPLACES_MAPING = new HashMap<String, List<String>>();
    private boolean isDebug;

    private WeavingConfigUtil() {
        try {
            Properties _CONFIG = new Properties();
            _CONFIG.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config/weaving_config.properties"));

            String jsonstr = _CONFIG.getProperty(WEAVING_CONFIG_REPLACES, "[]");
            ScriptEngineManager manager = new ScriptEngineManager();

            ScriptEngine engine = manager.getEngineByName("javascript");
            Map<String,Object> mockJSON =(Map<String,Object>) engine.eval(jsonstr);
//            // 获取配置的json字符
            // 如果存在就初始化数据，便于后面做类+方法的mock处理
            if (!mockJSON.isEmpty() && mockJSON.size() > 0) {
                for (Iterator it = mockJSON.values().iterator(); it.hasNext();) {
                    Map<String,Object> mockJSON1 = (Map<String,Object>) it.next();
                    if (mockJSON1.containsKey("className")) {
                        List<String> methods = new LinkedList<String>();
                        // 保存需要mock的方法
                        if (mockJSON1.containsKey("methods")) {
                            Map<String,Object> methodMap = (Map<String,Object>) mockJSON1.get("methods");
                            for (Iterator it1 = methodMap.values().iterator(); it1.hasNext();) {
                                methods.add((String) it1.next());
                            }
                        }
                        REPLACES_MAPING.put((String)mockJSON1.get("className"), methods);
                    }
                }
            }
            isDebug = Boolean.valueOf(_CONFIG.getProperty(WEAVING_CONFIG_DEBUG, "false"));
            System.out.println(REPLACES_MAPING);
        } catch (IOException ex) {
            Logger.getLogger(WeavingConfigUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ScriptException ex) {
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

        return REPLACES_MAPING.containsKey(className.replace("/", ".")) && REPLACES_MAPING.get(className.replace("/", ".")).contains(methodName);
    }

    /**
     * 验证是否需要替换类
     *
     * @param className
     * @return
     */
    public boolean isReplaceClass(String className) {

        return REPLACES_MAPING.containsKey(className.replace("/", "."));
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
