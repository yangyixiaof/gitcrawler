/**
 * <p>Title: �̼߳����</p>
 * <p>Description: ��ʵ��ʹ�ö����̹߳�ͬ��������һ��ʵ�����ǡ�</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: mainThread.java</p>
 * @author �Ž�
 * @version 1.0
 */
public class mainThread{
  public static int flag = 0;
  int count = 10;
/**
 *<br>����˵����������
 *<br>���������
 *<br>�������ͣ�
 */
  public static void main(String[] arg){
    new mainThread();
  }
/**
 *<br>����˵�����������������������̡߳�
 *<br>���������
 *<br>�������ͣ�
 */
  mainThread(){
    thread1 t1 = new mainThread.thread1(this.count);
    thread2 t2 = new mainThread.thread2(this.count);
    //�������߳�
    t1.start();
    t2.start();
    //���߳�һ���ȹ�����
    flag = 1;
  }
/**
 *<br>��˵�����ڲ��࣬�̳���Thread��
 *<br>��������ʵ���������ÿ��ǰ��Ŀո�
 */
  class thread1 extends Thread{
    int count1 = 0; 
    thread1(int i){
      count1 = i;
    }
    public void run(){
      
      while(true){
      	if(count1<=0) break;
      	if(mainThread.flag==1){
         
         for(int i=0;i<count1;i++){
           System.out.print(" ");
         }
         count1--;
         mainThread.flag=2;
        }
      }
    }
  }
/**
 *<br>��˵�����ڲ��࣬�̳���Thread��
 *<br>��������ʵ���������ÿ�еڡ�*���š����ṩ���С�
 */
  class thread2 extends Thread{
    int count2 = 0; 
    thread2(int i){
      count2 = i;
    }
    public void run(){
      int count = 0;
      while(true){
        if(count>=count2) break;
        if(mainThread.flag==2){
         for(int i=0;i<(count*2+1);i++){
           System.out.print("*");
         }
         System.out.print("\n");
         count++;
         mainThread.flag=1;
        }
      }
    }
  } 
}