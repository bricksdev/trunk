package cn.com.byd.factory.impl;


import cn.com.byd.domain.ConfigDomain;
import cn.com.byd.exceptions.AppExceptin;
import cn.com.byd.factory.BeanFactory;
import cn.com.byd.factory.builder.FactoryBuilder;
import cn.com.byd.utils.RefObjectUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultBeanFactory implements BeanFactory {

	private final static Map<String, Object> SINGLETON_BEAN_MAPPING = new ConcurrentHashMap<String, Object>();
	private final static Map<String, ConfigDomain> COMMON_BEAN_MAPPING = new ConcurrentHashMap<String, ConfigDomain>();

	/**
	 * 获取实例
	 * @param beanID
	 * @return
	 * @throws AppExceptin
	 */
	public Object getBean(String beanID) throws AppExceptin {
		Object obj = null;
		if (SINGLETON_BEAN_MAPPING.containsKey(beanID)) {
			obj = SINGLETON_BEAN_MAPPING.get(beanID);
		}

		if (COMMON_BEAN_MAPPING.containsKey(beanID)) {
			ConfigDomain config = COMMON_BEAN_MAPPING.get(beanID);

			if (config == null) {
				throw new AppExceptin("dont found config information:" + beanID);
			}
			if (config.isSingle()) {

				if (config.isProxyed()) {
					SINGLETON_BEAN_MAPPING.put(beanID,
											   FactoryBuilder.getProxyFactory().getProxy().findInterface(beanID, RefObjectUtil.loadClass(config.getClazzName())));
				} else {
					SINGLETON_BEAN_MAPPING.put(beanID, RefObjectUtil.loadClass(config.getClazzName()));
				}
				COMMON_BEAN_MAPPING.remove(beanID);
			} else {
				if (config.isProxyed()) {
					obj =
			   FactoryBuilder.getProxyFactory().getProxy().findInterface(beanID, RefObjectUtil.loadClass(config.getClazzName()));
				} else {
					obj = RefObjectUtil.loadClass(config.getClazzName());
				}
			}

		}

		if (obj == null) {
			throw new AppExceptin("dont found bean id:" + beanID);
		}
		return obj;
	}

	public void addBean(String beanID, String className, boolean isSingle, boolean isProxyed) throws AppExceptin {
		if (SINGLETON_BEAN_MAPPING.containsKey(beanID)) {
			throw new AppExceptin("bean id is found:" + beanID);
		}
		if (COMMON_BEAN_MAPPING.containsKey(beanID)) {
			throw new AppExceptin("bean id is found:" + beanID);
		}
		if (isSingle) {
			if (isProxyed) {
				SINGLETON_BEAN_MAPPING.put(beanID,
										   FactoryBuilder.getProxyFactory().getProxy().findInterface(beanID, RefObjectUtil.loadClass(className)));
			} else {
				SINGLETON_BEAN_MAPPING.put(beanID, RefObjectUtil.loadClass(className));
			}
		} else {
			ConfigDomain config = new ConfigDomain();
			config.setProxyed(isProxyed);
			config.setSingle(isSingle);
			config.setClazzName(className);
			COMMON_BEAN_MAPPING.put(beanID, config);
		}
	}


}
