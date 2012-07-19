package cn.com.byd.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 日志输出类
 * @author jianghongquan
 * @version v1.0
 */
public class Log extends Logger {
	private final static String ERROR = "ERROR";
	private final static String DEBUG = "DEBUG";

  public Log(String name) {
    super(name, null);
  }
	public Log(String name, String resouceBoundle) {
		super(name, resouceBoundle);
	}


	/**
   * 调试输出
	 * @param msg
	 */
	public void debug(String msg) {
		super.log(Level.SEVERE, DEBUG, new Exception(msg));
	}

	/**
   * 消息输出
	 * @param msg
	 */
	public void info(String msg) {
		super.info(msg);
	}

	/**
   * 警告输出
	 * @param msg
	 */
	public void warning(String msg) {
		super.warning(msg);
	}

	/**
   * 异常输出
	 * @param ex
	 */
	public void error(Throwable ex) {
		super.log(Level.SEVERE, ERROR, ex);
	}
}
