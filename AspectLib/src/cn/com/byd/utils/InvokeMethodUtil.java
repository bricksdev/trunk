package cn.com.byd.utils;


import cn.com.byd.exceptions.AppExceptin;
import cn.com.byd.exceptions.NoModuleException;
import cn.com.byd.factory.builder.FactoryBuilder;
import cn.com.byd.support.ILogger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InvokeMethodUtil {
    private final static ILogger log =
        FactoryBuilder.getLoggerFactory().getLogger(InvokeMethodUtil.class);

    public InvokeMethodUtil() {
        super();
    }

    public static Object invoke(Object obj, String methodName,
                                Object... args) throws NoModuleException,
                                                       AppExceptin {
        Method method = null;
        try {
            method =
                    ObjectPropertyUtil.findMethod(obj.getClass(), methodName, args);
            return method.invoke(obj, args);
        } catch (NoSuchMethodException e) {
            log.error(e);
            throw new NoModuleException(e);
        } catch (IllegalAccessException e) {
            log.error(e);
            throw new AppExceptin(e);
        } catch (InvocationTargetException e) {
            log.error(e);
            throw new AppExceptin(e);
        }
    }
}
