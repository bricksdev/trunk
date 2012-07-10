/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.utils;

/**
 *
 * @author kete
 */
public abstract class StringUtil {

    public static boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    /**
     * 连接指定连接符的内容
     * @param boundSymbol
     * @param content
     * @return 
     */
    public static String joinString(String boundSymbol, String... contents) {

        if (contents == null || contents.length == 0) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        int icount = 0;
        for (String content : contents) {
            if(content == null){
                continue;
            }
            if (icount++ > 0) {
                builder.append(boundSymbol);
            }
            builder.append(content);
        }

        return builder.toString();
    }
}
