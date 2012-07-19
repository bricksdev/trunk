package cn.com.byd.factory;

import cn.com.byd.support.ILogger;

public interface LoggerFactory {
  /**
   * 获取日志输出类
   * @return
   */
  public ILogger getLogger();

  /**
   * 获取日志输出类
   * @param clazz
   * @return
   */
  public ILogger getLogger(Class clazz);

  /**
   * 获取日志输出类
   * @param clazz
   * @param resouceBoudle
   * @return
   */
  public ILogger getLogger(Class clazz, String resouceBoudle);
}
