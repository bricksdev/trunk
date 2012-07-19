package cn.com.byd.imodules;

import cn.com.byd.beans.AsnItemBean;
import cn.com.byd.beans.ReceiveRoomBean;
import cn.com.byd.exceptions.WmsException;

public interface IReceiveRoomModule {
	/**
	 *保存收料房信息
	 * @param itemBean
	 * @return
	 * @throws WmsException
	 */
	ReceiveRoomBean saveReceiveRoom(AsnItemBean itemBean) throws WmsException;
	
}
