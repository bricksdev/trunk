package cn.com.byd.compose;


import cn.com.byd.compose.beans.Append;
import cn.com.byd.compose.beans.ModuleBean;
import cn.com.byd.compose.beans.Parameter;
import cn.com.byd.compose.beans.ParameterType;
import cn.com.byd.compose.beans.Result;
import cn.com.byd.compose.beans.Status;
import cn.com.byd.compose.modules.EqualGroupModules;
import cn.com.byd.compose.modules.IterateModules;
import cn.com.byd.compose.scope.MethodContext;
import cn.com.byd.compose.scope.ModuleContext;
import cn.com.byd.exceptions.AppExceptin;
import cn.com.byd.exceptions.NoModuleException;
import cn.com.byd.exceptions.ParameterException;
import cn.com.byd.factory.BeanFactory;
import cn.com.byd.factory.builder.FactoryBuilder;
import cn.com.byd.utils.CheckAppendableClassUtil;
import cn.com.byd.utils.InvokeMethodUtil;
import cn.com.byd.utils.StringUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class ModuleComposer {
	private final static BeanFactory beanFactory = FactoryBuilder.getBeanFactory();

	/**
	 *运行ID指定的组件
	 * @param id
	 * @param args
	 * @return
	 * @throws AppExceptin
	 * @throws NoModuleException
	 * @throws ParameterException
	 */
	public static Object runModules(String id, Object[] args) throws AppExceptin, NoModuleException,
																	 ParameterException {
		MethodContext methodContext = ModuleContext.INSTANCE.findMethodContext(id);
		return runModules(args, methodContext);
	}

	/**
	 *运行类.方法中指定的组件
	 * @param beanID
	 * @param methodName
	 * @param args
	 * @return
	 * @throws AppExceptin
	 * @throws NoSuchMethodException
	 * @throws NoModuleException
	 */
	public static Object runModules(String beanID, String methodName, Object[] args) throws AppExceptin,
																							NoModuleException,
																							ParameterException {
		MethodContext methodContext = ModuleContext.INSTANCE.findMethodContext(beanID, methodName);

		return runModules(args, methodContext);
	}

	/**
	 *运行指定的组件
	 * @param args
	 * @param methodContext
	 * @return
	 * @throws ParameterException
	 * @throws NoModuleException
	 * @throws AppExceptin
	 */
	private static Object runModules(Object[] args, MethodContext methodContext) throws ParameterException,
																						NoModuleException,
																						AppExceptin {
		if (methodContext == null || methodContext.equals(MethodContext.NULL)) {
			return null;
		}

		List<ModuleBean> currentModules = methodContext.findCurrentModules();
		Parameter parameter = methodContext.getParameter();
		// 验证参数是否正常
		parameter.checkParameters(args);
		// 初始化方法上下文的参数值
		initMethodContextParameterValues(parameter, args, methodContext);
		// 运行当前非分类及遍历的组件
		runCurrentModules(currentModules, methodContext);
		// 运行条件相同的组件
		runGroupModules(methodContext.findConditionGroupModules(), methodContext);
		// 运行遍历的组件
		runInteratorModules(methodContext.findIteratorModules(), methodContext);
		Result result = methodContext.getReturnResult();
		// 字符类型 且需要组装字符时使用 一般是由某一方法返回的值变成整体的返回值
		if (StringUtil.isEmptyAndNull(result.getRefereceValue()) && result.getAppends() != null) {

			if (result.getClazz().equals(String.class)) {
				StringBuilder builder = new StringBuilder();
				String tempStr = null;
				for (Append append : result.getAppends()) {
					if (append.isEmptyAppend()) {
						if (!StringUtil.isEmptyAndNull(append.getPrepend())) {
							builder.append(append.getPrepend());
						}
						tempStr = String.valueOf(methodContext.getIdValue(append.getProperty()));
						if (!StringUtil.isEmptyAndNull(tempStr)) {
							builder.append(tempStr);
						}
					}
				}
				return builder.toString();
			} else if (CheckAppendableClassUtil.INSTANCE.isCollection(result.getClazz())) {
				Collection collection = new ArrayList();
				Object obj = null;
				for (Append append : result.getAppends()) {
					if (append.isEmptyAppend()) {

						obj = methodContext.getIdValue(append.getProperty());
						if (obj != null) {
							collection.add(obj);
						}
					}
				}
				return collection;
			} else if (CheckAppendableClassUtil.INSTANCE.isMap(result.getClazz())) {
				throw new RuntimeException("Dont support return type:" + result.getRetultType());
			} else {
				throw new RuntimeException("Dont support return type:" + result.getRetultType());
			}
		} else {
			return methodContext.getIdValue(result.getRefereceValue());
		}
	}

	/**
	 *初始化方法上下文的参数值
	 * @param parameter
	 * @param args
	 * @param methodContext
	 */
	private static void initMethodContextParameterValues(Parameter parameter, Object[] args,
														 MethodContext methodContext) {
		List<ParameterType> params = parameter.getParameters();
		int idx = 0;
		for (ParameterType param : params) {
			if (!StringUtil.isEmptyAndNull(param.getId())) {
				methodContext.put(param.getId(), args[idx]);
			}
			idx++;
		}
	}

	/**
	 *运行遍历的组件
	 * @param modules
	 */
	private static void runInteratorModules(IterateModules modules, MethodContext methodContext) throws NoModuleException,
																										AppExceptin,
																										ParameterException {
		if (modules == null || modules.equals(IterateModules.NULL)) {
			return;
		}
		List<? super Object> items = modules.getItems();
		if (items == null || items.isEmpty()) {
			return;
		}
		int idx = 0;
		Status status = modules.getStatus();
		status.setTotal(items.size());
		for (Object obj : items) {
			status.setIndex(idx++);
			//保存临时的变量
			methodContext.put(modules.getItemProperty(), obj);
			// 保存临时变量的状态
			methodContext.put(modules.getStatusId(), status);
			runCurrentModules(modules.getCurrentModules(), methodContext);
			runGroupModules(modules.getEqualGroupModules(), methodContext);

		}
		//移除临时的变量
		methodContext.remove(modules.getItemProperty());
		// 移除临时变量的状态
		methodContext.remove(modules.getStatusId());
	}

	/**
	 *运行条件相同的组件
	 * @param modules
	 */
	private static void runGroupModules(List<EqualGroupModules> modules,
										MethodContext methodContext) throws NoModuleException, AppExceptin,
																			ParameterException {
		if (modules == null || modules.isEmpty()) {
			return;
		}
		for (EqualGroupModules groupModule : modules) {
			if (groupModule.getCompareValue().equals(methodContext.getIdValue(groupModule.getProperty()))) {
				runCurrentModules(groupModule.getGroupModules(), methodContext);
				runInteratorModules(groupModule.getIteratorModules(), methodContext);
			}
		}
	}

	/**
	 *运行当前非分类及遍历的组件
	 * @param modules
	 * @param methodContext
	 * @throws NoModuleException
	 * @throws AppExceptin
	 */
	private static void runCurrentModules(List<ModuleBean> modules, MethodContext methodContext) throws NoModuleException,
																										AppExceptin,
																										ParameterException {
		if (modules == null || modules.isEmpty()) {
			return;
		}

		for (ModuleBean module : modules) {

			// 存在区分是否条件调用组件 将默认执行所有的组件
			if (!StringUtil.isEmptyAndNull(module.getDivisionValue()) &&
				(module.getDivisions() != null && !module.getDivisions().isEmpty())) {

				if (module.getDivisions().contains(methodContext.getIdValue(module.getDivisionValue()))) {
					runMethodModule(module, methodContext);
				}
				// 没有区分是否条件调用组件 将默认执行所有的组件
			} else {
				runMethodModule(module, methodContext);
			}
		}
	}

	private static void runMethodModule(ModuleBean module, MethodContext methodContext) throws AppExceptin,
																							   NoModuleException,
																							   ParameterException {
		// 组件类
		Object moduleObj = null;
		// 组件返回值
		Object retObj = null;
		Result returnedObj = null;
		returnedObj = module.getReturnResult();
		if (!StringUtil.isEmptyAndNull(module.getReferenceId())) {
			retObj = runModules(module.getReferenceId(), module.getParameter().getParametersValue(methodContext));
		} else {
			moduleObj = beanFactory.getBean(module.getBeanId());
			retObj =
	  InvokeMethodUtil.invoke(moduleObj, module.getMethod(), module.getParameter().getParametersValue(methodContext));
		}

		if (!StringUtil.isEmptyAndNull(returnedObj.getId())) {
			methodContext.put(returnedObj.getId(), retObj);
		}
		runInteratorModules(returnedObj.getIteratorModules(), methodContext);
	}
}
