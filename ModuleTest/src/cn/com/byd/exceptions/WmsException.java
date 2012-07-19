package cn.com.byd.exceptions;

public class WmsException extends Exception {
	public WmsException(Throwable throwable) {
		super(throwable);
	}

	public WmsException(String string, Throwable throwable) {
		super(string, throwable);
	}

	public WmsException(String string) {
		super(string);
	}

	public WmsException() {
		super();
	}
}
