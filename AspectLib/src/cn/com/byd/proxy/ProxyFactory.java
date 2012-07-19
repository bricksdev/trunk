package cn.com.byd.proxy;


import cn.com.byd.proxy.iproxy.IProxy;
import cn.com.byd.proxy.proxyimpl.ProxyImpl;

public enum ProxyFactory {
    INSTANCE;
    public IProxy getProxy(){
        return new ProxyImpl();
    }
}
