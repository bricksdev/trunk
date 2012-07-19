package cn.com.byd.modules;

import cn.com.byd.beans.AsnItemBean;
import cn.com.byd.beans.ReceiveRoomBean;
import cn.com.byd.exceptions.WmsException;
import cn.com.byd.imodules.IReceiveRoomModule;

public class ReceiveRoomModule implements IReceiveRoomModule {
	public ReceiveRoomModule() {
		super();
	}
  /**
   *保存收料房信息
   * @param itemBean
   * @return
   * @throws WmsException
   */
	public ReceiveRoomBean saveReceiveRoom(AsnItemBean itemBean) throws WmsException {
		return null;
	}
}
