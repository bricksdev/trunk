package cn.com.byd.utils;

public final class StringUtil {
    public static boolean isEmptyAndNull(String value){
      return value == null || value.trim().equals("");
    }
}
