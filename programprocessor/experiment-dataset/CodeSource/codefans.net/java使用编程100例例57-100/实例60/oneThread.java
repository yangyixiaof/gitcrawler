/**
 * <p>Title: �̳�Thread��ʵ���߳�</p>
 * <p>Description: ͨ���̳�Thread�࣬ʵ����run������ʵ���Լ����߳�</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: oneThread.java</p>
 * @author �Ž�
 * @version 1.0
 */
public class oneThread extends Thread {
/**
 *<br>����˵����������������û��ʹ��
 *<br>���������
 *<br>�������ͣ�
 */
public oneThread() {
   
 }
/**
 *<br>����˵�����̳�Thread�����ʵ�ֵķ�����������start����ʱ���б�����
 *<br>���������
 *<br>�������ͣ�
 */
 public void run() {
  System.out.println("...............oneThread begining................");
  int flag = 0;
  while(true) {
  	if(flag==20){
  	  System.out.println("\n...............oneThread end............. ");
  	  break;
  	}
     for(int i=0;i<flag;i++)
       System.out.print("*");
     System.out.println("");
     flag++;
   try{
     //˯��0.1��
     sleep(100);
   }catch(Exception e){
   }   
  }
 }
/**
 *<br>����˵�������������������߳�
 *<br>���������
 *<br>�������ͣ�
 */
 public static void main(String args[]) {
    new oneThread().start();
 }
}
