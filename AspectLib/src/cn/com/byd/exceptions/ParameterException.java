package cn.com.byd.exceptions;

public class ParameterException extends Exception {
	public ParameterException(Throwable throwable) {
		super(throwable);
	}

	public ParameterException(String string, Throwable throwable) {
		super(string, throwable);
	}

	public ParameterException(String string) {
		super(string);
	}

	public ParameterException() {
		super();
	}
}
