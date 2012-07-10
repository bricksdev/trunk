/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kete
 */
public enum InvocationCount {

    INSTANCE;
    private static final String __DATA_CONFIG_FILE = "data/config/config.properties";
    private static final String __INVOCATION = "invocation_count";
    private int __INVOCATION_COUNT = 5;

    private InvocationCount() {
        try {
            Properties properties = new Properties();
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(__DATA_CONFIG_FILE));
            __INVOCATION_COUNT = Integer.valueOf(properties.getProperty(__INVOCATION, String.valueOf(__INVOCATION_COUNT)));

        } catch (IOException ex) {
            Logger.getLogger(InvocationCount.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * 获取反射次数
     * @return
     */
    public int getCount() {
        return __INVOCATION_COUNT;
    }
}
