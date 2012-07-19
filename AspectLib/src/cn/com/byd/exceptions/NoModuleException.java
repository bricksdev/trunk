package cn.com.byd.exceptions;

public class NoModuleException extends Exception {
	public NoModuleException(Throwable throwable) {
		super(throwable);
	}

	public NoModuleException(String string, Throwable throwable) {
		super(string, throwable);
	}

	public NoModuleException(String string) {
		super(string);
	}

	public NoModuleException() {
		super();
	}
}
