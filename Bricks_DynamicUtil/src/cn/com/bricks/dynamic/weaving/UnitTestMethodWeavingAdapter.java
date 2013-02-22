/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.com.bricks.dynamic.weaving;

import cn.com.bricks.dynamic.weaving.utils.WeavingConfigUtil;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.Method;

/**
 *
 * @author kete
 */
public class UnitTestMethodWeavingAdapter extends MethodVisitor implements Opcodes {

    private String _className, _methodName;
    private String _parameters;
    private Method method;
    private boolean isReplaceMethod = false;
//    private int stack = 0;
    private boolean isNoWeavingMethod = false;
    private boolean isStatic = false;
    private int skipFinally = 0;
    private int returnIdx = 0;
    private Label l0 = new Label();
    private Label l1 = new Label();
    private Label l2 = new Label();

    public UnitTestMethodWeavingAdapter(MethodVisitor visitor,
            String className,
            String methodName, String parameters) {
        super(ASM4, visitor);
//        super(visitor);

        _className = className;
        _methodName = methodName;
        _parameters = parameters;
        method = new Method(_methodName, _parameters);
        isReplaceMethod = WeavingConfigUtil.INSTANCE.isReplaceMethod(className, methodName);
        // 是否不需要注入的方法
        isNoWeavingMethod = WeavingConfigUtil.INSTANCE.isNoWeavingMethod(methodName);
        if (isNoWeavingMethod) {
            if (_parameters.contains("(Ljava/lang/Class;)")) {
                isNoWeavingMethod = false;
            }
        }
    }

    public UnitTestMethodWeavingAdapter(MethodVisitor visitor,
            String className,
            String methodName, String parameters, int access) {
        this(visitor, className, methodName, parameters);
        isStatic = (access & ACC_STATIC) != 0;
        //        localVar = new LocalVariablesSorter(access, _parameters, mv);
    }

    @Override
    public void visitCode() {

        if (!isNoWeavingMethod) {
            Type[] args = method.getArgumentTypes();
            if (isReplaceMethod) {
//                returnIdx = (isStatic ? 1 : 2);
//                this.visitTypeInsn(NEW, "java/lang/Object");
//                this.visitInsn(DUP);
                this.visitTryCatchBlock(l0, l1, l1, "java/lang/Throwable");
//                this.visitInsn(ACONST_NULL);
//                this.visitVarInsn(ASTORE, returnIdx);

                this.visitLabel(l0);
//                this.visitLabel(l1);
            } else {
                returnIdx = args.length + 1;
                setReturnVar(method.getReturnType());
                if (args != null && args.length > 0) {

                    this.visitTypeInsn(NEW, "java/util/ArrayList");
                    this.visitInsn(DUP);
                    this.visitMethodInsn(INVOKESPECIAL, "java/util/ArrayList", "<init>", "()V");
                    this.visitVarInsn(ASTORE, args.length + 2);

                    int idx = 0;
                    for (Type arg : args) {
                        this.visitVarInsn(ALOAD, args.length + 2);
                        this.setVarType(arg, isStatic ? idx++ : (++idx));
                        this.visitMethodInsn(INVOKEVIRTUAL, "java/util/ArrayList", "add", "(Ljava/lang/Object;)Z");
                        this.visitInsn(POP);
                    }

//            this.visitInsn(args.length + 4);
//            this.visitTypeInsn(ANEWARRAY, "java/lang/Object");
//            int idx = 0;
//
//            for (Type arg : args) {
//                this.visitInsn(DUP);
//                this.visitInsn(args.length + (++idx));
//                this.setVarType(arg, idx);
//                this.visitInsn(AASTORE);
//            }
//            this.visitVarInsn(ASTORE, args.length + 1);
                    this.visitLdcInsn(_className);
                    this.visitLdcInsn(method.toString());
                    this.visitVarInsn(ALOAD, args.length + 2);
                    this.visitMethodInsn(INVOKESTATIC,
                            "cn/com/bricks/dynamic/weaving/print/PrinterFactory",
                            "exportParameters",
                            "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V");

                } else {
                    this.visitLdcInsn(_className);
                    this.visitLdcInsn(method.toString());
                    this.visitMethodInsn(INVOKESTATIC,
                            "cn/com/bricks/dynamic/weaving/print/PrinterFactory",
                            "exportParameters",
                            "(Ljava/lang/String;Ljava/lang/String;)V");
                }

            }
        }
//        this.visitTypeInsn(NEW, "java/lang/Object");
//        this.visitInsn(DUP);
//        this.visitInsn(ACONST_NULL);
//        this.visitVarInsn(ASTORE, 1);
//        this.visitTryCatchBlock(l0, l1, l2, "java/lang/Throwable");
//        this.visitLabel(l0);
//        this.visitLabel(l1);
        super.visitCode();
    }

    private void setReturnValue(Type type) {
        switch (type.getSort()) {
            case Type.BOOLEAN:
                this.visitVarInsn(ILOAD, returnIdx);
                break;

            case Type.LONG:
                this.visitInsn(ICONST_0);
                this.visitVarInsn(LLOAD, returnIdx);
            case Type.INT:
                this.visitVarInsn(ILOAD, returnIdx);
                break;
//            case Type.VOID:
//                break;
            default:
                this.visitVarInsn(ALOAD, returnIdx);
                break;
        }
    }

    private void setReturnValueVal(Type type) {
        switch (type.getSort()) {
            case Type.BOOLEAN:
                this.visitVarInsn(ISTORE, returnIdx);
                break;
            case Type.LONG:
                this.visitVarInsn(LSTORE, returnIdx);
            case Type.INT:
                this.visitVarInsn(ISTORE, returnIdx);
                break;
//            case Type.VOID:
//                break;
            default:
                this.visitVarInsn(ASTORE, returnIdx);
                break;
        }
    }

    private void setReturnVar(Type type) {
        switch (type.getSort()) {
            case Type.BOOLEAN:
                this.visitInsn(ICONST_0);
                this.visitVarInsn(ISTORE, returnIdx);
//                this.visitMethodInsn(INVOKESPECIAL, "java/lang/Integer", "<init>", "()V");
//                this.visitVarInsn(ISTORE, returnIdx);
                break;

            case Type.LONG:
                this.visitInsn(ICONST_0);
                this.visitVarInsn(LSTORE, returnIdx);
            case Type.INT:
                this.visitInsn(ICONST_0);
                this.visitVarInsn(ISTORE, returnIdx);
                break;
//            case Type.VOID:
//                break;
            default:
                this.visitTypeInsn(NEW, "java/lang/Object");
                this.visitInsn(DUP);
                this.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");

                this.visitVarInsn(ASTORE, returnIdx);
                break;
        }
    }

    @Override
    public void visitInsn(int inst) {
        if (!isNoWeavingMethod) {
            if ((inst >= IRETURN && inst <= RETURN)) {
                if (isReplaceMethod) {
//
//                this.visitVarInsn(ASTORE, 1);

//                super.visitInsn(inst);
//                    this.visitVarInsn(ASTORE, returnIdx);
                    this.visitLabel(l1);
                    super.visitInsn(ARETURN);
//                    Label l3 = new Label();
//                    this.visitJumpInsn(GOTO, l2);
                    this.visitLabel(l1);
                    this.visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[]{"java/lang/Throwable"});
//            this.visitLdcInsn(_className);
//            this.visitLdcInsn(_methodName);
                    this.visitVarInsn(ASTORE, 1);
////
                    this.visitVarInsn(ALOAD, 1);
                    this.visitMethodInsn(INVOKESTATIC,
                            "cn/com/bricks/dynamic/weaving/print/PrinterFactory",
                            "exception",
                            "(Ljava/lang/Throwable;)V");
                    this.visitVarInsn(ALOAD, 0);
                    this.visitMethodInsn(INVOKESTATIC,
                            "byd/wms/factory/OperatFactory",
                            "getServiceObject",
                            "(Ljava/lang/Class;)Ljava/lang/Object;");
//                    this.visitVarInsn(ASTORE, 2);
//
//                    this.visitVarInsn(ALOAD, 2);
//                    this.visitLabel(l2);
//                    this.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
                } else {
                    Type rtn = method.getReturnType();

                    if (rtn == Type.VOID_TYPE) {
                        this.visitLdcInsn(_className);
                        this.visitLdcInsn(method.toString());
                        this.visitMethodInsn(INVOKESTATIC,
                                "cn/com/bricks/dynamic/weaving/print/PrinterFactory",
                                "exportReturn",
                                "(Ljava/lang/String;Ljava/lang/String;)V");
                    } else {
                        setReturnValueVal(rtn);
//                        this.visitVarInsn(ASTORE, returnIdx);
//                            this.visitInsn(POP);
//                            returnIdx++;
                        this.visitLdcInsn(_className);
                        this.visitLdcInsn(method.toString());
                        this.setVarType(rtn, returnIdx);
                        this.visitMethodInsn(INVOKESTATIC,
                                "cn/com/bricks/dynamic/weaving/print/PrinterFactory",
                                "exportReturn",
                                "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V");
                        setReturnValue(rtn);

                    }
//            this.visitLabel(l2);
//            this.visitLdcInsn(_className);
//            this.visitLdcInsn(_methodName);
//            this.setVarType(rtn, stack);
//            this.visitMethodInsn(INVOKESTATIC,
//                    "byd/wms/loader/weaving/TestXMLOutput",
//                    "exceptions",
//                    "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V");
                }
            } else if (inst == ATHROW) {
                if (skipFinally++ == 0) {
                    this.visitVarInsn(ASTORE, returnIdx);
                    this.visitLdcInsn(_className);
                    this.visitLdcInsn(method.toString());
                    this.visitVarInsn(ALOAD, returnIdx);
                    this.visitMethodInsn(INVOKESTATIC,
                            "cn/com/bricks/dynamic/weaving/print/PrinterFactory",
                            "exceptions",
                            "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V");
                    this.visitVarInsn(ALOAD, returnIdx);
                }

//                    this.visitLabel(l2);
//                    this.visitVarInsn(ASTORE, 1);
//                    this.visitVarInsn(ALOAD, 0);
//                    this.visitMethodInsn(INVOKESTATIC,
//                            "byd/wms/factory/OperatFactory",
//                            "getServiceObject",
//                            "(Ljava/lang/Class;)Ljava/lang/Object;");
//                    inst = ARETURN;
            }
        }
//        if ((inst >= IRETURN && inst <= RETURN)) {
////            this.visitVarInsn(ASTORE, 2);
////            super.visitInsn(inst);
//            this.visitLabel(l2);
//            this.visitVarInsn(ASTORE, 2);
////            this.visitLdcInsn(_className);
////            this.visitLdcInsn(_methodName);
//            this.visitVarInsn(ALOAD, 2);
//            this.visitMethodInsn(INVOKESTATIC,
//                    "byd/wms/loader/weaving/TestXMLOutput",
//                    "exception",
//                    "(Ljava/lang/Throwable;)V");
//            this.visitVarInsn(ALOAD, 0);
//            this.visitMethodInsn(INVOKESTATIC,
//                    "byd/wms/factory/OperatFactory",
//                    "getServiceObject",
//                    "(Ljava/lang/Class;)Ljava/lang/Object;");
//            this.visitVarInsn(ASTORE, 1);

//        }
        super.visitInsn(inst);
    }

//    @Override
//    public void visitEnd() {
//
//        super.visitEnd();
//    }
//    @Override
//    public void visitMethodInsn(int i, String string, String string1, String string2) {
//        super.visitMethodInsn(i, string, string1, string2);
//        addCount++;
//    }
    @Override
    public void visitVarInsn(int inst, int idx) {
//        stack = idx;
//        addCount = 0;
        super.visitVarInsn(inst, idx);
    }
//

//    @Override
//    public void visitMaxs(int i, int i1) {
//        super.visitMaxs(i+2, i1+1);
//    }
    /**
     * 设定返回变量数据
     *
     * @param type
     */
    private void setVarType(Type type, int idx) {
        if (type == Type.BOOLEAN_TYPE) {
            this.visitVarInsn(ILOAD, idx);
            this.visitMethodInsn(INVOKESTATIC, "java/lang/String", "valueOf", "(Z)Ljava/lang/String;");
//            this.visitMethodInsn(INVOKESTATIC,
//                    "byd/wms/loader/weaving/TestXMLOutput",
//                    "turnPrimitiveType",
//                    "(Z)Ljava/lang/Object;");
        } else if (type == Type.BYTE_TYPE) {
            this.visitVarInsn(ILOAD, idx);
            this.visitMethodInsn(INVOKESTATIC, "java/lang/String", "valueOf", "(B)Ljava/lang/String;");
//            this.visitMethodInsn(INVOKESTATIC,
//                    "byd/wms/loader/weaving/TestXMLOutput",
//                    "turnPrimitiveType",
//                    "(Ljava/lang/Object;)Ljava/lang/Object;");
        } else if (type == Type.CHAR_TYPE) {
            this.visitVarInsn(ILOAD, idx);
            this.visitMethodInsn(INVOKESTATIC, "java/lang/String", "valueOf", "(C)Ljava/lang/String;");
//            this.visitMethodInsn(INVOKESTATIC,
//                    "byd/wms/loader/weaving/TestXMLOutput",
//                    "turnPrimitiveType",
//                    "(C)Ljava/lang/Object;");
        } else if (type == Type.DOUBLE_TYPE) {
            this.visitVarInsn(DLOAD, idx);
            this.visitMethodInsn(INVOKESTATIC, "java/lang/String", "valueOf", "(D)Ljava/lang/String;");
        } else if (type == Type.FLOAT_TYPE) {
            this.visitVarInsn(FLOAD, idx);
            this.visitMethodInsn(INVOKESTATIC, "java/lang/String", "valueOf", "(F)Ljava/lang/String;");
        } else if (type == Type.INT_TYPE) {
            this.visitVarInsn(ILOAD, idx);
            this.visitMethodInsn(INVOKESTATIC, "java/lang/String", "valueOf", "(I)Ljava/lang/String;");
//            this.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
//            this.visitMethodInsn(INVOKESTATIC,
//                    "byd/wms/loader/weaving/TestXMLOutput",
//                    "turnPrimitiveType",
//                    "(I)Ljava/lang/Object;");
        } else if (type == Type.LONG_TYPE) {
            this.visitVarInsn(LLOAD, idx);
            this.visitMethodInsn(INVOKESTATIC, "java/lang/String", "valueOf", "(J)Ljava/lang/String;");
        } else if (type.getSort() == Type.ARRAY) {
            this.visitVarInsn(ALOAD, idx);
        } else {
            this.visitVarInsn(ALOAD, idx);
        }
    }
}
