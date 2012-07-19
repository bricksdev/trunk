package cn.com.parese.element;


import cn.com.byd.utils.RefObjectUtil;

import java.io.InputStream;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;


public class ElementFactory {

	private final static Properties CONFIG = new Properties();
	private final static String ELEMENT_CONFIG = "config/element.properties";
	private final static Map<String, BaseElement> elements = new ConcurrentHashMap<String, BaseElement>();

	static {
		InputStream reader = null;
		try {
			reader = Thread.currentThread().getContextClassLoader().getResourceAsStream(ELEMENT_CONFIG);
			CONFIG.load(reader);

			for (String key : CONFIG.stringPropertyNames()) {
				elements.put(key, (BaseElement)RefObjectUtil.loadClass(CONFIG.getProperty(key)));
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				reader = null;
			}

		}

	}

	public static BaseElement findElement(String element) {
		BaseElement nodeParese = BaseElement.NULL;
		if (elements.containsKey(element)) {
			nodeParese = elements.get(element);
		}
		return nodeParese;
	}

	public static BaseElement findElement(String elementName, Object[] args) {
		BaseElement nodeParese = BaseElement.NULL;
		if (elements.containsKey(elementName)) {

			nodeParese = (BaseElement)RefObjectUtil.loadClass(elements.get(elementName).getClass().getName(), args);

		}
		return nodeParese;
	}
}
