package cn.com.byd.compose.modules;


import cn.com.byd.compose.beans.ModuleBean;

import java.util.List;

public class EqualGroupModules extends Modules {
	/**
	 * 比较属性
	 */
	private String compareValue = null;

	/**
	 * 条件组件列表
	 */
	private List<ModuleBean> groupModules = null;

	/**
	 * 遍历的组件
	 */
	private IterateModules iteratorModules = null;

	public void setIteratorModules(IterateModules iteratorModules) {
		this.iteratorModules = iteratorModules;
	}

	public IterateModules getIteratorModules() {
		return iteratorModules;
	}

	public void setGroupModules(List<ModuleBean> groupModules) {
		this.groupModules = groupModules;
	}

	public List<ModuleBean> getGroupModules() {
		return groupModules;
	}

	public void setCompareValue(String compareValue) {
		this.compareValue = compareValue;
	}

	public String getCompareValue() {
		return compareValue;
	}
}
