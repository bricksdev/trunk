/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.bricks.dynamic.weaving;

import cn.com.bricks.dynamic.loader.util.DynamicDeployUtil;
import cn.com.bricks.dynamic.weaving.utils.WeavingConfigUtil;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

/**
 *
 * @author kete
 */
public class UnitTestTransformer implements ClassFileTransformer {

    private Log log = LogFactory.getLog(UnitTestTransformer.class);

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("javaagent start");
        inst.addTransformer(new UnitTestTransformer());

    }

    @Override
    public byte[] transform(ClassLoader loader, String className,
            Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
            byte[] classfileBuffer) throws IllegalClassFormatException {
        byte[] result = classfileBuffer; //定义新的字节码存储变量
        if (log.isTraceEnabled()) {
            log.trace("Loading " + className + " by " + loader.toString());
        }
        // 判断是否重新加载类文件，并实现动态部署
        if (DynamicDeployUtil.isReloadResource(className)) {
            if (log.isTraceEnabled()) {
                log.trace("Reloading " + className + " by " + loader.toString());
            }
            result = DynamicDeployUtil.findResource(className);
        }
        //在此通过修改类的字节码 classfileBuffer，来更改类。
        if (!WeavingConfigUtil.INSTANCE.isWeavingLoader(loader)) {
            return result;
        }
        boolean isWeavingClass = true;
        // 此处判断不需要修改的类将不进行修改
        if (WeavingConfigUtil.INSTANCE.isNoWeavingClassAndPkgs(className)) {
            isWeavingClass = false;
        }
        boolean isWeavingPkgs = false;
        // 处理包的weaving
        String jarName = (protectionDomain != null && protectionDomain.getCodeSource() != null && protectionDomain.getCodeSource().getLocation() != null) ? protectionDomain.getCodeSource().getLocation().getFile() : "";
        // 如果当前路径为classes下或为自定义的classloader(protectionDomain为NULL)需要weaving
        if ((isWeavingClass && WeavingConfigUtil.INSTANCE.isWeavingJar(jarName)) || protectionDomain == null) {
            isWeavingPkgs = true;
        }

        // weaving处理
        if (isWeavingClass && isWeavingPkgs || WeavingConfigUtil.INSTANCE.isReplaceClass(className)) {
            if (log.isTraceEnabled()) {
                log.trace("Weaving " + className + " by ASM4.1");
            }
            ClassReader reader = new ClassReader(result);
            ClassWriter writer = new ClassWriter(WeavingConfigUtil.INSTANCE.isDebug() ? ClassWriter.COMPUTE_FRAMES : ClassWriter.COMPUTE_MAXS);
            ClassVisitor adapter = new UnitClassWeavingAdapter(writer, className);
            reader.accept(adapter, WeavingConfigUtil.INSTANCE.isDebug() ? ClassReader.SKIP_FRAMES : ClassReader.SKIP_DEBUG);

            result = writer.toByteArray();
        }

        return result; //返回新的字节码
    }
}
