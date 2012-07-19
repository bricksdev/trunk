package cn.com.parese.elements;


import cn.com.byd.exceptions.AppExceptin;
import cn.com.byd.factory.builder.FactoryBuilder;
import cn.com.parese.element.BaseElement;
import cn.com.parese.exception.PareseException;

import org.dom4j.Attribute;
import org.dom4j.Element;


public class BeanElement implements BaseElement {
	private final static String ATTRIBUTE_ID = "id";
	private final static String ATTRIBUTE_TYPE = "type";
	private final static String ATTRIBUTE_PROXY = "proxy";
	private final static String ATTRIBUTE_SINGLE = "single";

	public BeanElement() {
		super();
	}

	/**
	 * @param element
	 * @throws PareseException
	 */
	public void doParese(Element element) throws PareseException {


		Attribute attributeId = element.attribute(ATTRIBUTE_ID);
		Attribute attributeType = element.attribute(ATTRIBUTE_TYPE);
		Attribute attributeProxy = element.attribute(ATTRIBUTE_PROXY);
		Attribute attributeSingle = element.attribute(ATTRIBUTE_SINGLE);

		if (attributeId == null) {
			throw new PareseException("Not found necessary attribute." + attributeId);
		}

		if (attributeType == null) {
			throw new PareseException("Not found necessary attribute." + attributeType);
		}

		// 保存解析出的bean信息
		try {
			FactoryBuilder.getBeanFactory().addBean(attributeId.getStringValue(), attributeType.getStringValue(),
													attributeSingle == null ? false : Boolean.parseBoolean(attributeSingle.getStringValue()),
													attributeProxy == null ? false :
													Boolean.parseBoolean(attributeProxy.getStringValue()));
		} catch (AppExceptin e) {
			throw new PareseException(e);
		}
	}
}
