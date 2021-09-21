import static utils.Utilities.writeFile;

import org.objectweb.asm.*;

/***
 * Class generates a if, then, else class that takes an int and checks if it equals 0 or not.
 * @author Jacob Morris, Aaron Bone
 * @version 1.2.3
 */

public class GenIfThen {

	public static void main(String[] args) {
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC,"IfThen", null, "java/lang/Object",null);
        
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
             * Take a value and compare to 0 and return a string stating whether or not the value equals 0 or not.
             */
            Label label1 = new Label();
            Label label2 = new Label();
            
            mv.visitLdcInsn((int) 0);//comparing value
            mv.visitVarInsn(Opcodes.ISTORE, 1);
            mv.visitVarInsn(Opcodes.ILOAD, 1);
            mv.visitJumpInsn(Opcodes.IFNE, label1);//If value does not equal 0, go to label1
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");//value equals 0, so prints out result
            mv.visitLdcInsn((String)"It does equal 0");
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            mv.visitJumpInsn(Opcodes.GOTO, label2);
            
            mv.visitLabel(label1);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");//value does not equal 0, prints out result
            mv.visitLdcInsn((String)"It does not equal 0");
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            mv.visitLabel(label2);
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0,0);
            mv.visitEnd();
        }

        cw.visitEnd();

        byte[] b = cw.toByteArray();

        writeFile(b,"IfThen.class");
        
        System.out.println("Done!");
	}

}
