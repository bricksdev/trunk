/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.bricks.skiplicense.weaving;

/**
 *
 * @author kete
 */
public interface ClassTransformer {

    /**
     * 转换字节流
     *
     * @param result
     * @param className
     * @return
     */
    byte[] transformBytes(byte[] result, String className);
    
}
