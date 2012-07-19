/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.national;

import cn.com.codes.BricksMessagesCodes;
import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 *
 * @author kete
 */
public class BricksNationality {

    private static final String START_SPLIT_CHAR = "[${]";
    private static final String END_SPLIT_CHAR = "}";
    private static final String _MESSAGES_HTML_MESSAGES = "messages/htmlmessages";
    private static final String _MESSAGES_MESSAGE = "messages/message";
    private static final String _PREFIX = "{";

    /**
     * 格式化
     * @param code
     * @param args
     * @return
     */
    public static String format(String code, Object... args) {
        ResourceBundle _BUNDLE = ResourceBundle.getBundle(_MESSAGES_MESSAGE, CurrentNationalLanguange.getLocale());
        try {
            return MessageFormat.format(findPatten(_BUNDLE.getString(code)), args);
        } catch (MissingResourceException ex) {
            return MessageFormat.format(findPatten(_BUNDLE.getString(BricksMessagesCodes._E00000)), code);
        }
    }

    /**
     * 将对应的格式变成java能处理的字符
     * @param pattern
     * @return
     */
    private static String findPatten(String pattern) {
        String[] splits = pattern.split(START_SPLIT_CHAR);
        StringBuilder builder = new StringBuilder();
        int idx = 0;
        int icnt = 0;
        for (String t : splits) {
            icnt = t.indexOf(END_SPLIT_CHAR);
            if (icnt >= 0) {
                builder.append(_PREFIX);
                builder.append(idx++);
                builder.append(t.substring(icnt));
            } else {
                builder.append(t);

            }
        }

        return builder.toString();
    }

    /**
     * 获取国际化字段内容
     * @param key
     * @return
     */
    public static String getValue(String key) {
        ResourceBundle _BUNDLE = ResourceBundle.getBundle(_MESSAGES_HTML_MESSAGES, CurrentNationalLanguange.getLocale());
        try {
            return _BUNDLE.getString(key);
        } catch (MissingResourceException ex) {
        }
        return key;
    }
}
