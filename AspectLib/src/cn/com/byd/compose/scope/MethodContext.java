package cn.com.byd.compose.scope;


import cn.com.byd.compose.beans.ModuleBean;
import cn.com.byd.compose.beans.Parameter;
import cn.com.byd.compose.beans.Result;
import cn.com.byd.compose.modules.EqualGroupModules;
import cn.com.byd.compose.modules.IterateModules;
import cn.com.byd.utils.ObjectPropertyUtil;
import cn.com.byd.utils.StringUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class MethodContext implements Cloneable, Serializable {

	private String id = null;
	private boolean isLog = false;
	private boolean isTransaction = false;
	private boolean isVerify = false;

	/**
	 * 空对象
	 */
	public final static MethodContext NULL = null;


	/**
	 * 上下文中变量 值映射
	 */
	private Map<String, Object> paraMap = new HashMap<String, Object>();

	/**
	 * 分组的组件列表
	 */
	private List<EqualGroupModules> equalGroupModules = new ArrayList<EqualGroupModules>();

	/**
	 * 循环组件列表
	 */
	private IterateModules iteratorModules = IterateModules.NULL;

	/**
	 * 当前直接访问的组件列表
	 */
	private List<ModuleBean> currentModules = new ArrayList<ModuleBean>();

	/**
	 * 方法参数
	 */
	private Parameter parameter = null;

	/**
	 * 返回的结果
	 */
	private Result returnResult = null;

	/**
	 *直接的组件列表
	 * @return
	 */
	public List<ModuleBean> findCurrentModules() {
		return currentModules;
	}

	public IterateModules findIteratorModules() {
		return iteratorModules;
	}

	public List<EqualGroupModules> findConditionGroupModules() {
		return equalGroupModules;
	}

	public void put(String idAndProperty, Object value) {
		paraMap.put(idAndProperty.contains(ObjectPropertyUtil.SPLIT_CHARACTER) ?
					idAndProperty.split(ObjectPropertyUtil.SPLIT_CHARACTER)[0] : idAndProperty, value);
	}

	public void putAll(Map<String, Object> allProperties) {
		for (String key : allProperties.keySet()) {
			this.put(key, allProperties.get(key));
		}
	}

	public void remove(String idAndProperty) {
		paraMap.remove(idAndProperty);
	}

	public Object getIdValue(String idAndProperty) {
		if (StringUtil.isEmptyAndNull(idAndProperty)) {
			return null;
		}
		String[] properties = idAndProperty.split(ObjectPropertyUtil.SPLIT_CHARACTER);
		if (properties.length == 1) {
			return paraMap.get(properties[0]);
		} else {
			Object obj = paraMap.get(properties[0]);
			return ObjectPropertyUtil.getDeepFieldValue(obj, idAndProperty);
		}

	}

	public void setCurrentModules(List<ModuleBean> currentModules) {
		this.currentModules.addAll(currentModules);
	}

	public void setCurrentModules(ModuleBean currentModule) {
		currentModules.add(currentModule);
	}

	public void setIteratorModules(IterateModules module) {
		iteratorModules = module;
	}

	public void setConditionGroupModules(List<EqualGroupModules> groupModules) {
		equalGroupModules.addAll(groupModules);
	}

	public void setConditionGroupModule(EqualGroupModules groupModule) {
		equalGroupModules.add(groupModule);
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

	/**
	 * @return
	 */
	@Override
	protected MethodContext clone() {
		MethodContext cloneObj = MethodContext.NULL;
		ObjectOutputStream objOutput = null;
		ObjectInputStream objInput = null;
		try {
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			objOutput = new ObjectOutputStream(output);
			objOutput.writeObject(this);

			byte[] buffer = output.toByteArray();
			ByteArrayInputStream input = new ByteArrayInputStream(buffer);

			objInput = new ObjectInputStream(input);
			cloneObj = (MethodContext)objInput.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (objOutput != null) {
					objOutput.close();
				}

				if (objOutput != null) {
					objOutput.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				objOutput = null;
				objOutput = null;
			}
		}
		return cloneObj;
	}

	public void setIsLog(boolean isLog) {
		this.isLog = isLog;
	}

	public boolean isLog() {
		return isLog;
	}

	public void setIsTransaction(boolean isTransaction) {
		this.isTransaction = isTransaction;
	}

	public boolean isTransaction() {
		return isTransaction;
	}

	public void setIsVerify(boolean isVerify) {
		this.isVerify = isVerify;
	}

	public boolean isVerify() {
		return isVerify;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}
