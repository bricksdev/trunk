package cn.com.byd.factory;

import cn.com.byd.proxy.handler.Transaction;

public interface TransactionFactory {

    /**
     * 获取事务
     * @return
     */
    Transaction getTransaction();
}
