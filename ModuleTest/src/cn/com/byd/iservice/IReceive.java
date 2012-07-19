package cn.com.byd.iservice;


import cn.com.byd.beans.AsnItemBean;
import cn.com.byd.exceptions.WmsException;

import java.util.List;

public interface IReceive {

    /**
     * @param asnNo
     * @return
     * @throws WmsException
     */
    List<AsnItemBean> queryAsnItems(String asnNo) throws WmsException;

    String saveReceive(String asnNo) throws WmsException;
}
