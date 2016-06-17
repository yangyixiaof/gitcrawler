/**
 * <p>Title: ʵ��Runnable�ӿڣ�����̡߳�</p>
 * <p>Description: ͨ��ʵ��Runnable�ӿ�������Լ����̣߳�t2����</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: twothread.java</p>
 * @author �Ž�
 * @version 1.0
 */
public class twothread implements Runnable {
/**
 *<br>����˵������������ʵ���̣߳�����������̡߳�
 *<br>���������
 *<br>�������ͣ�
 */
 twothread() { 
   //��ȡ��ǰ���߳�
   Thread t1 =Thread.currentThread(); 
   t1.setName("The first main thread"); 
   System.out.println("The running thread:" + t1); 
   //ͨ�������ࣨRunnable�ӿڣ������ƹ���һ���߳�
   Thread t2 = new Thread(this,"the second thread"); 
   System.out.println("creat another thread"); 
   //�����߳�
   t2.start(); 
   try { 
     System.out.println("first thread will sleep"); 
     Thread.sleep(3000); 
   }catch (InterruptedException e) {
     System.out.println("first thread has wrong"); 
   } 
   System.out.println("first thread exit"); 
 } 
/**
 *<br>����˵�����߳����塣ʵ��run������
 *<br>���������
 *<br>�������ͣ�
 */
 public void run() { 
   try { 
     for (int i=0;i<5;i++) { 
       System.out.println("Sleep time for thread 2:"+i); 
       Thread.sleep(1000); 
     }
  } catch (InterruptedException e) {
    System.out.println("thread has wrong"); 
  }
  System.out.println("second thread exit"); 
 } 
/**
 *<br>����˵����������
 *<br>���������
 *<br>�������ͣ�
 */
 public static void main(String args[]) { 
   new twothread(); 
 } 
}

