package cn.com.parese.elements;


import cn.com.byd.compose.beans.ModuleBean;
import cn.com.byd.compose.beans.Parameter;
import cn.com.byd.compose.beans.Result;
import cn.com.parese.element.BaseElement;
import cn.com.parese.element.ElementFactory;
import cn.com.parese.exception.PareseException;

import java.util.Iterator;

import org.dom4j.Attribute;
import org.dom4j.Element;


public class ModuleElement implements BaseElement {
	private final static String ATTRIBUTE_METHOD = "method";
	private final static String ATTRIBUTE_REFERENCE_ID = "beanId";
	private final static String ATTRIBUTE_ID = "referenceId";
	private final static String ATTRIBUTE_DIVISION = "division";
	private final static String ELEMENT_NAME_PARAMETER = "parameters";
	private final static String ELEMENT_NAME_RESULT = "result";
	private ModuleBean module = null;

	public ModuleElement() {
		super();
	}

	public ModuleElement(ModuleBean module) {
		super();
		this.module = module;
	}

	public void doParese(Element element) throws PareseException {
		Attribute attributeBeanId = element.attribute(ATTRIBUTE_REFERENCE_ID);
		Attribute attributeMethod = element.attribute(ATTRIBUTE_METHOD);
		Attribute attributeId = element.attribute(ATTRIBUTE_ID);

		if (attributeId == null) {
			if (attributeBeanId == null) {
				throw new PareseException("Not found necessary attribute." + attributeBeanId);
			}

			if (attributeMethod == null) {
				throw new PareseException("Not found necessary attribute." + attributeMethod);
			}
		} else {
			if (attributeId == null) {
				throw new PareseException("Not found necessary attribute." + attributeId);
			}
		}
		Attribute attributeDivision = element.attribute(ATTRIBUTE_DIVISION);


		module.setReferenceId(attributeId == null ? null : attributeId.getStringValue());

		module.setBeanId(attributeBeanId == null ? null : attributeBeanId.getStringValue());
		module.setMethod(attributeMethod == null ? null : attributeMethod.getStringValue());
		module.setDivisionValue(attributeDivision == null ? null : attributeDivision.getStringValue());
		// 解析xml内部组件
		Iterator<Element> it = element.elementIterator();
		Element subElement = null;
		while (it.hasNext()) {
			subElement = it.next();
			Object[] args = new Object[] { };
			if (ELEMENT_NAME_PARAMETER.equals(subElement.getName())) {
				Parameter parameter = new Parameter();
				module.setParameter(parameter);
				args = new Object[] { parameter };
			} else if (ELEMENT_NAME_RESULT.equals(subElement.getName())) {
				Result result = new Result();
				module.setReturnResult(result);
				args = new Object[] { result };
			}
			ElementFactory.findElement(subElement.getName(), args).doParese(subElement);

		}
	}
}
