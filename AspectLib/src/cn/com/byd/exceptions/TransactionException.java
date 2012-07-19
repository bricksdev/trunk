package cn.com.byd.exceptions;

public class TransactionException extends Exception {
	public TransactionException(Throwable throwable) {
		super(throwable);
	}

	public TransactionException(String string, Throwable throwable) {
		super(string, throwable);
	}

	public TransactionException(String string) {
		super(string);
	}

	public TransactionException() {
		super();
	}
}
