import static utils.Utilities.writeFile;

import org.objectweb.asm.*;

/***
 * Class generates a while loop class that counts from 0 to 10, printing the number after each iteration
 * @author Jacob Morris
 * @version 1.2.3
 */

public class GenWhile {

	public static void main(String[] args) {
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC,"WhileStuff", null, "java/lang/Object",null);
        
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
             * While loop works by having a starting value and comparing it to 10, if start value is less than 10 then it increments the val by 1 and loops until
             * it reaches 10
             */
            Label label1 = new Label();
            Label label2 = new Label();
            
            mv.visitLdcInsn((int) 0);//starting value
            mv.visitVarInsn(Opcodes.ISTORE, 1);
            mv.visitLabel(label2);
            mv.visitVarInsn(Opcodes.ILOAD, 1);
            mv.visitIntInsn(Opcodes.BIPUSH, 10);//second value
            mv.visitJumpInsn(Opcodes.IF_ICMPGE, label1);//if val1 is greater/equal to second value then go to label 1
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");//val1 is less than second value, so print result
            mv.visitVarInsn(Opcodes.ILOAD, 1);  
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
            mv.visitIincInsn(1, 1);//add 1 to val1
            mv.visitJumpInsn(Opcodes.GOTO, label2);//loop isn't over, go to label2
            mv.visitLabel(label1);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");//print out last number (should be equal to second value)
            mv.visitVarInsn(Opcodes.ILOAD, 1);  
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
            
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0,0);
            mv.visitEnd();
        }

        cw.visitEnd();

        byte[] b = cw.toByteArray();

        writeFile(b,"WhileStuff.class");
        
        System.out.println("Done!");
	}

}
