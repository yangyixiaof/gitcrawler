/**
 * <p>Title: �������߳�</p>
 * <p>Description: ʹ�ù��������������̡߳�</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: multiThread.java</p>
 * @author �Ž�
 * @version 1.0
 */
public class multiThread 
{ 
/**
 *<br>����˵����������
 *<br>���������
 *<br>�������ͣ�
 */
 public static void main (String [] args){ 
    new multiThread();
  }
/**
 *<br>����˵�������������������̣߳����������ǡ�
 *<br>���������
 *<br>�������ͣ�
 */
  multiThread(){
    for (int i = 0; i < 5; i++){
      System.out.println("Creating thread "+i);
      innThread mt = new innThread (i); 
      mt.start (); 
    }
  }
/**
 *<br>��˵�����ڲ���ͨ���̳�Threadʵ���߳� 
 *<br>��������ͨ������������������ͬ���߳�
 */     
 class innThread extends Thread 
 { 
  int count;
  innThread(int i){
     count=i;
  }
/**
 *<br>����˵�����ڲ����߳����壬�̳�Thread����ʵ�ֵķ�����
 *<br>���������
 *<br>�������ͣ�
 */
  public void run () 
  { 
    System.out.println("now "+count+" thread is beginning..... ");
    try{
      sleep(10-count);
    }catch(Exception e){
      System.out.println(e);
    }
    System.out.println("\n"+count+" thread is end!");
  } 
 } 
}
