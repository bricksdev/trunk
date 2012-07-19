package cn.com.byd.imodules;

import cn.com.byd.beans.ReceiveRoomBean;
import cn.com.byd.exceptions.WmsException;

public interface IIQCModule {
	/**
	 * 保存质检相关信息
	 * @param receiveBean
	 * @return
	 * @throws WmsException
	 */
	public String saveIQCInfo(ReceiveRoomBean receiveBean) throws WmsException;
}
