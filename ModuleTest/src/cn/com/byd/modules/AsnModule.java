package cn.com.byd.modules;


import cn.com.byd.beans.AsnItemBean;
import cn.com.byd.exceptions.WmsException;
import cn.com.byd.imodules.IAsnModule;
import cn.com.byd.utils.ConsoleDebugUtil;

import java.util.Collections;
import java.util.List;

public class AsnModule implements IAsnModule {
	public AsnModule() {
		super();
	}
	/**
	 *获取送货单行项目列表
	 * @param asnNo
	 * @return
	 * @throws WmsException
	 */
	public List<AsnItemBean> queryAsnItems(String asnNo) throws WmsException{
	    ConsoleDebugUtil.INSTANCE.println("query asn items.");
		return Collections.emptyList();
	}
	/**
	 *更新送货单状态
	 * @param asnNo
	 * @throws WmsException
	 */
	public String updateAsnStatus(String asnNo) throws WmsException{
		ConsoleDebugUtil.INSTANCE.println("update asn status.");
    return "WMS52246578965";
	}
}
