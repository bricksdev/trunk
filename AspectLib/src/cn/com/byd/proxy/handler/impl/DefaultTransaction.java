package cn.com.byd.proxy.handler.impl;

import cn.com.byd.proxy.handler.Transaction;
import cn.com.byd.utils.ConsoleDebugUtil;

public class DefaultTransaction implements Transaction {
    /**
     * 开始事务
     */
    public void begin() {
        ConsoleDebugUtil.INSTANCE.println("begin transaction.");
    }

    /**
     * 结束事务
     */
    public void end() {
        ConsoleDebugUtil.INSTANCE.println("end transaction.");

    }

    /**
     * 提交事务
     */
    public void commit() {
        ConsoleDebugUtil.INSTANCE.println("commit transaction.");
    }

    /**
     * 回滚事务
     */
    public void rollback() {
        ConsoleDebugUtil.INSTANCE.println("rollback transaction.");

    }
}
