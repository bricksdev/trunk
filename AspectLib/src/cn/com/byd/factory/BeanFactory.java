package cn.com.byd.factory;

import cn.com.byd.exceptions.AppExceptin;

public interface BeanFactory {

	/**
	 * 获取指定类的实体
	 * @param beanId
	 * @return
	 * @throws AppExceptin
	 */
	public abstract Object getBean(String beanId) throws AppExceptin;


	/**
	 * 加实例对象
	 * @param beanId
	 * @param className
	 * @param isSingle
	 * @param isProxyed
	 * @throws AppExceptin
	 */
	public abstract void addBean(String beanId, String className, boolean isSingle,
								 boolean isProxyed) throws AppExceptin;

}
