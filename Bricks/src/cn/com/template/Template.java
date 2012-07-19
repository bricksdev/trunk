/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.template;

import cn.com.exceptions.AppException;

/**
 *
 * @author kete
 */
public interface Template {
    String _SPLITER_CHAE = "_";
    String content() throws AppException;
}
