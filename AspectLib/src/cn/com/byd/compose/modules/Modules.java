package cn.com.byd.compose.modules;

import java.io.Serializable;

public abstract class Modules implements Serializable {
  /**
   * 属性ID
   */
  private String property = null;

	public void setProperty(String property) {
		this.property = property;
	}

	public String getProperty() {
		return property;
	}
}
