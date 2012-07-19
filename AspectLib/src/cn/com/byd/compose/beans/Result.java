package cn.com.byd.compose.beans;


import cn.com.byd.compose.modules.IterateModules;

import java.io.Serializable;

import java.util.List;


public class Result  implements Serializable{
	/**
	 * 结果ID
	 */
	private String id = null;

	/**
	 * 遍历的组件
	 */
	private IterateModules iteratorModules = null;

	/**
	 * 返回的类型
	 */
	private String retultType = null;

	/**
	 * 类
	 */
	private Class clazz = null;

	/**
	 * 字符类型 追加
	 */
	private List<Append> appends = null;
  /**
   * 参照属性
   */
  private String refereceValue = null;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setIteratorModules(IterateModules iteratorModules) {
		this.iteratorModules = iteratorModules;
	}

	public IterateModules getIteratorModules() {
		return iteratorModules;
	}

	public void setRetultType(String retultType) {
		this.retultType = retultType;
	}

	public String getRetultType() {
		return retultType;
	}

	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}

	public Class getClazz() {
		return clazz;
	}

	public void setAppends(List<Append> appends) {
		this.appends = appends;
	}

	public List<Append> getAppends() {
		return appends;
	}

	public void setRefereceValue(String refereceValue) {
		this.refereceValue = refereceValue;
	}

	public String getRefereceValue() {
		return refereceValue;
	}
}
