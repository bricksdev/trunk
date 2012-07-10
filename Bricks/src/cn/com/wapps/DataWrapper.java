/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.wapps;

/**
 *
 * @author kete
 */
public interface DataWrapper {

     <T> T execute(Class<T> clazz);
}
