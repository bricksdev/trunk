/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.national;

import java.util.Locale;

/**
 *
 * @author kete
 */
public class CurrentNationalLanguange {

    private final static ThreadLocal<Language> _LANGUAGE = new ThreadLocal<Language>();

    public static void setLanguage(Language lan) {
        _LANGUAGE.set(lan);
    }

    public static Locale getLocale() {
        return _LANGUAGE.get() == null ? Locale.getDefault() : _LANGUAGE.get().getLanguage();
    }
}
