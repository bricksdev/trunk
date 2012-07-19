package cn.com.byd.iservice.impl;


import cn.com.byd.beans.AsnItemBean;
import cn.com.byd.compose.ModuleComposer;
import cn.com.byd.exceptions.AppExceptin;
import cn.com.byd.exceptions.NoModuleException;
import cn.com.byd.exceptions.ParameterException;
import cn.com.byd.iservice.IReceive;
import cn.com.byd.utils.ConsoleDebugUtil;

import java.util.Collections;
import java.util.List;

public class ReceiveImpl implements IReceive {
	public ReceiveImpl() {
		super();
	}

	/**
	 *获取送货单行项目
	 * @param asnNo
	 * @return
	 */
	public List<AsnItemBean> queryAsnItems(String asnNo) {
		return Collections.emptyList();
	}

	/**
	 *收料操作
	 * @param asnNo
	 * @return
	 */
	public String saveReceive(String asnNo) {

		try {
          ConsoleDebugUtil.INSTANCE.println("parameter=>" + asnNo );
			return (String)ModuleComposer.runModules(ReceiveImpl.class.getName(), "saveReceive",
													 new Object[] { asnNo });
		} catch (AppExceptin e) {
			e.printStackTrace();
		} catch (NoModuleException e) {
			e.printStackTrace();
		} catch (ParameterException e) {
			e.printStackTrace();
		}
		return null;
	}
}
