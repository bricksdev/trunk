/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package byd.wms.loader.weaving;

import cn.com.bricks.dynamic.weaving.utils.WeavingConfigUtil;
import cn.com.bricks.dynamic.weaving.UnitTestTransformer;
import cn.com.bricks.custom.loader.WmsClassLoader;
import java.io.PrintWriter;
import java.lang.instrument.Instrumentation;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.objectweb.asm.util.ASMifier;
import org.objectweb.asm.util.TraceClassVisitor;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author kete
 */
public class WmsTransformerNGTest {

    public WmsTransformerNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * premain方法的测试 (属于类WmsTransformer)。
     */
//    @Test
    public void testPremain() {
        System.out.println("premain");
        String agentArgs = "";
        Instrumentation inst = null;
        UnitTestTransformer.premain(agentArgs, inst);
        // TODO 检查生成的测试代码并删除失败的默认调用。
        fail("\u6d4b\u8bd5\u7528\u4f8b\u4e3a\u539f\u578b\u3002");
    }

    /**
     * transform方法的测试 (属于类WmsTransformer)。
     */
    @Test(invocationCount=1000,threadPoolSize=2)
    @SuppressWarnings("unchecked")
    public void testTransform() throws Exception {
        System.out.println("transform");
        WmsClassLoader loader = new WmsClassLoader(this.getClass().getClassLoader());
        String className = t.TU.class.getName().replace(".", "/");
        Class<?> classBeingRedefined = null;
        Class c = Class.forName(className.replace("/", "."));
        byte[] classfileBuffer = loader.loadClassData(className);
        UnitTestTransformer instance = new UnitTestTransformer();
        byte[] expResult = null;
        byte[] result = instance.transform(c.getClassLoader(), className, classBeingRedefined, null, classfileBuffer);
        Class r = loader.getInstance(className.replace("/", "."), result);
//        r.getConstructor(new Class[]{String.class});
//        System.out.println(r.getMethods()[0].toString());
        r.getMethod("test", new Class[]{String.class}).invoke(r.newInstance(), new Object[]{"teeeee"});
//        r.getMethod("test1", new Class[]{String.class, boolean.class}).invoke(r.newInstance(), new Object[]{className + "test1", true});
//        r.getMethod("test3", new Class[]{String.class, String.class}).invoke(r.newInstance(), new Object[]{className, "test222"});
//
//        r.getMethod("test4", new Class[]{String.class, String.class, String[].class}).invoke(r.newInstance(), new Object[]{className, "test222", new String[]{"teeeee"}});
//
//        r.getMethod("getOperaction", new Class[]{IWeavingChecker.class}).invoke(r.newInstance(), new Object[]{IWeavingChecker.class});
//        Class kk = loader.findClass(TestXMLOutput.class.getName());
//        kk.getMethod("output").invoke(kk.newInstance());
        //        assertEquals(result, expResult);
        // TODO 检查生成的测试代码并删除失败的默认调用。
//        fail("\u6d4b\u8bd5\u7528\u4f8b\u4e3a\u539f\u578b\u3002");
    }

//    @Test
    public void test() {
//        System.out.println(Pattern.matches("(([\\w-_]*[/]+)*wms_[^(util)][.]jar)", "wms_stock.jar"));
//        System.out.println(Pattern.matches("(get[\\w]*\\[/]+)", "getMacd(Ljava/lang/String;)"));
        System.out.println(WeavingConfigUtil.INSTANCE.isWeavingJar("/media/WinD/softdev/kopnew/sourcecodes/wms-trunk/lib/wms_util.jar"));
//        System.out.println(WeavingConfigUtil.INSTANCE.isNoWeavingClassAndPkgs("byd/wms/cms/service/AutoCompleteInputServiceNG$2"));
//        System.out.println(WeavingConfigUtil.INSTANCE.isNoWeavingMethod("get"));
//        System.out.println((33 & org.springframework.asm.Opcodes.ACC_STATIC));
    }

//    @Test
    public void writeByte() {
        try {
//            ClassReader r = new ClassReader("byd/wms/loader/weaving/TestXMLOutput");
//            System.out.println(new String(r.b));
            System.out.println(
            ClassLoader.getSystemClassLoader());
            ASMifier mifier = new ASMifier();
//            TraceClassVisitor visitor = new TraceClassVisitor(new PrintWriter(System.out));
//            visitor.visitSource(t.TU.class.getName(), "debug");
            
            String[] args = new String[]{"-debug",t.TU.class.getName()};
            mifier.main(args);
        } catch (Exception ex) {
            Logger.getLogger(WmsTransformerNGTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
