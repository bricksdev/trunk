/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.utils;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kete
 */
public enum DataProviderConfig {

    INSTANCE;
    private static final String __DATA_CONFIG_FILE = "data/config/config.properties";
    private static final String __INVOCATION = "invocation_count";
    private static final String __DATA_PROVIDE_MODEL = "data_provide_model";
    private static final String __BASE_PATH = "ROOT";
    private static final String DEFAULT_SPLITER = ",";
    private static final String CONFIG_SPLITER = "SPLITER";
    private String basePath = null;
    private String spliter = null;
    private int __INVOCATION_COUNT = 5;
    private DataProviderModel __MODEL = DataProviderModel.MOCK;

    private DataProviderConfig() {
        try {
            Properties properties = new Properties();
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(__DATA_CONFIG_FILE));
            __INVOCATION_COUNT = Integer.valueOf(properties.getProperty(__INVOCATION, String.valueOf(__INVOCATION_COUNT)));
            __MODEL = DataProviderModel.valueOf(properties.getProperty(__DATA_PROVIDE_MODEL, __MODEL.name()).toUpperCase());
            basePath = properties.getProperty(__BASE_PATH);
            spliter = properties.getProperty(CONFIG_SPLITER, DEFAULT_SPLITER);
        } catch (IOException ex) {
            Logger.getLogger(DataProviderConfig.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * 获取到跟路径
     * @return 返回配置文件base_path.properties中跟路径
     */
    public String getBasePath() {
        return basePath;
    }

    /**
     * 获取分割符默认为","分割
     * @return
     */
    public String getSpliter() {
        return spliter;
    }

    /**
     * 获取反射次数
     * @return
     */
    public int getCount() {
        return __INVOCATION_COUNT;
    }

    /**
     * 获取当前数据提供者的模式
     * MOCK:系统自动创建的数据，内容都是固定显示
     * FILE:此模式是根据文件中设定的内容进行模拟数据（此方法便于实际意义的单元测试）
     * @return
     */
    public DataProviderModel getProviderModel() {
        return __MODEL;
    }

    public enum DataProviderModel {

        MOCK,
        FILE
    }
}
