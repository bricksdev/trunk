package test.loader.weaving;

import java.util.ArrayList;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author kete
 */
public class TU {
    
    public void test() {
    }
    
    public boolean test2(String z, boolean c) {
//        Object[] bj = new Object[]{z, c};
//        TestXMLOutput.exportParameters("", "", bj);
        boolean b = false;

//        TestXMLOutput.exportReturn("", "", b);
        return b;
    }
    
    public boolean test(String z, boolean c, String... args) {
        Object[] bj = new Object[]{z, c, args};
        ArrayList obj = new ArrayList();
        obj.add(z);
        obj.add(c);
        obj.add(args);
//        DefaultXMLPrinter.exportParameters("", "", obj);
        boolean b = c;

//        TestXMLOutput.exportReturn("", "", b);
        return b;
    }
    
    public boolean test1(String z, boolean c) {
        ArrayList list = new ArrayList();
        list.add(z);
        list.add(c);
        boolean b = false;

//        TestXMLOutput.exportReturn("", "", b);
        return b;
    }
    
    public synchronized void check(String tent) {
        String t = tent;
    }
    
    public String test3(String o, String o1) {
        System.out.println(o + o1);
        String o2 = o + o1;
        return o2;
    }
    
    public String test4(String l, String p, String... args) {
        Integer i = 0;
        String re = args[i];
        return re;
    }
    
    public String test5(String l, String p, boolean... args) {
        Integer i = 0;
        String re = String.valueOf(args[i]);
        return re;
    }

    /**
     *
     * @param e1
     * @return
     */
    public String testttt(String e1) throws Exception {
        try {
        } catch (RuntimeException ex) {
            Exception e = ex;
            throw e;
        }
        return null;
    }
    
    private void display() {
        
    }

    protected void display1() {
        Logger.getLogger(this.getClass().getName()).warning("IP granted success.");
    }

    /**
     * 转化基本数据类型
     *
     * @param obj
     * @return
     */
    public static Object turnPrimitiveType(Object obj) {
        
        if (obj == null) {
            return obj;
        }
        Object rtn = obj;
        
        return rtn;
        
    }
//    public void test2(String o) {
//        TestXMLOutput.exportParameters("", "", o);
//        System.out.println(o);
//        TestXMLOutput.exportReturn("", "", null);
//    }
//    public String test3(String o, String o1) {
//        TestXMLOutput.exportParameters("", "", o, o1);
//        System.out.println(o + o1);
//        String o2 = o + o1;
//        TestXMLOutput.exportReturn(o1, o1, o2);
//        return o2;
//    }
//
//    public String test4(String l, String p, String... args) {
//        TestXMLOutput.exportParameters("", "", l, p, args);
//        Integer i = 0;
//        String re = args[i];
//        TestXMLOutput.exportReturn(l, p, re);
//        return re;
//    }
    
}

interface IWeavingChecker {
    
    boolean checkingOperation(String operation);
}

class ObjectFactory {
    
    public static <T> T turnObject(Class<T> condition) {
        return (T) condition;
    }
}
