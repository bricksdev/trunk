/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.factory;

import cn.com.cache.InstanceCache;
import cn.com.global.MotionModel;
import cn.com.refects.InstanceCreator;
import cn.com.refects.MockInstanceCreator;
import cn.com.utils.DataProviderConfig;
import cn.com.utils.DataProviderConfig.DataProviderModel;

/**
 *
 * @author kete
 */
public class OperatFactory {

    /**
     * 获取实体化对象,默认为单类模式
     * @param <T>
     * @param clazz
     * @return
     */
    public static <T> T getServiceObject(Class<T> clazz) {
        Object instance = null;
        // 测试运行模式
        switch (MotionModel.getModel()) {
            case TEST:
                instance = getMockInstance(clazz, null, DataProviderConfig.INSTANCE.getProviderModel());
                break;
            case PRD:
                instance = getInstance(clazz, null);
                break;
            default:
                instance = getMockInstance(clazz, null, DataProviderConfig.INSTANCE.getProviderModel());
                break;
        }
        return (T) instance;
    }

    /**
     * 获取实体化对象,默认为单类模式
     * @param <T>
     * @param clazz
     * @param parameters
     * @return
     */
    public static <T> T getServiceObject(Class<T> clazz, Object[] parameters) {
        Object instance = null;
        // 测试运行模式
        switch (MotionModel.getModel()) {
            case TEST:
                instance = getMockInstance(clazz, parameters, DataProviderConfig.INSTANCE.getProviderModel());
                break;
            case PRD:
                instance = getInstance(clazz, parameters);
                break;
            default:
                instance = getMockInstance(clazz, parameters, DataProviderConfig.INSTANCE.getProviderModel());
                break;
        }
        return (T) instance;
    }

    /**
     * 获取实体化对象,默认为单类模式
     * @param <T>
     * @param clazz
     * @param model 数据提供的模式
     * @return
     */
    public static <T> T getServiceObject(Class<T> clazz, DataProviderModel model) {
        Object instance = null;
        // 测试运行模式
        switch (MotionModel.getModel()) {
            case TEST:
                instance = getMockInstance(clazz, null, model);
                break;
            case PRD:
                instance = getInstance(clazz, null);
                break;
            default:
                instance = getMockInstance(clazz, null, model);
                break;
        }
        return (T) instance;
    }

    /**
     * 获取实体化对象,默认为单类模式
     * @param <T>
     * @param clazz
     * @param model 数据提供的模式
     * @return
     */
    public static <T> T getServiceObject(Class<T> clazz, DataProviderModel model, Object[] parameters) {
        Object instance = null;
        // 测试运行模式
        switch (MotionModel.getModel()) {
            case TEST:
                instance = getMockInstance(clazz, parameters, model);
                break;
            case PRD:
                instance = getInstance(clazz, parameters);
                break;
            default:
                instance = getMockInstance(clazz, parameters, model);
                break;
        }
        return (T) instance;
    }

    /**
     * 实际对象的获取
     * @param clazz
     * @return
     */
    private static Object getInstance(Class<?> clazz, Object[] parameters) {
        Object instance = null;
        instance = InstanceCache.getInstace(clazz);
        // 创建实体对象
        if (instance == null) {
            instance = InstanceCreator.instance(clazz, parameters);
            InstanceCache.put(clazz, instance);
        }
        return instance;
    }

    /**
     * MOCK实现对象的获取
     * @param clazz
     * @return
     */
    private static Object getMockInstance(Class<?> clazz, Object[] parameters, DataProviderModel model) {
        Object instance = null;
        instance = InstanceCache.getInstace(clazz);
        // 创建实体对象
        if (instance == null) {
            instance = MockInstanceCreator.instance(clazz, parameters, model);
            InstanceCache.put(clazz, instance);
        }
        return instance;
    }

    /**
     * 获取实体化对象
     * @param <T>
     * @param clazz
     * @param model
     * @return
     */
    public static <T> T getServiceObject(Class<T> clazz, InstanceModel model) {
        Object instance = null;
        // 测试运行模式
        switch (MotionModel.getModel()) {
            case TEST:
                // 单类模式直接返回
                if (InstanceModel.SINGLETON == model) {
                    instance = getMockInstance(clazz, null, DataProviderConfig.INSTANCE.getProviderModel());
                } else {
                    instance = MockInstanceCreator.instance(clazz, DataProviderConfig.INSTANCE.getProviderModel());
                }
                break;
            case PRD:
                // 单类模式直接返回
                if (InstanceModel.SINGLETON == model) {
                    instance = getInstance(clazz, null);
                } else {
                    instance = InstanceCreator.instance(clazz);
                }

                break;
            default:
                // 单类模式直接返回
                if (InstanceModel.SINGLETON == model) {
                    instance = getMockInstance(clazz, null, DataProviderConfig.INSTANCE.getProviderModel());
                } else {
                    instance = MockInstanceCreator.instance(clazz, DataProviderConfig.INSTANCE.getProviderModel());
                }
                break;
        }
        return (T) instance;
    }

    /**
     * 获取实体化对象
     * @param <T>
     * @param clazz
     * @param model
     * @param args
     * @return
     */
    public static <T> T getServiceObject(Class<T> clazz, InstanceModel model, Object[] args) {
        Object instance = null;
        // 测试运行模式
        switch (MotionModel.getModel()) {
            case TEST:
                // 单类模式直接返回
                if (InstanceModel.SINGLETON == model) {
                    instance = getMockInstance(clazz, args, DataProviderConfig.INSTANCE.getProviderModel());
                } else {
                    instance = MockInstanceCreator.instance(clazz, args);
                }
                break;
            case PRD:
                // 单类模式直接返回
                if (InstanceModel.SINGLETON == model) {
                    instance = getInstance(clazz, args);
                } else {
                    instance = InstanceCreator.instance(clazz, args);
                }
            default:
                // 单类模式直接返回
                if (InstanceModel.SINGLETON == model) {
                    instance = getMockInstance(clazz, args, DataProviderConfig.INSTANCE.getProviderModel());
                } else {
                    instance = MockInstanceCreator.instance(clazz, args);
                }
                break;
        }
        return (T) instance;
    }
}
