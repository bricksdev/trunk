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
public class Language {

    private String language;

    public Language(String language) {
        this.language = language;
    }

    public Locale getLanguage() {
        return new Locale(language);
    }
}
