/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.bricks.dynamic.weaving;

import cn.com.bricks.dynamic.weaving.utils.WeavingConfigUtil;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 *
 * @author kete
 */
public class UnitClassWeavingAdapter extends ClassVisitor implements Opcodes {

    private String className;
    private boolean isInterface;

    public UnitClassWeavingAdapter(ClassVisitor visitor, String theClass) {
        super(ASM4, visitor);
        this.className = theClass;
    }

    @Override
    public void visit(int version, int access, String name,
            String signature, String superName, String[] interfaces) {
        cv.visit(version, access, name, signature, superName, interfaces);
        className = name;
        isInterface = (access & Opcodes.ACC_INTERFACE) != 0;

    }

//    @Override
//    public void visitSource(String string, String string1) {
//        System.err.println(string + " == " + string1);
//        super.visitSource(string, "true");
//    }
    @Override
    public FieldVisitor visitField(int i, String string, String string1, String string2, Object o) {
        return super.visitField(i, string, string1, string2, o);
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
        if ((!WeavingConfigUtil.INSTANCE.isReplaceClass(className) && !name.equals("<init>") && !name.equals("<clinit>") && !isInterface) || WeavingConfigUtil.INSTANCE.isReplaceMethod(className, name)) {
            MethodVisitor ma = new UnitTestMethodWeavingAdapter(mv, className, name, descriptor, arg);
//            MethodAdapter ma = new WmsAdviceAdapter(arg, name, descriptor, mv, className);
            return ma;
        }
        return mv;
    }
}