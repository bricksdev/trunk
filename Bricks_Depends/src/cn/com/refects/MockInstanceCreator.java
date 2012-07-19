/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.refects;

import cn.com.refects.handler.MockInvocationHandler;
import cn.com.utils.DataProviderConfig;
import cn.com.utils.DataProviderConfig.DataProviderModel;
import java.lang.reflect.Proxy;

/**
 *
 * @author kete
 */
public class MockInstanceCreator {

    /**
     * 获取mock实类
     * @param clazz
     * @return
     */
    public static Object instance(Class<?> clazz, DataProviderModel model) {

        return Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new MockInvocationHandler(model));
    }

    /**
     * 获取mock实类
     * @param clazz
     * @param parameters
     * @return
     */
    public static Object instance(Class<?> clazz, Object parameters) {

        return instance(clazz, DataProviderConfig.INSTANCE.getProviderModel());
    }

    /**
     * 获取mock实类
     * @param clazz
     * @param parameters
     * @return
     */
    public static Object instance(Class<?> clazz, Object parameters, DataProviderModel model) {

        return instance(clazz, model);
    }
}
