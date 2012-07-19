package cn.com.parese.elements;


import cn.com.byd.compose.beans.Append;
import cn.com.byd.compose.beans.Result;
import cn.com.parese.element.BaseElement;
import cn.com.parese.exception.PareseException;

import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Element;


public class AppendElement implements BaseElement {

	private final static String ATTRIBUTE_PREPEND = "prepend";
	private final static String ATTRIBUTE_VALUE = "value";
	private final static String ATTRIBUTE_EMPTY = "emptyAppend";
	private Result result = null;

	public AppendElement() {
		super();
	}

	public AppendElement(Result result) {
		super();
		this.result = result;
	}

	public void doParese(Element element) throws PareseException {

		Attribute attributeValue = element.attribute(ATTRIBUTE_VALUE);
		if (attributeValue == null) {
			throw new PareseException("Not found necessary attribute." + attributeValue);
		}
		Attribute attributePre = element.attribute(ATTRIBUTE_PREPEND);
		Attribute attributeEmpty = element.attribute(ATTRIBUTE_EMPTY);
		List<Append> appends = result.getAppends();
		Append append = new Append();
		appends.add(append);
		append.setEmptyAppend(attributeEmpty == null ? true : Boolean.parseBoolean(attributeEmpty.getStringValue()));
		append.setPrepend(attributePre == null ? null : attributePre.getStringValue());
		append.setProperty(attributeValue.getStringValue());
	}
}
