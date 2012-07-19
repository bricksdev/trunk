package cn.com.byd.factory;

import cn.com.byd.support.ObjectCreator;

public interface InstanceFactory {

    /**
     *获取实类创建者
     * @return
     */
    ObjectCreator getCreator();
}
