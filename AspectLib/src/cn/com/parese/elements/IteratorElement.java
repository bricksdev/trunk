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


public class IteratorElement implements BaseElement {

	private final static String ATTRIBUTE_PROPERTY = "property";
	private final static String ATTRIBUTE_ITEMS = "items";
	private final static String ATTRIBUTE_STATUS = "status";
	private final static String ELEMENT_NAME_MODULE = "module";
	private final static String ELEMENT_NAME_NOT_EQUAL = "isNotEqual";
	private final static String ELEMENT_NAME_EQUAL = "isEqual";
	private IterateModules iteratorModule = null;

	public IteratorElement() {
		super();
	}

	public IteratorElement(IterateModules iteratorModule) {
		super();
		this.iteratorModule = iteratorModule;
	}

	public void doParese(Element element) throws PareseException {
		Attribute attributeProperty = element.attribute(ATTRIBUTE_PROPERTY);
		Attribute attributeItems = element.attribute(ATTRIBUTE_ITEMS);
		if (attributeProperty == null) {
			throw new PareseException("Not found necessary attribute." + attributeProperty);
		}
		if (attributeItems == null) {
			throw new PareseException("Not found necessary attribute." + attributeItems);
		}

		Attribute attributeStatus = element.attribute(ATTRIBUTE_STATUS);

		iteratorModule.setItemProperty(attributeItems.getStringValue());
		iteratorModule.setProperty(attributeProperty.getStringValue());
		iteratorModule.setStatusId(attributeStatus == null ? null : attributeStatus.getStringValue());
		// 解析xml内部组件
		Iterator<Element> it = element.elementIterator();
		Element subElement = null;
		while (it.hasNext()) {
			subElement = it.next();
			Object[] args = new Object[] { };
			if (ELEMENT_NAME_MODULE.equals(subElement.getName())) {
				ModuleBean module = new ModuleBean();
				List<ModuleBean> modules = iteratorModule.getCurrentModules();
				if (modules == null) {
					modules = new ArrayList<ModuleBean>();
					iteratorModule.setCurrentModules(modules);
				}
				modules.add(module);
				args = new Object[] { module };
			} else if (ELEMENT_NAME_NOT_EQUAL.equals(subElement.getName())) {
				EqualGroupModules module = new EqualGroupModules();
				List<EqualGroupModules> modules = iteratorModule.getEqualGroupModules();
				if (modules == null) {
					modules = new ArrayList<EqualGroupModules>();
					iteratorModule.setEqualGroupModules(modules);
				}
				args = new Object[] { module };
			} else if (ELEMENT_NAME_EQUAL.equals(subElement.getName())) {
				EqualGroupModules module = new EqualGroupModules();
				List<EqualGroupModules> modules = iteratorModule.getEqualGroupModules();
				if (modules == null) {
					modules = new ArrayList<EqualGroupModules>();
					iteratorModule.setEqualGroupModules(modules);
				}
				args = new Object[] { module };
			}
			ElementFactory.findElement(subElement.getName(), args).doParese(subElement);

		}
	}
}
