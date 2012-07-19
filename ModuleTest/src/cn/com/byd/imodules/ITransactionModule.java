package cn.com.byd.imodules;

import cn.com.byd.beans.TransactionHeader;
import cn.com.byd.beans.TransactionItem;
import cn.com.byd.exceptions.WmsException;

public interface ITransactionModule {

	/**
	 *保存事务信息
	 * @param header
	 * @param item
	 * @return
	 * @throws WmsException
	 */
	String saveTransaction(TransactionHeader header, TransactionItem item) throws WmsException;
}
