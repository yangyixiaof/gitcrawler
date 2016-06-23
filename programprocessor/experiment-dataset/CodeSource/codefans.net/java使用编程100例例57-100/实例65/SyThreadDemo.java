/**
 * <p>Title: �߳�ͬ��</p>
 * <p>Description: ͨ��ʹ��ͬ����ʵ�ֶԹ������ݵĲ���</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: SyThreadDemo.java</p>
 * @author �Ž�
 * @version 1.0
 */
/**
 *<br>��˵����������
 *<br>�������������������̣߳�����������
 */
public class SyThreadDemo 
{ 
 public static void main (String [] args) 
 { 
  trade ft = new trade (); 
  addThread tt1 = new addThread (ft, "add"); 
  decThread tt2 = new decThread (ft, "dec"); 
  tt1.start (); 
  tt2.start (); 
 } 
}
/**
 *<br>��˵����ͬ����
 *<br>�������������湲�����ݣ�
 */
class trade 
{ 
  private String transName; 
  private double amount; 
/**
 *<br>����˵������������
 *<br>���������String transName ��������
 *<br>���������double amount �ʽ�����
 *<br>�������ͣ�
 */
  synchronized void update (String transName, double amount) 
  { 
   this.transName = transName; 
   this.amount = amount; 
   System.out.println (this.transName + " " + this.amount); 
  } 
} 
/**
 *<br>��˵��������ʽ�
 *<br>�������������̣߳�����trade.update�����������޸�����
 */
class addThread extends Thread 
{ 
  private trade ft; 
  addThread (trade ft, String name) 
  { 
   super (name);
   this.ft = ft; 
  } 
  public void run () 
  { 
   for (int i = 0; i < 10; i++) 
     ft.update ("add", 2000.0); 
 } 
} 
/**
 *<br>��˵���������ʽ�
 *<br>�������������̣߳�����trade.update�����������޸�����
 */
class decThread extends Thread 
{ 
  private trade fd; 
  decThread (trade fd, String name) 
  { 
   super (name);
   this.fd = fd; 
  } 
/**
 *<br>����˵�����߳�����
 *<br>���������
 *<br>�������ͣ�
 */
  public void run () 
  { 
   for (int i = 0; i < 10; i++) 
     fd.update ("dec", -2000.0); 

 } 
} 

