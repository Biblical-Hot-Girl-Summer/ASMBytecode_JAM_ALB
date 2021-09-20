import static utils.Utilities.writeFile;

import org.objectweb.asm.*;

public class GenInput {

	public static void main(String[] args) {
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC,"UserInput", null, "java/lang/Object",null);
        
        {
			MethodVisitor mv=cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
			mv.visitCode();
			mv.visitVarInsn(Opcodes.ALOAD, 0); //load the first local variable: this
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V",false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(1,1);
			mv.visitEnd();
		}

        {
            MethodVisitor mv=cw.visitMethod(Opcodes.ACC_PUBLIC+Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
            mv.visitCode();
            Label label1 = new Label();
            Label label2 = new Label();
            mv.visitLdcInsn((int) 0);
            mv.visitVarInsn(Opcodes.ISTORE, 1);
            mv.visitLdcInsn((int) 0);
            mv.visitVarInsn(Opcodes.ISTORE, 2);
            
            mv.visitTypeInsn(Opcodes.NEW, "java/util/Scanner");
            mv.visitInsn(Opcodes.DUP);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "in", "Ljava/io/InputStream;");
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/util/Scanner", "<init>", "(Ljava/io/InputStream;)V", false);
            mv.visitVarInsn(Opcodes.ASTORE,3);
            
            
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn((String)"Enter 5 Ints:");
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            
            
            
            mv.visitLabel(label2);
            mv.visitVarInsn(Opcodes.ILOAD, 1);
            mv.visitInsn(Opcodes.ICONST_5);
            mv.visitJumpInsn(Opcodes.IF_ICMPGE, label1);
            
            mv.visitVarInsn(Opcodes.ALOAD,3);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/util/Scanner", "nextInt", "()I",false);
            mv.visitVarInsn(Opcodes.ISTORE,4);
           
            
            
            
            mv.visitVarInsn(Opcodes.ILOAD,2);
            mv.visitVarInsn(Opcodes.ILOAD,4);
            mv.visitInsn(Opcodes.IADD);
            mv.visitVarInsn(Opcodes.ISTORE,2);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.ILOAD, 2);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
            
            mv.visitIincInsn(1, 1);
            
            mv.visitJumpInsn(Opcodes.GOTO, label2);
            
            
            mv.visitLabel(label1);
            
            
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0,0);
            mv.visitEnd();
        }

        cw.visitEnd();

        byte[] b = cw.toByteArray();

        writeFile(b,"UserInput.class");
        
        System.out.println("Done!");
	}

}
