package cn.com.byd.proxy.handler;


/**
 * 事务处理类
 * @author jianghongquan
 * @version v1.0
 */
public interface Transaction {

    /**
     * 开始事务
     */
    public void begin();

    /**
     * 结束事务
     */
    public void end();

    /**
     * 提交事务
     */
    public void commit();

    /**
     * 回滚事务
     */
    public void rollback();
}
