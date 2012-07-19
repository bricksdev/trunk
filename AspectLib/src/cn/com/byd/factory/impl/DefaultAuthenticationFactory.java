package cn.com.byd.factory.impl;

import cn.com.byd.factory.AuthenticationFactory;
import cn.com.byd.proxy.handler.Authenticator;
import cn.com.byd.proxy.handler.impl.DefaultAuthenticator;

public class DefaultAuthenticationFactory implements AuthenticationFactory {
    public DefaultAuthenticationFactory() {
    }

    public Authenticator getAutoentication() {
        return new DefaultAuthenticator();
    }
}
