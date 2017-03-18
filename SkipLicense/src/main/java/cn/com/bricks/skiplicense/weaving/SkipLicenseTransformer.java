/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.bricks.skiplicense.weaving;

import cn.com.bricks.skiplicense.weaving.asm.AsmClassTransformer;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 *
 * @author kete
 */
public class SkipLicenseTransformer implements ClassFileTransformer {

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("license granted.");
        inst.addTransformer(new SkipLicenseTransformer());

    }

    @Override
    public byte[] transform(ClassLoader loader, String className,
            Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
            byte[] classfileBuffer) throws IllegalClassFormatException {
        byte[] result = classfileBuffer; //定义新的字节码存储变量
        // 转换字节码处理
        ClassTransformer transformer = new AsmClassTransformer();
        result = transformer.transformBytes(result, className);

        return result; //返回新的字节码
    }
}
