package cn.com.byd.support.impl;

import cn.com.byd.support.ObjectCreator;
import cn.com.byd.utils.RefObjectUtil;

public class DefaultObjectCreator implements ObjectCreator {
    public DefaultObjectCreator() {
    }

    public Object findObject(String className) {
        return RefObjectUtil.loadClass(className);
    }
}
