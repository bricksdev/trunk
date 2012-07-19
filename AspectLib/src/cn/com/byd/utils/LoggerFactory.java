package cn.com.byd.utils;

public enum LoggerFactory {
	INSTANCE;

	/**
	 * 获取日志输出类
	 * @param clazz
	 * @return
	 */
	public Log getLogger(Class clazz) {
		return new Log(clazz.getName());
	}

	/**
	 *获取日志输出类
	 * @param clazz
	 * @param resouceBoudle
	 * @return
	 */
	public Log getLogger(Class clazz, String resouceBoudle) {
		return new Log(clazz.getName(), resouceBoudle);
	}
}
