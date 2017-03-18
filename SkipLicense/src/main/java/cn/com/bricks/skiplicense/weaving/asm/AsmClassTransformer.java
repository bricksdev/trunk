/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.bricks.skiplicense.weaving.asm;

import cn.com.bricks.skiplicense.weaving.ClassTransformer;
import cn.com.bricks.skiplicense.weaving.SkipClassWeavingAdapter;
import cn.com.bricks.dynamic.weaving.utils.WeavingConfigUtil;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

/**
 *
 * @author kete
 */
public class AsmClassTransformer implements ClassTransformer {

    /**
     * 转换字节流
     *
     * @param result
     * @param className
     * @return
     */
    @Override
    public byte[] transformBytes(byte[] result, String className) {
        ClassReader reader = new ClassReader(result);
        ClassWriter writer = new ClassWriter(WeavingConfigUtil.INSTANCE.isDebug() ? ClassWriter.COMPUTE_FRAMES : ClassWriter.COMPUTE_MAXS);
        ClassVisitor adapter = new SkipClassWeavingAdapter(writer, className);
        reader.accept(adapter, WeavingConfigUtil.INSTANCE.isDebug() ? ClassReader.SKIP_FRAMES : ClassReader.SKIP_DEBUG);
        result = writer.toByteArray();
        return result;
    }
}
