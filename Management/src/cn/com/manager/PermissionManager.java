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
        permissionChecker.registerPermission(PermissionChecker._PERMISSION_ACTION, new ProgramPermission());
        permissionChecker.registerPermission(PermissionChecker._PERMISSION_SECRET, new SecretPermission());
        // TODO数据库填充
        _USER_PERMISSION_CHECKER.put("1", permissionChecker);

    }

    public static Permission getUserPermission() {
        return getUserPermission("1");

    }

    public static Permission getUserPermission(String userId) {

        return _USER_PERMISSION_CHECKER.get(userId);
    }
}
