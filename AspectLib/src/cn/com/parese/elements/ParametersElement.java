package cn.com.parese.elements;


import cn.com.byd.compose.beans.Parameter;
import cn.com.byd.compose.beans.ParameterType;
import cn.com.parese.element.BaseElement;
import cn.com.parese.element.ElementFactory;
import cn.com.parese.exception.PareseException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Element;


public class ParametersElement implements BaseElement {
	private Parameter parameter = null;

	public ParametersElement() {
		super();
	}

	public ParametersElement(Parameter parameter) {
		super();
		this.parameter = parameter;
	}

	public void doParese(Element element) throws PareseException {
		// 设定空的参数列表
		List<ParameterType> parameters = new ArrayList<ParameterType>();
		parameter.setParameters(parameters);

		// 遍历参数列表配置
		Iterator<Element> it = element.elementIterator();
		Element subElement = null;
		while (it.hasNext()) {
			subElement = it.next();
			ElementFactory.findElement(subElement.getName(), new Object[] { parameter }).doParese(subElement);
		}
	}
}
