/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.exceptions;

/**
 *
 * @author kete
 */
public class AppException extends Exception {

    private String code = null;

    /**
     * Creates a new instance of <code>AppException</code> without detail message.
     */
    private AppException() {
    }

    /**
     * Constructs an instance of <code>AppException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public AppException(String code, String msg) {
        super(msg);
        this.code = code;
    }

    @Override
    public String getMessage() {
        StringBuilder message = new StringBuilder(code).append(":").append(super.getMessage());
        return message.toString();
    }
}
