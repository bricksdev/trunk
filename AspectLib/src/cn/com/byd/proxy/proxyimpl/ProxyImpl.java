package cn.com.byd.proxy.proxyimpl;


import cn.com.byd.proxy.handler.InterceptionHandler;
import cn.com.byd.proxy.iproxy.IProxy;

import java.lang.reflect.Proxy;


/**
 * 代理实现类
 * @author jianghongquan
 * @version v1.0
 */
public class ProxyImpl implements IProxy {
	public ProxyImpl() {
		super();
	}

	/**
	 * 获取接口的实现类
	 * @param beanID
   * @param obj 实体
	 * @return
	 */
	public Object findInterface(String beanID, Object obj) {
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(),
									  new InterceptionHandler(obj, beanID));
	}


}
