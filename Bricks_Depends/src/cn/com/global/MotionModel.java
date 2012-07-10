/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.global;

/**
 *
 * @author kete
 */
public class MotionModel {

    private static Model model = Model.TEST;

    /**
     * 获取当前运行模式
     * @return
     */
    public static Model getModel() {

        return model;
    }
    /**
     * 设定运行模式
     * @param m
     */
    public static void setModel(Model m){
        model = m;
    }
}
