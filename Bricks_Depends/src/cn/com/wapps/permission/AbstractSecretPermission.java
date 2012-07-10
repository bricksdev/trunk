/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.wapps.permission;

import cn.com.exceptions.AppException;

/**
 *
 * @author kete
 */
public abstract class AbstractSecretPermission implements Permission {

    @Override
    public void check(Class<?> clazz, String operat) throws AppException {
    }

    @Override
    public void check(String className, String operat) throws AppException {
    }

    @Override
    public boolean validate(String permissionName, String operation) {
        return true;
    }
}
