import static utils.Utilities.writeFile;

import org.objectweb.asm.*;

/***
 * Class generates a multiplier class that takes predefined numbers and multiplies the first value by the second. Does it three times with double, int, and long.
 * @author Jacob Morris, Aaron Bone
 * @version 1.2.3
 */

public class GenMult {

	public static void main(String[] args) {
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC,"MultNumbers", null, "java/lang/Object",null);
        
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
            
            /**
             * Block takes two doubles and multiplies the first by the second
             */
            
            mv.visitLdcInsn((Double)2.25);//first value
            mv.visitVarInsn(Opcodes.DSTORE,1);
            mv.visitLdcInsn((Double)420.21);//second value
            mv.visitVarInsn(Opcodes.DSTORE,3);
            mv.visitVarInsn(Opcodes.DLOAD,1);
            mv.visitVarInsn(Opcodes.DLOAD,3);
            mv.visitInsn(Opcodes.DMUL);//multiply val1 by val2
            mv.visitVarInsn(Opcodes.DSTORE,5);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");//print result
            mv.visitVarInsn(Opcodes.DLOAD, 5);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(D)V", false);
            
            /**
             * Block takes two ints and multiplies the first by the second
             */
            
            mv.visitLdcInsn((int)20);//first value
            mv.visitVarInsn(Opcodes.ISTORE,7);
            mv.visitLdcInsn((int)3);//second value
            mv.visitVarInsn(Opcodes.ISTORE,8);
            mv.visitVarInsn(Opcodes.ILOAD,7);
            mv.visitVarInsn(Opcodes.ILOAD,8);
            mv.visitInsn(Opcodes.IMUL);//multiply val1 by val2
            mv.visitVarInsn(Opcodes.ISTORE,9);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");//print result
            mv.visitVarInsn(Opcodes.ILOAD, 9);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
            
            /**
             * Block takes two longs and multiplies the first by the second
             */
            
            mv.visitLdcInsn((long)5);//first value
            mv.visitVarInsn(Opcodes.LSTORE,10);
            mv.visitLdcInsn((long)25);//second value
            mv.visitVarInsn(Opcodes.LSTORE,12);
            mv.visitVarInsn(Opcodes.LLOAD,10);
            mv.visitVarInsn(Opcodes.LLOAD,12);
            mv.visitInsn(Opcodes.LMUL);//multiply val1 by val2
            mv.visitVarInsn(Opcodes.LSTORE,14);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");//print result
            mv.visitVarInsn(Opcodes.LLOAD, 14);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(J)V", false);
            
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0,0);
            mv.visitEnd();
        }

        cw.visitEnd();

        byte[] b = cw.toByteArray();

        writeFile(b,"MultNumbers.class");
        
        System.out.println("Done!");
	}

}
