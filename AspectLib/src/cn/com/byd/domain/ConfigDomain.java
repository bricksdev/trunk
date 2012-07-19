package cn.com.byd.domain;

public class ConfigDomain {

	private boolean isProxyed = false;
	private boolean isSingle = false;
	private String clazzName = null;

	public void setProxyed(boolean isProxyed) {
		this.isProxyed = isProxyed;
	}

	public boolean isProxyed() {
		return isProxyed;
	}

	public void setSingle(boolean isSingle) {
		this.isSingle = isSingle;
	}

	public boolean isSingle() {
		return isSingle;
	}


	public void setClazzName(String clazzName) {
		this.clazzName = clazzName;
	}

	public String getClazzName() {
		return clazzName;
	}
}
