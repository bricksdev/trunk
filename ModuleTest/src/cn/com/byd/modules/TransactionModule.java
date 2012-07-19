package cn.com.byd.modules;

import cn.com.byd.beans.TransactionHeader;
import cn.com.byd.beans.TransactionItem;
import cn.com.byd.exceptions.WmsException;
import cn.com.byd.imodules.ITransactionModule;

public class TransactionModule implements ITransactionModule {
	public TransactionModule() {
		super();
	}
  /**
   *保存事务信息
   * @param header
   * @param item
   * @return
   * @throws WmsException
   */
	public String saveTransaction(TransactionHeader header, TransactionItem item) throws WmsException {
		return "WMS:544875665";
	}
}
