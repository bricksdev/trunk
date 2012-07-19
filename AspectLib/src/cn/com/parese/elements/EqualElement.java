package cn.com.parese.elements;


import cn.com.byd.compose.beans.ModuleBean;
import cn.com.byd.compose.modules.EqualGroupModules;
import cn.com.byd.compose.modules.IterateModules;
import cn.com.parese.element.BaseElement;
import cn.com.parese.element.ElementFactory;
import cn.com.parese.exception.PareseException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Element;


public class EqualElement implements BaseElement {

	private final static String ATTRIBUTE_PROPERTY = "property";
	private final static String ATTRIBUTE_VALUE = "compareValue";
	private final static String ELEMENT_NAME_MODULE = "module";
	private final static String ELEMENT_NAME_ITERATOR = "iterator";
	private EqualGroupModules equalModule = null;

	public EqualElement() {
		super();
	}

	public EqualElement(EqualGroupModules equalModule) {
		super();
		this.equalModule = equalModule;
	}

	public void doParese(Element element) throws PareseException {
		Attribute attributeProperty = element.attribute(ATTRIBUTE_PROPERTY);
		Attribute attributeValue = element.attribute(ATTRIBUTE_VALUE);

		if (attributeProperty == null) {
			throw new PareseException("Not found necessary attribute." + attributeProperty);
		}

		if (attributeValue == null) {
			throw new PareseException("Not found necessary attribute." + attributeValue);
		}

		// 解析xml内部组件
		Iterator<Element> it = element.elementIterator();
		Element subElement = null;
		while (it.hasNext()) {
			subElement = it.next();
			Object[] args = new Object[] { };
			if (ELEMENT_NAME_MODULE.equals(subElement.getName())) {
				ModuleBean module = new ModuleBean();
				List<ModuleBean> modules = equalModule.getGroupModules();
				if (modules == null) {
					modules = new ArrayList<ModuleBean>();
					equalModule.setGroupModules(modules);
				}
				modules.add(module);
				args = new Object[] { module };
			} else if (ELEMENT_NAME_ITERATOR.equals(subElement.getName())) {
				IterateModules modules = new IterateModules();
				equalModule.setIteratorModules(modules);
				args = new Object[] { modules };
			}
			ElementFactory.findElement(subElement.getName(), args).doParese(subElement);

		}
	}
}
