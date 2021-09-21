import static utils.Utilities.writeFile;

import org.objectweb.asm.*;

/***
 * Class generates a subtractor class that takes predefined numbers and subtracts the first value by the second. Does it three times with double, int, and long.
 * @author Jacob Morris, Aaron Bone
 * @version 1.2.3
 */

public class GenSub {

	public static void main(String[] args) {
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC,"SubtractNumbers", null, "java/lang/Object",null);
        
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
             * Block takes two doubles and subtracts the first by the second
             */
            mv.visitLdcInsn((Double)50.8);//first value
            mv.visitVarInsn(Opcodes.DSTORE,1);
            mv.visitLdcInsn((Double)20.3);//second value
            mv.visitVarInsn(Opcodes.DSTORE,3);
            mv.visitVarInsn(Opcodes.DLOAD,1);
            mv.visitVarInsn(Opcodes.DLOAD,3);
            mv.visitInsn(Opcodes.DSUB);//pop top two values and subtract val2 from val1
            mv.visitVarInsn(Opcodes.DSTORE,5);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");//print result
            mv.visitVarInsn(Opcodes.DLOAD, 5);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(D)V", false);
            
            /**
             * Block takes two int and subtracts the first by the second
             */
            mv.visitLdcInsn((int)10);//first value
            mv.visitVarInsn(Opcodes.ISTORE,7);
            mv.visitLdcInsn((int)6);//second value
            mv.visitVarInsn(Opcodes.ISTORE,8);
            mv.visitVarInsn(Opcodes.ILOAD,7);
            mv.visitVarInsn(Opcodes.ILOAD,8);
            mv.visitInsn(Opcodes.ISUB);//pop top two values and subtract val2 from val1
            mv.visitVarInsn(Opcodes.ISTORE,9);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");//print result
            mv.visitVarInsn(Opcodes.ILOAD, 9);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
            
            /**
             * Block takes two long and subtracts the first by the second
             */
            mv.visitLdcInsn((long)24);//first value
            mv.visitVarInsn(Opcodes.LSTORE,10);
            mv.visitLdcInsn((long)7);//second value
            mv.visitVarInsn(Opcodes.LSTORE,12);
            mv.visitVarInsn(Opcodes.LLOAD,10);
            mv.visitVarInsn(Opcodes.LLOAD,12);
            mv.visitInsn(Opcodes.LSUB);//pop top two values and subtract val2 from val1
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

        writeFile(b,"SubtractNumbers.class");
        
        System.out.println("Done!");
	}

}
