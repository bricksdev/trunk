/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.refects.handler;

import cn.com.refects.TypeContentRefecter;
import cn.com.refects.TypeInitialization;
import cn.com.refects.provider.DataProviderUtil;
import cn.com.utils.DataProviderConfig.DataProviderModel;
import cn.com.utils.StringUtil;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * mock接口的代理实现，只做参数验证及方法运行后结果返回，数据根据基本数据类型进行模拟，
 * <br/>
 * 数据模拟结果依赖与数据提供的方式
 * @author kete
 */
public class MockInvocationHandler implements InvocationHandler {

    private static final String SpliterSymbol = "/";
    private DataProviderModel model = null;

    public MockInvocationHandler(DataProviderModel model) {
        this.model = model;
    }

    /**
     * 验证数据及其他处理
     */
    private void before() {
        // TODO
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object value = null;
        switch (model) {
            case FILE:
                String parameter = null;
                if (args != null && args.length == 1 && args[0] instanceof String) {
                    parameter = String.valueOf(args[0]);
                }
                value = invokeByFile(method, parameter);
                break;
            default:
                value = invoke(method);
                break;
        }
        return value;
    }

    /**
     * 模拟返回的数据通过对象反射
     * @param method
     * @return
     * @throws Throwable
     */
    private Object invoke(Method method) throws Throwable {

        // 排除void类
        if (method.getReturnType().equals(Void.class)) {
            return null;
        }
        // 返回通用类初始化
        if (TypeInitialization.isCommonType(method.getReturnType())) {
            return TypeInitialization.initializeCommonType(method.getReturnType());
        }

        // 处理数组类型初始化
        if (method.getReturnType().isArray()) {
            return TypeInitialization.initializeList(method.getReturnType().getComponentType()).toArray();
        }

        // 处理列表类初始化
        if ((method.getReturnType().isInterface() && method.getReturnType().equals(List.class))) {

            return TypeInitialization.initializeGenericList(method.getGenericReturnType());

        }

        // 验证可处理的返回类型初始化
        boolean isMaybe = true;
        // 不处理映射类型初始化
        if (method.getReturnType().isInterface() && method.getReturnType().equals(Map.class)) {
            isMaybe = false;
        }

        // 处理一般类型初始化
        if (!method.getReturnType().isInterface()) {
            return TypeInitialization.initializeObject(method.getReturnType());
        }

        if (!isMaybe) {
            throw new RuntimeException("don't operat method return type :" + method.getReturnType().getName());
        }
        return null;
    }

    /**
     * 模拟返回的数据通过数据提供的文件
     * @param method
     * @param parameter 单字符传递获取不同数据的标示
     * @return
     * @throws Throwable
     */
    public Object invokeByFile(Method method, String parameter) throws Throwable {

        // 排除void类
        if (method.getReturnType().equals(Void.class)) {
            return null;
        }
        String path = StringUtil.joinString(SpliterSymbol, method.getDeclaringClass().getSimpleName(), method.getName(), parameter);
        List results = null;
        // 返回通用类初始化
        if (TypeInitialization.isCommonType(method.getReturnType())) {
            return TypeInitialization.initializeCommonType(method.getReturnType());
        }

        // 处理数组类型初始化
        if (method.getReturnType().isArray()) {
            results = DataProviderUtil.INSTACE.getData(path, method.getReturnType().getComponentType());
            return results == null || results.isEmpty() ? null : results.toArray();
        }

        // 处理列表类初始化
        if ((method.getReturnType().isInterface() && method.getReturnType().equals(List.class))) {
            Class contentClass = TypeContentRefecter.getGenericClass(method.getGenericReturnType());
            String contentPath = StringUtil.joinString(SpliterSymbol, path, contentClass.getSimpleName());
            results = DataProviderUtil.INSTACE.getData(contentPath, contentClass);
            return results;

        }

        // 验证可处理的返回类型初始化
        boolean isMaybe = true;
        // 不处理映射类型初始化
        if (method.getReturnType().isInterface() && method.getReturnType().equals(Map.class)) {
            isMaybe = false;
        }

        // 处理一般类型初始化
        if (!method.getReturnType().isInterface()) {
            Class contentClass = method.getReturnType();
            String contentPath = StringUtil.joinString(SpliterSymbol, path, contentClass.getSimpleName());
            results = DataProviderUtil.INSTACE.getData(contentPath, method.getReturnType());
            return results == null || results.isEmpty() ? null : results.get(0);
        }

        if (!isMaybe) {
            throw new RuntimeException("don't operat method return type :" + method.getReturnType().getName());
        }
        return null;
    }
}
