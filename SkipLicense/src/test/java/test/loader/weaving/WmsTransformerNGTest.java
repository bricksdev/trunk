/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.loader.weaving;

import cn.com.bricks.skiplicense.weaving.SkipLicenseTransformer;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.lang.instrument.Instrumentation;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.objectweb.asm.util.ASMifier;
import org.objectweb.asm.util.TraceClassVisitor;
//import jdk.internal.org.objectweb.asm.util.ASMifier;
//import jdk.internal.org.objectweb.asm.util.TraceClassVisitor;
import static org.testng.Assert.fail;
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
        SkipLicenseTransformer.premain(agentArgs, inst);
        // TODO 检查生成的测试代码并删除失败的默认调用。
        fail("\u6d4b\u8bd5\u7528\u4f8b\u4e3a\u539f\u578b\u3002");
    }

    /**
     * transform方法的测试 (属于类WmsTransformer)。
     */
    @Test(invocationCount = 1, threadPoolSize = 2)
    @SuppressWarnings("unchecked")
    public void testTransform() throws Exception {
        System.out.println("transform");
        TestOnlyClassLoader loader = new TestOnlyClassLoader(this.getClass().getClassLoader());
        String className = TU.class.getName().replace(".", "/");
        TU tu = new TU();
        tu.display1();
        Class<?> classBeingRedefined = null;
        Class c = Class.forName(className.replace("/", "."));
        byte[] classfileBuffer = loader.loadClassData(className);
        SkipLicenseTransformer instance = new SkipLicenseTransformer();
        byte[] expResult = null;
        byte[] result = instance.transform(loader, className, classBeingRedefined, null, classfileBuffer);
        Class r = loader.getInstance(className.replace("/", "."), result);
        File file = new File("target/com/apusic/server/J2EEServer.class");
        if (file != null) {
            file.delete();
        }
        if (file != null && !file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        FileOutputStream outputstream = new FileOutputStream(file, true);
        outputstream.write(result);
        outputstream.flush();
        outputstream.close();
        r.getMethod("displayVersion").invoke(r.newInstance());
    }
    @Test
    public void writebyte(){
        writeByte(TU.class.getName());
    }
    
    public void writeByte(String clName) {
        try {
//            ClassReader r = new ClassReader("byd/wms/loader/weaving/TestXMLOutput");
//            System.out.println(new String(r.b));
            ASMifier mifier = new ASMifier();
            TraceClassVisitor visitor = new TraceClassVisitor(new PrintWriter(System.out));
            visitor.visitSource(clName, null);

            String[] args = new String[]{clName};
            mifier.main(args);
        } catch (Exception ex) {
            Logger.getLogger(WmsTransformerNGTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
