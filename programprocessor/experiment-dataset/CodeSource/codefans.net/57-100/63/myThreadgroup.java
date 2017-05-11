
/**
 * <p>Title: �߳���Ⱥ</p>
 * <p>Description: ͨ���߳����������Ķ���̡߳�</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: myThreadgroup.java</p>
 * @author �Ž�
 * @version 1.0
 */
public class myThreadgroup extends Thread {
  public static int flag=1;
  ThreadGroup tgA;
  ThreadGroup tgB;
/**
 *<br>����˵����������
 *<br>���������
 *<br>�������ͣ�
 */
  public static void main(String[] args){
    myThreadgroup dt = new myThreadgroup();
    //�����߳���A
    dt.tgA = new ThreadGroup("A");
    //�����߳���B
    dt.tgB = new ThreadGroup("B");
    for(int i=1;i<3;i++)
      new thread1(dt.tgA,i*1000,"one"+i);
    for(int i=1;i<3;i++)
      new thread1(dt.tgB,1000,"two"+i);
    //�������̺߳������߳���
    dt.start();
  }
/**
 *<br>����˵��������run�����������߳���
 *<br>���������
 *<br>�������ͣ�
 */
  public void run(){
    try{
     this.sleep(5000);
     this.tgB.checkAccess();
     //ֹͣ�߳���B��
     this.tgB.stop();
     System.out.println("**************tgB stop!***********************");
     this.sleep(1000);
     //����߳���A�Ƿ���Ը���
     this.tgA.checkAccess();
     //ֹͣ�߳���A
     this.tgA.stop();
     System.out.println("**************tgA stop!***********************");
     
    }catch(SecurityException es){
       System.out.println("**"+es);
    }catch(Exception e){
       System.out.println("::"+e);
    }
   }
}
/**
 * <p>Title: �߳���</p>
 * <p>Description: ͨ���������Ĳ�����ʵ�ֲ�ͬ���߳�</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: thread1.java</p>
 * @author �Ž�
 * @version 1.0
 */
class thread1 extends Thread {
    int pauseTime;
    String name;
    public thread1(ThreadGroup g,int x, String n) {
        super(g,n);
        pauseTime = x;
        name = n;
        start();
    }
/**
 *<br>����˵�������븲�ǵķ�����
 *<br>���������
 *<br>�������ͣ�
 */
   public void run () 
   {
     while(true) {
      try {
          System.out.print(name+"::::");
          this.getThreadGroup().list();//��ȡ�߳�����Ϣ
          Thread.sleep(pauseTime);
      } catch(Exception e) {
          System.out.println(e);
      }
    }
   }
}
