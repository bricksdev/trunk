/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.exceptions;

import cn.com.national.BricksNationality;

/**
 *
 * @author kete
 */
public class AppException extends Exception {

    private static final String SPLIT_CHAR = ":";
    private String code = null;
    private Throwable exception = null;
    private Object[] parametes = null;

    private AppException() {
    }

    public AppException(String code) {
        this.code = code;
    }

    public AppException(String code, Object... args) {
        this.code = code;
        this.parametes = args;
    }

    public AppException(String code, Throwable ex, Object... args) {
        this(code, args);
        this.exception = ex;
    }

//    public AppException(String code, String msg, Object... args) {
//        super(msg);
//        this.parametes = args;
//        this.code = code;
//    }
//
//    public AppException(String code, String msg, Throwable ex, Object... args) {
//        this(code, msg, args);
//        this.exception = ex;
//    }
    @Override
    public String getMessage() {
        StringBuilder message = new StringBuilder(code).append(SPLIT_CHAR).append(BricksNationality.format(code, parametes));
        return message.toString();
    }
}
