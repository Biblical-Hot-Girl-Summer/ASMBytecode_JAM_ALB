import static utils.Utilities.writeFile;

import org.objectweb.asm.*;

public class GenCMP {

	public static void main(String[] args) {
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC,"CompareNumbers", null, "java/lang/Object",null);
        
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
            mv.visitLdcInsn((Double)15.8);
            mv.visitVarInsn(Opcodes.DSTORE,1);
            mv.visitLdcInsn((Double)15.9);
            mv.visitVarInsn(Opcodes.DSTORE,3);
            mv.visitVarInsn(Opcodes.DLOAD,1);
            mv.visitVarInsn(Opcodes.DLOAD,3);
            mv.visitInsn(Opcodes.DCMPG);
            mv.visitVarInsn(Opcodes.ISTORE,5);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.ILOAD, 5);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
            
            mv.visitLdcInsn((long)24);
            mv.visitVarInsn(Opcodes.LSTORE,7);
            mv.visitLdcInsn((long)7);
            mv.visitVarInsn(Opcodes.LSTORE,9);
            mv.visitVarInsn(Opcodes.LLOAD,7);
            mv.visitVarInsn(Opcodes.LLOAD,9);
            mv.visitInsn(Opcodes.LCMP);
            mv.visitVarInsn(Opcodes.ISTORE,11);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitVarInsn(Opcodes.ILOAD, 11);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
            
            
            	
        	Label label1 = new Label();
        	Label label2 = new Label();
        	Label label3 = new Label();
        	
            mv.visitLdcInsn((int)5);
            mv.visitVarInsn(Opcodes.ISTORE,12);
            mv.visitLdcInsn((int)10);
            mv.visitVarInsn(Opcodes.ISTORE,13);
            mv.visitVarInsn(Opcodes.ILOAD,12);
            mv.visitVarInsn(Opcodes.ILOAD,13);            
            mv.visitJumpInsn(Opcodes.IF_ICMPLE, label1);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitInsn(Opcodes.ICONST_1);   
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
	        mv.visitJumpInsn(Opcodes.GOTO, label2);
            
           
            mv.visitLabel(label1);
            mv.visitVarInsn(Opcodes.ILOAD,12);
            mv.visitVarInsn(Opcodes.ILOAD,13);
            mv.visitJumpInsn(Opcodes.IF_ICMPNE, label3);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitInsn(Opcodes.ICONST_0);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
	        mv.visitJumpInsn(Opcodes.GOTO, label2);
	        
	        mv.visitLabel(label3);
	        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitInsn(Opcodes.ICONST_M1);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
            
            mv.visitLabel(label2);
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0,0);
            mv.visitEnd();
        }

        cw.visitEnd();

        byte[] b = cw.toByteArray();

        writeFile(b,"CompareNumbers.class");
        
        System.out.println("Done!");
	}

}
