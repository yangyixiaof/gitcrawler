/**
 * <p>Title: �߳̿���</p>
 * <p>Description: ʵ�ֶ��̵߳Ŀ��ƣ��жϡ����𡢻ָ���ֹͣ</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: threadCtrl.java</p>
 * @author �Ž�
 * @version 1.0
 */
public class threadCtrl{
  public static void main(String [] main){
     new threadCtrl();
  }
/**
 *<br>����˵���������������������߳�
 *<br>���������
 *<br>�������ͣ�
 */
  threadCtrl(){
    try{
     Thread tm = Thread.currentThread();
     threadchild td = new threadchild();
     td.start();
     tm.sleep(500);
     System.out.println("interrupt child thread");
     td.interrupt();

     System.out.println("let child thread wait!");
     //td.wait();
     //td.suspend();
     tm.sleep(1000);

     System.out.println("let child thread working");
     td.fauxresume();
     //td.resume();
     tm.sleep(1000);
     td.runflag = false;
     System.out.println("main over..............");
   }catch(InterruptedException ie){
   	 System.out.println("inter main::"+ie);
   }catch(Exception e){
     System.out.println("main::"+e);
   }
  }

}
/**
 *<br>��˵���������߳���
 */
  class threadchild extends Thread {
    boolean runflag = true;
    boolean suspended = true;
    threadchild(){
    }
    public synchronized void fauxresume(){
      suspended = false;
      notify();
    } 
    public  void run(){
      while(runflag){
        System.out.println("I am working..............");
        try{
          sleep(1000);
        }catch(InterruptedException e){
          System.out.println("sleep::"+e);
        }
       synchronized(this){
        try{
          if(suspended)
           wait();
        }catch(InterruptedException e){
          System.out.println("wait::"+e);
        }
       }
      }
      System.out.println("thread over...........");
    }
  }