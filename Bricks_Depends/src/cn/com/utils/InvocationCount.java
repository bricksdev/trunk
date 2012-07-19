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
    private static final String _DATA_CONFIG_FILE = "data/config/config.properties";
    private static final String _INVOCATION = "invocation_count";
    private int _INVOCATION_COUNT = 10;

    private InvocationCount() {
        try {
            Properties properties = new Properties();
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(_DATA_CONFIG_FILE));
            _INVOCATION_COUNT = Integer.valueOf(properties.getProperty(_INVOCATION, String.valueOf(_INVOCATION_COUNT)));

        } catch (IOException ex) {
            Logger.getLogger(InvocationCount.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * 获取反射次数
     * @return
     */
    public int getCount() {
        return _INVOCATION_COUNT;
    }
}
