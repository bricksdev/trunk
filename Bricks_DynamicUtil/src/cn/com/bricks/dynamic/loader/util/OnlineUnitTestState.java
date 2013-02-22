/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.bricks.dynamic.loader.util;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 是否在线测试状态设定
 *
 * @author kete
 */
public abstract class OnlineUnitTestState {

    /**
     * 线程级变量
     */
    private final static ThreadLocal<AtomicBoolean> _IS_TEST = new ThreadLocal<AtomicBoolean>() {
        @Override
        protected AtomicBoolean initialValue() {
            return new AtomicBoolean();
        }
    };

    /**
     * 判断是否当前线程为测试线程
     *
     * @return
     */
    public static boolean isTestState() {
        return _IS_TEST.get().get();
    }

    /**
     * 设定当前线程为测试线程
     *
     * @param isTesting
     */
    public static void setTestState(boolean isTesting) {
        _IS_TEST.get().set(isTesting);
    }
}
