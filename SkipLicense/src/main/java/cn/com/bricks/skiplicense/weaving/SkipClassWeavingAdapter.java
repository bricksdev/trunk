/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.bricks.skiplicense.weaving;

import cn.com.bricks.dynamic.weaving.utils.WeavingConfigUtil;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 *
 * @author kete
 */
public class SkipClassWeavingAdapter extends ClassVisitor implements Opcodes {

    private String className;

    public SkipClassWeavingAdapter(ClassVisitor visitor, String theClass) {
        super(ASM4, visitor);
        this.className = theClass;
    }

    @Override
    public void visit(int version, int access, String name,
            String signature, String superName, String[] interfaces) {
        cv.visit(version, access, name, signature, superName, interfaces);
        className = name;
    }

    /**
     *
     * @param arg
     * @param name
     * @param descriptor
     * @param signature
     * @param exceptions
     * @return
     */
    @Override
    public MethodVisitor visitMethod(int arg,
            String name,
            String descriptor,
            String signature,
            String[] exceptions) {
        MethodVisitor mv = super.visitMethod(arg,
                name,
                descriptor,
                signature,
                exceptions);
        if (WeavingConfigUtil.INSTANCE.isReplaceClass(className) && WeavingConfigUtil.INSTANCE.isReplaceMethod(className, name)) {
            // 替换指定类 
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getName", "()Ljava/lang/String;", false);
            mv.visitMethodInsn(INVOKESTATIC, "java/util/logging/Logger", "getLogger", "(Ljava/lang/String;)Ljava/util/logging/Logger;", false);
            mv.visitLdcInsn("IP granted success.");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/util/logging/Logger", "warning", "(Ljava/lang/String;)V", false);
            mv.visitInsn(RETURN);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }
        return mv;
    }
}
