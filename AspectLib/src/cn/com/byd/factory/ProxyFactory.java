package cn.com.byd.factory;


import cn.com.byd.proxy.iproxy.IProxy;

public interface ProxyFactory {
    public IProxy getProxy(String devision);

    public IProxy getProxy();
}
