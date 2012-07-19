package cn.com.byd.support.impl;


import cn.com.byd.support.ILogger;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 日志输出类
 * @author jianghongquan
 * @version v1.0
 */
public class Log implements ILogger {
  private final static String ERROR = "ERROR";
  private final static String DEBUG = "DEBUG";
  private static Logger logger = null;

  public Log() {
    getLogger(Logger.GLOBAL_LOGGER_NAME, null);
  }

  public Log(String name) {
    getLogger(name, null);
  }

  public Log(String name, String resouceBoundle) {
    getLogger(name, resouceBoundle);
  }

  private static synchronized void getLogger(String name,
                                             String resouceBoundle) {
    if (logger != null) {
      return;
    }
    logger = Logger.getLogger(name, resouceBoundle);
  }

  /**
   * 调试输出
   * @param msg
   */
  public void debug(String msg) {
    logger.log(Level.SEVERE, DEBUG, new Exception(msg));
  }

  /**
   * 消息输出
   * @param msg
   */
  public void info(String msg) {
    logger.info(msg);
  }

  /**
   * 警告输出
   * @param msg
   */
  public void warning(String msg) {
    logger.warning(msg);
  }

  /**
   * 异常输出
   * @param ex
   */
  public void error(Throwable ex) {
    logger.log(Level.SEVERE, ERROR, ex);
  }
}
