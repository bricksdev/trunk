package cn.com.byd.imodules;


import cn.com.byd.beans.AsnItemBean;
import cn.com.byd.exceptions.WmsException;

import java.util.List;

public interface IAsnModule {
	/**
	 * 获取送货单行项目列表
	 * @param asnNo
	 * @return
	 * @throws WmsException
	 */
	public List<AsnItemBean> queryAsnItems(String asnNo) throws WmsException;

	/**
	 * 更新送货单状态
	 * @param asnNo
	 * @throws WmsException
	 */
	public String updateAsnStatus(String asnNo) throws WmsException;
}
