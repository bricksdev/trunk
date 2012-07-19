package cn.com.byd.proxy.handler;


import cn.com.byd.exceptions.AuthException;

/**
 * 方法权限验证类.
 * @author jianghongquan
 * @version v1.0
 * @since 2011-1-27
 */
public interface Authenticator {

    /**
     *验证当前目标类 是否具有操作的权限
     * @param methodName
     * @param targetClass
     * @throws AuthException
     */
    public void checkMethodAuth(String methodName, String targetClass) throws AuthException;
}
