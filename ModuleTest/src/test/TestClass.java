package test;


import cn.com.byd.exceptions.AppExceptin;
import cn.com.byd.exceptions.WmsException;
import cn.com.byd.factory.builder.FactoryBuilder;
import cn.com.byd.iservice.IReceive;
import cn.com.parese.reader.XMLConfigReader;

public class TestClass {
	public TestClass() {
		super();
	}

	public static void initTestData() throws AppExceptin {
		//        FactoryBuilder.createBeanFactory(null);
		//        FactoryBuilder.createLoggerFactory(null);
		//        FactoryBuilder.createProxyFactory(null);
		//        FactoryBuilder.createAuthenticationFactory(null);
		//        FactoryBuilder.createTransactionactory(null);
		//
		//        BeanFactory beanFactory = FactoryBuilder.getBeanFactory();
		////        beanFactory.addBean("asnModule", "cn.com.byd.modules.AsnModule");
		////        beanFactory.addDynamicBean("receiveService",
		////                                   FactoryBuilder.getProxyFactory().getProxy().findInterface("receiveService"));
		//        //		beanFactory.addBean("cn.com.byd.beans.TransactionHeader");
		//        //		beanFactory.addBean("cn.com.byd.beans.TransactionItem");
		//        //		beanFactory.addBean("");
		//        Map<String, List<ModuleMethodSource>> params = new HashMap<String, List<ModuleMethodSource>>();
		//        List<ModuleMethodSource> methods = new ArrayList<ModuleMethodSource>();
		//        // 构建方法数据
		//        ModuleMethodSource method = new ModuleMethodSource();
		//        method.setMethod("saveReceive");
		//        // 构建方法内部上下文 独立于各方法之外
		//        MethodContext methodContext = new MethodContext();
		//        List<ModuleBean> currentModules = new ArrayList<ModuleBean>();
		//
		//        ModuleBean moduleBean = new ModuleBean();
		//        moduleBean.setMethod("updateAsnStatus");
		//        moduleBean.setBeanId("asnModule");
		//        Parameter parameter = null;
		//        Result returnResult = null;
		//        List<ParameterType> parameterTypes = null;
		//        ParameterType parameterType = null;
		//
		//        parameter = new Parameter();
		//        // 设定参数类型
		//        parameterTypes = new ArrayList<ParameterType>();
		//        parameterType = new ParameterType();
		//        //	  parameterType.setId("asnNo");
		//        parameterType.setTypeName("java.lang.String");
		//        parameterType.setClazz(String.class);
		//        parameterType.setReferenceValue("asnNo");
		//        parameterTypes.add(parameterType);
		//
		//        //	  parameterType = new ParameterType();
		//        //	  //    parameterType.setId("asnNo");
		//        //	  parameterType.setTypeName("cn.com.byd.beans.TransactionItem");
		//        //	  parameterType.setClazz(TransactionItem.class);
		//        //	  parameterTypes.add(parameterType);
		//        parameter.setParameters(parameterTypes);
		//
		//        returnResult = new Result();
		//        returnResult.setClazz(String.class);
		//        returnResult.setRetultType("java.lang.String");
		//        returnResult.setId("wmsPzh");
		//        moduleBean.setParameter(parameter);
		//        moduleBean.setReturnResult(returnResult);
		//        currentModules.add(moduleBean);
		//        // 追加组件列表
		//        methodContext.setCurrentModules(currentModules);
		//        parameter = new Parameter();
		//        // 设定参数类型
		//        parameterTypes = new ArrayList<ParameterType>();
		//        parameterType = new ParameterType();
		//        parameterType.setId("asnNo");
		//        parameterType.setTypeName("java.lang.String");
		//        parameterType.setClazz(String.class);
		//        parameterTypes.add(parameterType);
		//        parameter.setParameters(parameterTypes);
		//
		//        returnResult = new Result();
		//        returnResult.setClazz(String.class);
		//        returnResult.setRetultType("java.lang.String");
		//        List<Append> appends = new ArrayList<Append>();
		//        Append append = new Append();
		//        append.setPrepend("数据保存成功,凭证号:");
		//        append.setProperty("wmsPzh");
		//        appends.add(append);
		//        returnResult.setAppends(appends);
		//        //		returnResult.setRefereceValue("wmsPzh");
		//        // 设定该方法 参数及返回结果
		//        methodContext.setParameter(parameter);
		//        methodContext.setReturnResult(returnResult);
		//
		//        method.setMethodContext(methodContext);
		//        methods.add(method);
		//        params.put("cn.com.byd.iservice.impl.ReceiveImpl", methods);
		//        ModuleSource.INSTANCE.setClassAndMethodMap(params);
		XMLConfigReader.INSTANCE.doParese();
	}

	public static void main(String[] args) {
		try {
			initTestData();

			IReceive receive;
			receive = (IReceive)FactoryBuilder.getBeanFactory().getBean("receiveModule");
			String asnNo = "123456789";

			String result = receive.saveReceive(asnNo);
			System.out.println("result=>" + result);
		} catch (WmsException e) {
			e.printStackTrace();
		} catch (AppExceptin e) {
			e.printStackTrace();
		}
	}
}
