package cn.com.byd.factory;

import cn.com.byd.proxy.handler.Authenticator;

public interface AuthenticationFactory {
    /**
     *获取操作权限验证器
     * @return
     */
    Authenticator getAutoentication();
}
