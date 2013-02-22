package t;

public class TU {

    public void test(String t) {
        System.out.println(t);
    }

    public void test1(String t) {
        try {
            System.out.println(t);
        } catch (Throwable ex) {
            throw ex;
        }
    }
}
//
//interface IWeavingChecker {
//
//    boolean checkingOperation(String operation);
//}
//
//class ObjectFactory {
//
//    public static <T> T turnObject(Class<T> condition) {
//        return (T) condition;
//    }
//}