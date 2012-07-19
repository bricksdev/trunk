/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.wrapper;

/**
 * 自动封装处理类
 * @author kete
 */
public interface DataWrapper {

     <T> T execute(Class<T> clazz);
}
