package cn.com.parese.exception;

public class PareseException extends Exception {
    @SuppressWarnings("compatibility:-3415323018505805500")
    private static final long serialVersionUID = 1400473345736780617L;

    public PareseException(Throwable throwable) {
        super(throwable);
    }

    public PareseException(String string, Throwable throwable) {
        super(string, throwable);
    }

    public PareseException(String string) {
        super(string);
    }

    public PareseException() {
        super();
    }
}
