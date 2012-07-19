package cn.com.byd.exceptions;

public class AuthException extends Exception {
	public AuthException(Throwable throwable) {
		super(throwable);
	}

	public AuthException(String string, Throwable throwable) {
		super(string, throwable);
	}

	public AuthException(String string) {
		super(string);
	}

	public AuthException() {
		super();
	}
}
