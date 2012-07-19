/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.manager.permission;

import cn.com.wapps.permission.AbstractSecretPermission;

/**
 * 保密权限，设定将内容进行隐藏或显示
 * @author kete
 */
public class SecretPermission extends AbstractSecretPermission {

    @Override
    public boolean validateSecret(String elementid) {
        return true;
    }
}
