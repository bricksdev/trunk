package cn.com.byd.proxy.handler.impl;

import cn.com.byd.exceptions.AuthException;
import cn.com.byd.proxy.handler.Authenticator;
import cn.com.byd.utils.ConsoleDebugUtil;

public final class DefaultAuthenticator implements Authenticator {
    public void checkMethodAuth(String methodName, String targetClass) throws AuthException {
        //      if (methodName.equals("saveReceive")) {
        //          throw new AuthException("Don't operat the " + methodName);
        //      }
        ConsoleDebugUtil.INSTANCE.println("check method Authenticator.");
    }

}
