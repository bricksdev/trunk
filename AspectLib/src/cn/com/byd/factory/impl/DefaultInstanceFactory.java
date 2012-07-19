package cn.com.byd.factory.impl;

import cn.com.byd.factory.InstanceFactory;
import cn.com.byd.support.ObjectCreator;
import cn.com.byd.support.impl.DefaultObjectCreator;

public class DefaultInstanceFactory implements InstanceFactory {
    public DefaultInstanceFactory() {
    }

    public ObjectCreator getCreator() {
        return new DefaultObjectCreator();
    }
}
