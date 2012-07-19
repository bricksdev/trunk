package cn.com.byd.support;

public interface ILogger {
  /**
   * 调试输出
   * @param msg
   */
  public void debug(String msg);

  /**
   * 消息输出
   * @param msg
   */
  public void info(String msg);

  /**
   * 警告输出
   * @param msg
   */
  public void warning(String msg);

  /**
   * 异常输出
   * @param ex
   */
  public void error(Throwable ex);
}
