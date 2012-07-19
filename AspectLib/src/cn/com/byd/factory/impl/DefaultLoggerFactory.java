package cn.com.byd.factory.impl;

import cn.com.byd.factory.LoggerFactory;
import cn.com.byd.support.ILogger;
import cn.com.byd.support.impl.Log;

public class DefaultLoggerFactory implements LoggerFactory {

    /**
     * 获取日志输出类
     * @return
     */
    public ILogger getLogger() {
        return new Log();
    }

    /**
     * 获取日志输出类
     * @param clazz
     * @return
     */
    public ILogger getLogger(Class clazz) {
        return new Log(clazz.getName());
    }

    /**
     *获取日志输出类
     * @param clazz
     * @param resouceBoudle
     * @return
     */
    public ILogger getLogger(Class clazz, String resouceBoudle) {
        return new Log(clazz.getName(), resouceBoudle);
    }
}
