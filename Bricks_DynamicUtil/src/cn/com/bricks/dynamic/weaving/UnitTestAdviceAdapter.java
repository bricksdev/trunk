/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.bricks.dynamic.weaving;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AdviceAdapter;
import org.objectweb.asm.commons.Method;



/**
 *
 * @author kete
 */
public class UnitTestAdviceAdapter extends AdviceAdapter {

    private String _className;
    private String _methodName;
    private Method method;

    public UnitTestAdviceAdapter(int access, String name, String desc,
            MethodVisitor mv) {
        super(ASM4, mv, access, name, desc);
//        super(mv, access, name, desc);
        _methodName = name;
        method = new Method(_methodName, desc);
    }

    public UnitTestAdviceAdapter(int access, String name, String desc,
            MethodVisitor mv, String className) {
        this(access, name, desc, mv);
        _className = className;
        _methodName = name;
        method = new Method(_methodName, desc);
    }

    @Override
    protected void onMethodEnter() {
//        System.out.println("ttte");
//        this.visitLdcInsn(_className);
//        this.visitLdcInsn(_methodName);
//        method = new Method(_methodName, desc);
//        Type[] args = method.getArgumentTypes();
//        if (args != null && args.length > 0) {
//            this.visitInsn(args.length);
//            this.visitTypeInsn(Opcodes.ANEWARRAY, "java/lang/Object");
//            int idx = 0;
//            for (Type arg : args) {
//                this.visitInsn(Opcodes.DUP);
//                this.visitInsn(idx++);
//                this.setVarType(arg, idx);
//                this.visitInsn(Opcodes.AASTORE);
//            }
//            this.visitVarInsn(Opcodes.ASTORE, idx + 1);
//            this.visitLdcInsn(_className);
//            this.visitLdcInsn(_methodName);
//            this.visitVarInsn(Opcodes.ALOAD, idx + 1);
//            this.visitMethodInsn(Opcodes.INVOKESTATIC,
//                    "byd/wms/loader/weaving/TestXMLOutput",
//                    "exportParameters",
//                    "(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V");
//        } else {
        this.visitLdcInsn("");
        this.visitLdcInsn("");
        this.visitMethodInsn(Opcodes.INVOKESTATIC,
                "cn/com/bricks/dynamic/weaving/TestXMLOutput",
                "exportParameters",
                "(Ljava/lang/String;Ljava/lang/String;)V");
//        }
    }

    @Override
    protected void onMethodExit(int i) {
        System.out.println("end");
        mv.visitLdcInsn("");
        mv.visitLdcInsn("");

//        mv.visitInsn(ICONST_1);
//        mv.visitTypeInsn(ANEWARRAY, "java/lang/Object");
//        mv.visitInsn(DUP);
//        mv.visitInsn(ICONST_0);
//        mv.visitVarInsn(ALOAD, 2);
//        mv.visitInsn(AASTORE);
        mv.visitMethodInsn(INVOKESTATIC, "byd/wms/loader/weaving/TestXMLOutput", "exportReturn", "(Ljava/lang/String;Ljava/lang/String;)V");
    }

    public static int getACC_VARARGS() {
        return ACC_VARARGS;
    }

    @Override
    public void catchException(Label label, Label label1, Type type) {
        System.out.println("exception");
//        mv.visitLdcInsn(_className);
//        mv.visitLdcInsn(_methodName);
//        mv.visitVarInsn(ALOAD, 3);
//        mv.visitMethodInsn(INVOKESTATIC, "byd/wms/loader/weaving/TestXMLOutput", "exceptions", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V");
//        super.catchException(label, label1, type);
    }

    /**
     * 设定返回变量数据
     *
     * @param type
     */
    private void setVarType(Type type, int idx) {
//        System.out.println(type + "  "+ idx);
        if (type == Type.BOOLEAN_TYPE) {
            this.visitVarInsn(Opcodes.ILOAD, idx);
            this.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;");
        } else if (type == Type.BYTE_TYPE) {
            this.visitVarInsn(Opcodes.ILOAD, idx);
            this.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Byte", "valueOf", "(B)Ljava/lang/Byte;");
        } else if (type == Type.CHAR_TYPE) {
            this.visitVarInsn(Opcodes.ILOAD, idx);
            this.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Character", "valueOf", "(C)Ljava/lang/Character;");
        } else if (type == Type.DOUBLE_TYPE) {
            this.visitVarInsn(Opcodes.DLOAD, idx);
        } else if (type == Type.FLOAT_TYPE) {
            this.visitVarInsn(Opcodes.FLOAD, idx);
        } else if (type == Type.INT_TYPE) {
            this.visitVarInsn(Opcodes.ILOAD, idx);
            this.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
        } else if (type == Type.LONG_TYPE) {
            this.visitVarInsn(Opcodes.LLOAD, idx);

        } else {
            this.visitVarInsn(Opcodes.ALOAD, idx);
        }
    }
}
