package cn.com.parese.elements;

import cn.com.byd.compose.beans.ParameterType;
import cn.com.parese.element.BaseElement;
import cn.com.parese.exception.PareseException;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Element;

public class PropertyElement implements BaseElement {

    private final static String ATTRIBUTE_ID = "id";
    private final static String ATTRIBUTE_TYPE = "type";
    private final static String ATTRIBUTE_VALUE = "value";
    private ParameterType parameter = null;

    public PropertyElement() {
        super();
    }

    public PropertyElement(ParameterType parameter) {
        super();
        this.parameter = parameter;
    }

    public void doParese(Element element) throws PareseException {
//		Attribute attributeType = element.attribute(ATTRIBUTE_TYPE);
        Attribute attributeValue = element.attribute(ATTRIBUTE_VALUE);
//		if (attributeType == null) {
        //			throw new PareseException("Not found necessary attribute." + attributeType);
        //		}

        Attribute attributeID = element.attribute(ATTRIBUTE_ID);
        if (attributeValue == null) {
            throw new PareseException("Not found necessary attribute." + attributeValue);
        }
        if (attributeID == null) {
            throw new PareseException("Not found necessary attribute." + attributeID);
        }

        Map<String, String> propertyMappings = parameter.getBeanFieldMap();

        if (propertyMappings == null) {
            propertyMappings = new HashMap<String, String>();
        }
        propertyMappings.put(attributeID.getStringValue(), attributeValue.getStringValue());
    }
}
