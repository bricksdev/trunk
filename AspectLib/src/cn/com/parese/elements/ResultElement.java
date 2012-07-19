package cn.com.parese.elements;


import cn.com.byd.compose.beans.Append;
import cn.com.byd.compose.beans.Result;
import cn.com.byd.utils.RefObjectUtil;
import cn.com.parese.element.BaseElement;
import cn.com.parese.element.ElementFactory;
import cn.com.parese.exception.PareseException;

import java.util.ArrayList;
import java.util.Iterator;

import org.dom4j.Attribute;
import org.dom4j.Element;


public class ResultElement implements BaseElement {
	private final static String ATTRIBUTE_TYPE = "type";
	private final static String ATTRIBUTE_ID = "id";
	private final static String ATTRIBUTE_VALUE = "value";
	private Result result = null;

	public ResultElement() {
		super();
	}

	public ResultElement(Result result) {
		super();
		this.result = result;
	}

	public void doParese(Element element) throws PareseException {
		Attribute attributeType = element.attribute(ATTRIBUTE_TYPE);

		if (attributeType == null) {
			throw new PareseException("Not found necessary attribute." + attributeType);
		}
		Attribute attributeValue = element.attribute(ATTRIBUTE_VALUE);
		Attribute attributeID = element.attribute(ATTRIBUTE_ID);

		result.setRetultType(attributeType.getStringValue());
		result.setClazz(RefObjectUtil.load(attributeType.getStringValue()));

		result.setId(attributeID == null ? null : attributeID.getStringValue());
		result.setRefereceValue(attributeValue == null ? null : attributeValue.getStringValue());

		Iterator<Element> it = element.elementIterator();
		if (it.hasNext()) {
			Element subElement = null;
			result.setAppends(new ArrayList<Append>());
			do {
				subElement = it.next();
				ElementFactory.findElement(subElement.getName(), new Object[] { result }).doParese(subElement);
			} while (it.hasNext());
		}
	}
}
