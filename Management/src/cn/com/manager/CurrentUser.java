/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.manager;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kete
 */
public class CurrentUser {

    public final static String _SESSION = "edu.yale.its.tp.cas.client.filter.user";
    private final static ThreadLocal<String> _USER = new ThreadLocal<String>();

    public static void setUserId(String userId) {
        _USER.set(userId);
    }

    public static String getUserID() {
        return _USER.get();
    }

    public static List<String> getEffectNodes() {
        return new ArrayList<String>() {

            {
                add("receipt");
                add("iqc");
            }
        };
    }
}
