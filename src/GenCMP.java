import static utils.Utilities.writeFile;

import org.objectweb.asm.*;

/***
 * Class generates a comparison class that takes predefined numbers and returns 1, 0, or -1 depending if the first value is greater, equal, or less than the second
 * value. Values are double, long, and int.
 * @author Jacob Morris, Aaron Bone
 * @version 1.2.3
 */
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
            
            /**
             * block takes two doubles and uses DCMPG to check greater/less than values
             */
            mv.visitLdcInsn((Double)15.8);//first value
            mv.visitVarInsn(Opcodes.DSTORE,1);
            mv.visitLdcInsn((Double)15.9);//second value
            mv.visitVarInsn(Opcodes.DSTORE,3);
            mv.visitVarInsn(Opcodes.DLOAD,1);
            mv.visitVarInsn(Opcodes.DLOAD,3);
            mv.visitInsn(Opcodes.DCMPG);//pops last two numbers on stack and checks if one is greater/less than
            mv.visitVarInsn(Opcodes.ISTORE,5);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");//print out result
            mv.visitVarInsn(Opcodes.ILOAD, 5);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
            
            /**
             * block takes two longs and uses LCMPG to check greater/less than values
             */
            mv.visitLdcInsn((long)24);//first value
            mv.visitVarInsn(Opcodes.LSTORE,7);
            mv.visitLdcInsn((long)7);//second value
            mv.visitVarInsn(Opcodes.LSTORE,9);
            mv.visitVarInsn(Opcodes.LLOAD,7);
            mv.visitVarInsn(Opcodes.LLOAD,9);
            mv.visitInsn(Opcodes.LCMP);//pops last two numbers on stack and checks if one is greater/less than
            mv.visitVarInsn(Opcodes.ISTORE,11);
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");//print out result
            mv.visitVarInsn(Opcodes.ILOAD, 11);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
            
            
            	
            /**
             * block takes two ints and uses two if statements to check greater/less than values while utilizing labels to navigate around
             */
        	Label label1 = new Label();
        	Label label2 = new Label();
        	Label label3 = new Label();
        	
            mv.visitLdcInsn((int)5);//first value
            mv.visitVarInsn(Opcodes.ISTORE,12);
            mv.visitLdcInsn((int)10);//second value
            mv.visitVarInsn(Opcodes.ISTORE,13);
            mv.visitVarInsn(Opcodes.ILOAD,12);
            mv.visitVarInsn(Opcodes.ILOAD,13);            
            mv.visitJumpInsn(Opcodes.IF_ICMPLE, label1);//check if val1 is less than or equal to value 2, if so then go to label 1
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");//val 1 is greater than val2, so print 1
            mv.visitInsn(Opcodes.ICONST_1);   
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
	        mv.visitJumpInsn(Opcodes.GOTO, label2);//go to the end at label 2
            
           
            mv.visitLabel(label1);
            mv.visitVarInsn(Opcodes.ILOAD,12);
            mv.visitVarInsn(Opcodes.ILOAD,13);
            mv.visitJumpInsn(Opcodes.IF_ICMPNE, label3);//check if val1 is not equal to value 2, if so then go to label 3
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");//val 1 is equal to val2, so print 0
            mv.visitInsn(Opcodes.ICONST_0);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
	        mv.visitJumpInsn(Opcodes.GOTO, label2);//go to the end at label 2
	        
	        mv.visitLabel(label3);
	        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");//val 1 is less than val 2, so print -1
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
