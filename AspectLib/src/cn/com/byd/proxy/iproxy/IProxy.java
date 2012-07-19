package cn.com.byd.proxy.iproxy;

public interface IProxy {

	/**
	 * 获取接口的实现类
	 * @param beanID
	 * @param obj 实体
	 * @return
	 */
	public Object findInterface(String beanID, Object obj);
}
