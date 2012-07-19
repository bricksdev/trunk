package cn.com.byd.compose.source;


import cn.com.byd.compose.scope.MethodContext;

public class ModuleMethodSource {
	private String method = null;
	private String id = null;
	private MethodContext methodContext = null;

	public void setMethod(String method) {
		this.method = method;
	}

	public String getMethod() {
		return method;
	}

	public void setMethodContext(MethodContext methodContext) {
		this.methodContext = methodContext;
	}

	public MethodContext getMethodContext() {
		return methodContext;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}
