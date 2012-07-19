package cn.com.byd.compose.beans;

import java.io.Serializable;

public class Append implements Serializable {
	/**
	 * 预追加的内容
	 */
	private String prepend = null;

	/**
	 * 判定属性
	 */
	private String property = null;

	/**
	 * 是否空追加 默认为true
	 */
	private boolean emptyAppend = true;

	public void setPrepend(String prepend) {
		this.prepend = prepend;
	}

	public String getPrepend() {
		return prepend;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getProperty() {
		return property;
	}

	public void setEmptyAppend(boolean emptyAppend) {
		this.emptyAppend = emptyAppend;
	}

	public boolean isEmptyAppend() {
		return emptyAppend;
	}
}
