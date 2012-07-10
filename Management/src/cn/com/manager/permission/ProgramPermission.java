/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.manager.permission;

import cn.com.wapps.permission.AbstractPermission;

/**
 *
 * @author kete
 */
public class ProgramPermission extends AbstractPermission {

    @Override
    public boolean validate(String permissionName, String operation) {

        return true;
    }
}
