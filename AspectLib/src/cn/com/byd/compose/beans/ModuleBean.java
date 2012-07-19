package cn.com.byd.compose.beans;

import java.io.Serializable;

import java.util.List;


public class ModuleBean implements Serializable {
	/**
	 * 参考beanID
	 */
	private String beanId = null;

	private String referenceId = null;

	/**
	 * 方法
	 */
	private String method = null;

	/**
	 * 组件需要的参数
	 */
	private Parameter parameter = null;

	/**
	 * 组件返回的结果
	 */
	private Result returnResult = null;

	/**
	 * 区分是否调用该组件
	 */
	private List<String> divisions = null;

	/**
	 * 区分是否调用的值
	 */
	private String divisionValue = null;


	public void setMethod(String method) {
		this.method = method;
	}

	public String getMethod() {
		return method;
	}

	public void setParameter(Parameter parameter) {
		this.parameter = parameter;
	}

	public Parameter getParameter() {
		return parameter;
	}

	public void setReturnResult(Result returnResult) {
		this.returnResult = returnResult;
	}

	public Result getReturnResult() {
		return returnResult;
	}

	public void setDivisions(List<String> divisions) {
		this.divisions = divisions;
	}

	public List<String> getDivisions() {
		return divisions;
	}

	public void setDivisionValue(String divisionValue) {
		this.divisionValue = divisionValue;
	}

	public String getDivisionValue() {
		return divisionValue;
	}

	public void setBeanId(String referenceId) {
		this.beanId = referenceId;
	}

	public String getBeanId() {
		return beanId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	public String getReferenceId() {
		return referenceId;
	}
}
