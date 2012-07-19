package cn.com.byd.exceptions;

public class NoModuleMethodException extends Exception {
	public NoModuleMethodException(Throwable throwable) {
		super(throwable);
	}

	public NoModuleMethodException(String string, Throwable throwable) {
		super(string, throwable);
	}

	public NoModuleMethodException(String string) {
		super(string);
	}

	public NoModuleMethodException() {
		super();
	}
}
