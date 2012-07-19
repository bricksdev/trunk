package cn.com.byd.compose.source;


import cn.com.byd.utils.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum ModuleSource {
	INSTANCE;

	/**
	 * 类与方法对应列表Map<beanID,Map<methodName, MethodContext>>
	 */
	private final static Map<String, List<ModuleMethodSource>> CLASS_AND_METHOD_MAP =
		   new ConcurrentHashMap<String, List<ModuleMethodSource>>();

	/**
	 * ID对应列表Map<ID,ModuleMethodSource>
	 */
	private final static Map<String, ModuleMethodSource> ID_MAP = new ConcurrentHashMap<String, ModuleMethodSource>();

	public void setClassAndMethodMap(Map<String, List<ModuleMethodSource>> classAndMethodMap) {
		if (classAndMethodMap == null) {
			return;
		}
		for (String beanID : classAndMethodMap.keySet()) {
			this.setClassAndMethodList(beanID, classAndMethodMap.get(beanID));
		}
	}

	public void setClassAndMethodList(String beanID, List<ModuleMethodSource> classAndMethods) {
		List<ModuleMethodSource> methods = CLASS_AND_METHOD_MAP.get(beanID);
		if (methods == null || methods.isEmpty()) {
			methods = new ArrayList<ModuleMethodSource>();
			CLASS_AND_METHOD_MAP.put(beanID, methods);
		}
		if (classAndMethods == null) {
			return;
		}
		for (ModuleMethodSource source : classAndMethods) {
			this.setClassAndMethodList(beanID, source);
		}
	}

	public void setClassAndMethodList(String beanID, ModuleMethodSource classAndMethod) {
		List<ModuleMethodSource> methods = CLASS_AND_METHOD_MAP.get(beanID);
		if (methods == null || methods.isEmpty()) {
			methods = new ArrayList<ModuleMethodSource>();
			CLASS_AND_METHOD_MAP.put(beanID, methods);
		}

		if (classAndMethod == null) {
			return;
		}

		if (!StringUtil.isEmptyAndNull(classAndMethod.getId())) {
			ID_MAP.put(classAndMethod.getId(), classAndMethod);
		}
		methods.add(classAndMethod);

	}

	public List<ModuleMethodSource> findMethods(String beanID) {
		if (CLASS_AND_METHOD_MAP == null || CLASS_AND_METHOD_MAP.isEmpty()) {
			return Collections.emptyList();
		}
		if (CLASS_AND_METHOD_MAP.containsKey(beanID)) {
			return CLASS_AND_METHOD_MAP.get(beanID);
		}
		return Collections.emptyList();
	}

	public ModuleMethodSource findMethodsByPrimaryKey(String id) {
		if (ID_MAP == null || ID_MAP.isEmpty()) {
			return null;
		}
		if (ID_MAP.containsKey(id)) {
			return ID_MAP.get(id);
		}
		return null;
	}
}
