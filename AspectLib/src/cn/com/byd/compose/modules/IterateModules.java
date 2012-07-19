package cn.com.byd.compose.modules;


import cn.com.byd.compose.beans.ModuleBean;
import cn.com.byd.compose.beans.Status;
import cn.com.byd.compose.utils.Null;

import java.util.List;

public class IterateModules extends Modules {
	/**
	 * 遍历的对象
	 */
	private List<? super Object> items = null;

	/**
	 * 遍历的每个临时参数名
	 */
	private String itemProperty = null;

	/**
	 * 状态
	 */
	private Status status = new Status();

	/**
	 * 分组的组件列表
	 */
	private List<EqualGroupModules> equalGroupModules = null;

	/**
	 * 当前循环下的组件列表
	 */
	private List<ModuleBean> currentModules = null;

	/**
	 * 状态变量名
	 */
	private String statusId = null;

	/**
	 * 空对象
	 */
	public final static NullIteratorModules NULL = new NullIteratorModules();


	public Status getStatus() {
		return status;
	}

	public void setEqualGroupModules(List<EqualGroupModules> equalGroupModules) {
		this.equalGroupModules = equalGroupModules;
	}

	public List<EqualGroupModules> getEqualGroupModules() {
		return equalGroupModules;
	}

	public void setCurrentModules(List<ModuleBean> currentModules) {
		this.currentModules = currentModules;
	}

	public List<ModuleBean> getCurrentModules() {
		return currentModules;
	}

	public void setItems(List<? super Object> items) {
		this.items = items;
	}

	public List<? super Object> getItems() {
		return items;
	}

	public void setItemProperty(String itemProperty) {
		this.itemProperty = itemProperty;
	}

	public String getItemProperty() {
		return itemProperty;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	public String getStatusId() {
		return statusId;
	}

	private static class NullIteratorModules extends IterateModules implements Null {
		private NullIteratorModules() {
			super();
		}
	}
}

