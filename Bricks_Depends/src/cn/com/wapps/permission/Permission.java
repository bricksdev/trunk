/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.wapps.permission;

import cn.com.exceptions.AppException;

/**
 * 权限验证接口
 * @author kete
 */
public interface Permission {
    String _PERMISSION_ACTION = "action";
    String _PERMISSION_SECRET = "secret";

    /**
     * 验证是否具有操作权限
     * @param permissionName
     * @param operation
     * @return
     * @throws AppException
     */
    boolean validate(String permissionName, String operation) throws AppException;

    /**
     * 验证是否需要隐藏显示内容
     * @param elementid
     * @return
     * @throws AppException
     */
    boolean validateSecret(String elementid) throws AppException;

    /**
     * 提供给form进行验证是否具有操作权限
     * @param clazz
     * @param operat
     * @throws AppException
     */
    void check(Class<?> clazz, String operat) throws AppException;

    /**
     * 提供给form进行验证是否具有操作权限
     * @param className
     * @param operat
     * @throws AppException
     */
    void check(String className, String operat) throws AppException;
}
