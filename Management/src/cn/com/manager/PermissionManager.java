/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.manager;

import cn.com.exceptions.AppException;
import cn.com.manager.permission.ProgramPermission;
import cn.com.manager.permission.SecretPermission;
import cn.com.manager.permission.checker.PermissionChecker;
import cn.com.wapps.permission.Permission;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author kete
 */
public class PermissionManager {

    private final static Map<String, Permission> _USER_PERMISSION_CHECKER = new ConcurrentHashMap<String, Permission>(35);

    static {
        resetPermission();
    }

    public static void resetPermission() {
        PermissionChecker permissionChecker = new PermissionChecker();
        permissionChecker.registerPermission("action", new ProgramPermission());
        permissionChecker.registerPermission("secret", new SecretPermission());
        _USER_PERMISSION_CHECKER.put("1", permissionChecker);

    }

    public static void check(Class<?> clazz, String operat) throws AppException {
        check(clazz.getName(), operat);
    }

    public static void check(String className, String operat) throws AppException {

        getUserPermission().check(className, operat);

    }

    public static Permission getUserPermission() {
        return getUserPermission("1");

    }

    public static Permission getUserPermission(String userId) {

        return _USER_PERMISSION_CHECKER.get(userId);
    }
}
