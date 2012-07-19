package cn.com.parese.elements;


import cn.com.byd.compose.beans.ModuleBean;
import cn.com.byd.compose.beans.Parameter;
import cn.com.byd.compose.beans.Result;
import cn.com.byd.compose.modules.EqualGroupModules;
import cn.com.byd.compose.modules.IterateModules;
import cn.com.byd.compose.scope.MethodContext;
import cn.com.byd.compose.source.ModuleMethodSource;
import cn.com.byd.compose.source.ModuleSource;
import cn.com.parese.element.BaseElement;
import cn.com.parese.element.ElementFactory;
import cn.com.parese.exception.PareseException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Element;


public class ModulesElement implements BaseElement {

	private final static String ATTRIBUTE_METHOD = "method";
	private final static String ATTRIBUTE_REFERENCE_ID = "beanId";
	private final static String ATTRIBUTE_LOG = "logging";
	private final static String ATTRIBUTE_TRANSACTION = "transaction";
	private final static String ATTRIBUTE_VERIFY = "verify";
	private final static String ELEMENT_NAME_PARAMETER = "parameters";
	private final static String ELEMENT_NAME_RESULT = "result";
	private final static String ELEMENT_NAME_MODULE = "module";
	private final static String ELEMENT_NAME_ITERATOR = "iterator";
	private final static String ELEMENT_NAME_NOT_EQUAL = "isNotEqual";
	private final static String ELEMENT_NAME_EQUAL = "isEqual";
	private final static String ATTRIBUTE_ID = "id";

	public ModulesElement() {
		super();
	}

	public void doParese(Element element) throws PareseException {
		Attribute attributeBeanId = element.attribute(ATTRIBUTE_REFERENCE_ID);
		Attribute attributeMethod = element.attribute(ATTRIBUTE_METHOD);
		Attribute attributeLog = element.attribute(ATTRIBUTE_LOG);
		Attribute attributeTransaction = element.attribute(ATTRIBUTE_TRANSACTION);
		Attribute attributeVerify = element.attribute(ATTRIBUTE_VERIFY);

		if (attributeBeanId == null) {
			throw new PareseException("Not found necessary attribute." + attributeBeanId);
		}

		if (attributeMethod == null) {
			throw new PareseException("Not found necessary attribute." + attributeMethod);
		}
		Attribute attributeId = element.attribute(ATTRIBUTE_ID);
		ModuleSource source = ModuleSource.INSTANCE;
		ModuleMethodSource methodConfig = new ModuleMethodSource();
		// 设定方法名
		methodConfig.setMethod(attributeMethod.getStringValue());
		methodConfig.setId(attributeId == null ? null : attributeId.getStringValue());
		MethodContext methodContext = new MethodContext();

		methodContext.setIsLog(attributeLog == null ? false : Boolean.parseBoolean(attributeLog.getStringValue()));

		methodContext.setIsTransaction(attributeTransaction == null ? false :
									   Boolean.parseBoolean(attributeTransaction.getStringValue()));

		methodContext.setIsVerify(attributeVerify == null ? false :
								  Boolean.parseBoolean(attributeVerify.getStringValue()));

		methodConfig.setMethodContext(methodContext);

		source.setClassAndMethodList(attributeBeanId.getStringValue(), methodConfig);

		// 解析xml内部组件
		Iterator<Element> it = element.elementIterator();
		Element subElement = null;
		while (it.hasNext()) {
			subElement = it.next();
			Object[] args = new Object[] { methodContext };
			if (ELEMENT_NAME_PARAMETER.equals(subElement.getName())) {
				Parameter parameter = new Parameter();
				methodContext.setParameter(parameter);
				args = new Object[] { parameter };
			} else if (ELEMENT_NAME_RESULT.equals(subElement.getName())) {
				Result result = new Result();
				methodContext.setReturnResult(result);
				args = new Object[] { result };
			} else if (ELEMENT_NAME_MODULE.equals(subElement.getName())) {
				ModuleBean module = new ModuleBean();
				List<ModuleBean> modules = methodContext.findCurrentModules();
				if (modules == null) {
					modules = new ArrayList<ModuleBean>();
					methodContext.setCurrentModules(modules);
				}
				modules.add(module);
				args = new Object[] { module };
			} else if (ELEMENT_NAME_ITERATOR.equals(subElement.getName())) {
				IterateModules modules = new IterateModules();
				methodContext.setIteratorModules(modules);
				args = new Object[] { modules };
			} else if (ELEMENT_NAME_NOT_EQUAL.equals(subElement.getName())) {
				EqualGroupModules modules = new EqualGroupModules();

				methodContext.setConditionGroupModule(modules);
				args = new Object[] { modules };
			} else if (ELEMENT_NAME_EQUAL.equals(subElement.getName())) {
				EqualGroupModules modules = new EqualGroupModules();

				methodContext.setConditionGroupModule(modules);
				args = new Object[] { modules };
			}
			ElementFactory.findElement(subElement.getName(), args).doParese(subElement);

		}

	}
}
