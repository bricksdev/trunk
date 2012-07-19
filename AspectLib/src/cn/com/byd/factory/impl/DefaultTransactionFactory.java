package cn.com.byd.factory.impl;

import cn.com.byd.factory.TransactionFactory;
import cn.com.byd.proxy.handler.Transaction;
import cn.com.byd.proxy.handler.impl.DefaultTransaction;

public class DefaultTransactionFactory implements TransactionFactory {
    public DefaultTransactionFactory() {
    }

    public Transaction getTransaction() {
        return new DefaultTransaction();
    }
}
