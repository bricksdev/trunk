/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.manager.permission.checker;

import cn.com.exceptions.AppException;
import cn.com.wapps.permission.Permission;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kete
 */
public class PermissionChecker implements Permission {

    private final Map<String, Permission> __PERMISSION_LIST = new HashMap<String, Permission>(3);
    private Permission currentPermission = null;

    public PermissionChecker() {
    }

    public void registerPermission(String permissionName, Permission permission) {
        __PERMISSION_LIST.put(permissionName, permission);
    }

    public void cancelPermission(String permissionName) {
        __PERMISSION_LIST.remove(permissionName);
    }

    @Override
    public void check(String className, String operat) throws AppException {


        if (!validate(className, operat)) {
            throw new AppException("0001", "No permission current operation -" + operat);
        }

    }

    @Override
    public boolean validate(String className, String operat) {


        boolean ifpermission = false;

//        // 如果没有设定权限，将不允许操作
//        if (__PERMISSION_LIST != null) {
//            for (Permission p : ) {
//                ifpermission = p.validate(className, operat);
//            }
//        }
        ifpermission = __PERMISSION_LIST.get("action").validate(className, operat);

        return ifpermission;

    }

    @Override
    public void check(Class<?> clazz, String operat) throws AppException {

        check(clazz.getName(), operat);
    }

    public Permission getCurrentPermission() {

        return currentPermission;
    }

    @Override
    public boolean validateSecret(String elementid) {

        return __PERMISSION_LIST.get("secret").validateSecret(elementid);

    }
}
