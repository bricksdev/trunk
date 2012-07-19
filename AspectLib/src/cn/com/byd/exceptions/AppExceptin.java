package cn.com.byd.exceptions;

public class AppExceptin extends Exception {
	public AppExceptin(Throwable throwable) {
		super(throwable);
	}

	public AppExceptin(String string, Throwable throwable) {
		super(string, throwable);
	}

	public AppExceptin(String string) {
		super(string);
	}

	public AppExceptin() {
		super();
	}
}
