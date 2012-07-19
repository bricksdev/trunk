package cn.com.byd.proxy.handler;


import cn.com.byd.compose.scope.MethodContext;
import cn.com.byd.compose.scope.ModuleContext;
import cn.com.byd.factory.builder.FactoryBuilder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class InterceptionHandler implements InvocationHandler {
	private Object proxyed = null;
	private String beanID = null;

	public InterceptionHandler(Object obj, String beanID) {
		super();
		this.proxyed = obj;
		this.beanID = beanID;
	}


	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		MethodContext bean = ModuleContext.INSTANCE.findMethodContext(beanID, method.getName());
		if (bean.isVerify()) {
			// 验证调用权限
			FactoryBuilder.getAuthenticationFactory().getAutoentication().checkMethodAuth(method.getName(),
																						  proxyed.getClass().getName());
		}
		// 处理日志输出
		if (bean.isLog()) {
			FactoryBuilder.getLoggerFactory().getLogger(InterceptionHandler.class).info(" ++++++++ " +
																						proxyed.getClass().getName() + " method:" + method.getName() +
																						" START");
		}
		// 处理事务
		if (bean.isTransaction()) {
			FactoryBuilder.getTransactionFactory().getTransaction().begin();
		}
		Object obj = null;
		try {
			obj = method.invoke(proxyed, args);
		} catch (Throwable ex) {
			FactoryBuilder.getLoggerFactory().getLogger(InterceptionHandler.class).error(ex);
			// 处理事务
			if (bean.isTransaction()) {
				FactoryBuilder.getTransactionFactory().getTransaction().rollback();
			}
			throw ex;
		}
		// 处理事务
		if (bean.isTransaction()) {
			FactoryBuilder.getTransactionFactory().getTransaction().commit();
		}
		// 处理日志输出
		if (bean.isLog()) {
			FactoryBuilder.getLoggerFactory().getLogger(InterceptionHandler.class).info(" ++++++++ " +
																						proxyed.getClass().getName() + " method:" + method.getName() +
																						" END");
		}
		return obj;
	}
}
