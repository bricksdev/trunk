package cn.com.byd.factory.impl;

import cn.com.byd.factory.ProxyFactory;
import cn.com.byd.proxy.iproxy.IProxy;
import cn.com.byd.proxy.proxyimpl.ProxyImpl;

public class DefaultProxyFactory implements ProxyFactory {
    public DefaultProxyFactory() {
    }

    public IProxy getProxy(String devision) {
        return new ProxyImpl();
    }

    public IProxy getProxy() {
        return new ProxyImpl();
    }
}
