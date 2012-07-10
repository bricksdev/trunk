/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.manager.permission;

import cn.com.wapps.permission.AbstractSecretPermission;

/**
 *
 * @author kete
 */
public class SecretPermission extends  AbstractSecretPermission {

    @Override
    public boolean validateSecret(String elementid) {
        return true;
    }

}
