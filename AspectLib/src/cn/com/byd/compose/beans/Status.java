package cn.com.byd.compose.beans;

import java.io.Serializable;

public class Status implements Serializable {
	/**
	 * 索引
	 */
	private int index = 0;

	/**
	 * 总数
	 */
	private int total = 0;


	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getTotal() {
		return total;
	}

}
