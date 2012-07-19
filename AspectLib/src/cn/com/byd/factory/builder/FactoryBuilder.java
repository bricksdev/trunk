package cn.com.byd.factory.builder;

import cn.com.byd.factory.AuthenticationFactory;
import cn.com.byd.factory.BeanFactory;
import cn.com.byd.factory.LoggerFactory;
import cn.com.byd.factory.ProxyFactory;
import cn.com.byd.factory.TransactionFactory;
import cn.com.byd.factory.impl.DefaultAuthenticationFactory;
import cn.com.byd.factory.impl.DefaultBeanFactory;
import cn.com.byd.factory.impl.DefaultLoggerFactory;
import cn.com.byd.factory.impl.DefaultProxyFactory;
import cn.com.byd.factory.impl.DefaultTransactionFactory;
import cn.com.byd.utils.RefObjectUtil;
import cn.com.byd.utils.StringUtil;

public abstract class FactoryBuilder {
	private static BeanFactory beanFactory = null;
	private static LoggerFactory loggerFactory = null;
	private static ProxyFactory proxyFactory = null;
	private static AuthenticationFactory authenticationFactory = null;
	private static TransactionFactory transactionFactory = null;

	/**
	 *获取指定的bean工厂的实现类
	 * @param factoryName
	 * @return
	 */
	public static void createBeanFactory(String factoryName) {
		if (beanFactory != null) {
			return;
		}
		// 如果传入的工厂为空将采用默认的工厂模式
		if (StringUtil.isEmptyAndNull(factoryName)) {
			beanFactory = new DefaultBeanFactory();
			return;
		}

		beanFactory = (BeanFactory)RefObjectUtil.loadClass(factoryName);
	}

	/**
	 *获取指定全局bean工厂的实现类
	 * @return
	 */
	public static BeanFactory getBeanFactory() {
		return beanFactory == null ? new DefaultBeanFactory() : beanFactory;
	}

	/**
	 *获取指定的logger工厂的实现类
	 * @param factoryName
	 * @return
	 */
	public static void createLoggerFactory(String factoryName) {
		if (loggerFactory != null) {
			return;
		}
		// 如果传入的工厂为空将采用默认的工厂模式
		if (StringUtil.isEmptyAndNull(factoryName)) {
			loggerFactory = new DefaultLoggerFactory();
			return;
		}
		loggerFactory = (LoggerFactory)RefObjectUtil.loadClass(factoryName);
	}

	/**
	 *获取指定全局logger工厂的实现类
	 * @return
	 */
	public static LoggerFactory getLoggerFactory() {
		return loggerFactory == null ? new DefaultLoggerFactory() : loggerFactory;
	}

	/**
	 *获取指定的Proxy工厂的实现类
	 * @param factoryName
	 * @return
	 */
	public static void createProxyFactory(String factoryName) {
		if (proxyFactory != null) {
			return;
		}
		// 如果传入的工厂为空将采用默认的工厂模式
		if (StringUtil.isEmptyAndNull(factoryName)) {
			proxyFactory = new DefaultProxyFactory();
			return;
		}
		proxyFactory = (ProxyFactory)RefObjectUtil.loadClass(factoryName);
	}

	/**
	 *获取指定全局Proxy工厂的实现类
	 * @return
	 */
	public static ProxyFactory getProxyFactory() {
		return proxyFactory == null ? new DefaultProxyFactory() : proxyFactory;
	}

	/**
	 *获取指定的操作权限验证工厂的实现类
	 * @param factoryName
	 * @return
	 */
	public static void createAuthenticationFactory(String factoryName) {
		if (authenticationFactory != null) {
			return;
		}
		// 如果传入的工厂为空将采用默认的工厂模式
		if (StringUtil.isEmptyAndNull(factoryName)) {
			authenticationFactory = new DefaultAuthenticationFactory();
			return;
		}
		authenticationFactory = (AuthenticationFactory)RefObjectUtil.loadClass(factoryName);
	}

	/**
	 *获取指定全局操作权限验证工厂的实现类
	 * @return
	 */
	public static AuthenticationFactory getAuthenticationFactory() {
		return authenticationFactory == null ? new DefaultAuthenticationFactory() : authenticationFactory;
	}

	/**
	 *获取指定的事务工厂的实现类
	 * @param factoryName
	 * @return
	 */
	public static void createTransactionactory(String factoryName) {
		if (transactionFactory != null) {
			return;
		}
		// 如果传入的工厂为空将采用默认的工厂模式
		if (StringUtil.isEmptyAndNull(factoryName)) {
			transactionFactory = new DefaultTransactionFactory();
			return;
		}
		transactionFactory = (TransactionFactory)RefObjectUtil.loadClass(factoryName);
	}

	/**
	 *获取指定全局事务工厂的实现类
	 * @return
	 */
	public static TransactionFactory getTransactionFactory() {
		return transactionFactory == null ? new DefaultTransactionFactory() : transactionFactory;
	}
}
