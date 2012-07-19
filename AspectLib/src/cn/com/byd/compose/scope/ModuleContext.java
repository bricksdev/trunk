package cn.com.byd.compose.scope;


import cn.com.byd.compose.source.ModuleMethodSource;
import cn.com.byd.compose.source.ModuleSource;

import java.util.List;

public enum ModuleContext {
	INSTANCE;

	/**
	 * 类列表
	 */
	private final static ModuleSource CLASS_SOURCE = ModuleSource.INSTANCE;

	/**
	 * 保持线程间的数据不能被共享
	 */
	private final static ThreadLocal<MethodContext> CURRENT_THREAD_METHOD_CONTEXT = new ThreadLocal<MethodContext>();

	/**
	 * 获取方法上下文
	 * @param className
	 * @param methodName
	 * @return
	 */
	public MethodContext findMethodContext(String className, String methodName) {
      
		MethodContext methodContext = CURRENT_THREAD_METHOD_CONTEXT.get();
		if (methodContext != null) {
			return methodContext;
		}
		List<ModuleMethodSource> methodModules = CLASS_SOURCE.findMethods(className);

		for (ModuleMethodSource modules : methodModules) {
			if (methodName.equals(modules.getMethod())) {
				methodContext = modules.getMethodContext().clone();
				CURRENT_THREAD_METHOD_CONTEXT.set(methodContext);
				return methodContext;
			}
		}
		return MethodContext.NULL;
	}

	/**
	 * 获取方法上下文
	 * @param id
	 * @return
	 */
	public MethodContext findMethodContext(String id) {
		MethodContext methodContext = CURRENT_THREAD_METHOD_CONTEXT.get();
		if (methodContext != null) {
			return methodContext;
		}
		ModuleMethodSource methodModule = CLASS_SOURCE.findMethodsByPrimaryKey(id);

		methodContext = methodModule.getMethodContext().clone();
		CURRENT_THREAD_METHOD_CONTEXT.set(methodContext);
		return methodContext;

	}
}
