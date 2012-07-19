package cn.com.byd.modules;

import cn.com.byd.beans.ReceiveRoomBean;
import cn.com.byd.exceptions.WmsException;
import cn.com.byd.imodules.IIQCModule;
import cn.com.byd.utils.ConsoleDebugUtil;

public class IQCModule implements IIQCModule {
	public IQCModule() {
		super();
	}
	/**
	 * 保存质检相关信息
	 * @param receiveBean
	 * @return
	 * @throws WmsException
	 */
	public String saveIQCInfo(ReceiveRoomBean receiveBean) throws WmsException{
	    ConsoleDebugUtil.INSTANCE.println("save iqc information.");
		return "IQC:10001154";
	}
}
