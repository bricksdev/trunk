package cn.com.parese.elements;


import cn.com.byd.compose.beans.Parameter;
import cn.com.byd.compose.beans.ParameterType;
import cn.com.byd.utils.RefObjectUtil;
import cn.com.parese.element.BaseElement;
import cn.com.parese.element.ElementFactory;
import cn.com.parese.exception.PareseException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Element;


public class ParameterElement implements BaseElement {
	private final static String ATTRIBUTE_ID = "id";
	private final static String ATTRIBUTE_TYPE = "type";
	private final static String ATTRIBUTE_VALUE = "value";
	private Parameter parameter = null;

	public ParameterElement() {
		super();
	}

	public ParameterElement(Parameter parameter) {
		super();
		this.parameter = parameter;
	}

	public void doParese(Element element) throws PareseException {
	  Attribute attributeType = element.attribute(ATTRIBUTE_TYPE);
	  Attribute attributeValue = element.attribute(ATTRIBUTE_VALUE);
	  if (attributeType == null) {
	    throw new PareseException("Not found necessary attribute." + attributeType);
	  }

	  Attribute attributeID = element.attribute(ATTRIBUTE_ID);
		List<ParameterType> parameters = parameter.getParameters();

		ParameterType parameter = new ParameterType();
		parameters.add(parameter);
		parameter.setClazz(RefObjectUtil.load(attributeType.getStringValue()));
		parameter.setTypeName(attributeType.getStringValue());
		parameter.setReferenceValue(attributeValue == null ? null : attributeValue.getStringValue());
		parameter.setId(attributeID == null ? null : attributeID.getStringValue());
		Iterator<Element> it = element.elementIterator();
		Element subElement = null;
		if (it.hasNext()) {
			parameter.setBeanFieldMap(new HashMap<String, String>());
			do {
				subElement = it.next();
				ElementFactory.findElement(subElement.getName(), new Object[] { parameter }).doParese(subElement);
			} while (it.hasNext());
		}

	}
}
