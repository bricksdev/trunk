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
public interface Permission {

    boolean validate(String permissionName, String operation);

    void check(Class<?> clazz, String operat) throws AppException;

    void check(String className, String operat) throws AppException;

    boolean validateSecret(String elementid);
}
