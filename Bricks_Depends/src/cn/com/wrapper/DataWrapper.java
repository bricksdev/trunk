/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.wrapper;

/**
 *
 * @author kete
 */
public interface DataWrapper {

     <T> T execute(Class<T> clazz);
}
