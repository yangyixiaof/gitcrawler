package e8.com;
/**
 * <p>Title: ��ʶ��</p>
 * <p>Description: ��ʾ��ʶ������ķ��ʿ���</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: </p>
 * @author �Ž�
 * @version 1.0
 */
public class classDemo1
{
//���з���
 public void mechod1()
 {
   System.out.println("����һ�����еķ������κ��඼���Է��ʡ�");
 }
//�ڱ����ķ���
 protected void mechod2()
 {
   System.out.println("����һ���ܵ������ķ�����ֻ��������Է��ʡ�");
 }
//˽�еķ���
 private void mechod3()
 {
   System.out.println("����һ��˽�еķ�����ֻ���౾��ſ��Է��ʡ�");
 }
 public static void main(String[] args){
   classDemo1 d = new classDemo1();
   d.mechod1();
   d.mechod2();
   d.mechod3();
 }  
}//end:)~