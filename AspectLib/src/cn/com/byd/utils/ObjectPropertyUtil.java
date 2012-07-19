package cn.com.byd.utils;

import java.lang.reflect.Method;

public abstract class ObjectPropertyUtil {

	public final static String SPLIT_CHARACTER = "[.]";

	/**
	 * 获取类的方法
	 * @param clazz
	 * @param methodName
	 * @param args
	 * @return
	 * @throws NoSuchMethodException
	 */
	public static Method findMethod(Class clazz, String methodName, Object... args) throws NoSuchMethodException {

		return RefObjectUtil.findMethod(clazz, methodName, getObjectClass(args));
	}

	private static Class[] getObjectClass(Object... args) {
		Class[] clazz = new Class[args.length];
		int idx = 0;
		for (Object tmpObj : args) {
			clazz[idx++] = tmpObj.getClass();
		}
		return clazz;
	}

	/**
	 *获取当前对象 内对象的属性值
	 * @param obj
	 * @param deepFieldName
	 * @return
	 */
	public static Object getDeepFieldValue(Object obj, String deepFieldName) {
		if (obj == null) {
			return null;
		}
		String fieldName = null;
		String[] fields = null;
		Object tmpObj = null;
		while (deepFieldName.contains(SPLIT_CHARACTER)) {
			fields = deepFieldName.split(SPLIT_CHARACTER, 2);
			fieldName = fields[0];
			deepFieldName = fields[1];
			if (tmpObj == null) {
				tmpObj = obj;
			}
			tmpObj = RefObjectUtil.getField(tmpObj.getClass(), fieldName, tmpObj);
		}

		return tmpObj;
	}
}
