/**
 * <p>Title: ������</p>
 * <p>Description: ʹ�ü̳��࣬����������</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: </p>
 * @author �Ž�
 * @version 1.0
 */
class tree
{
/**
 *<br>����˵������������
 *<br>���������
 *<br>�������ͣ�
 */
  public void root()
  {
    String sSite = "������";
    String sFunction = "��������";
    print("λ�ã�"+sSite);
    print("���ܣ�"+sFunction);
  }
/**
 *<br>����˵������������
 *<br>���������
 *<br>�������ͣ�
 */
  public void bolo()
  {
    String sSite = "����";
    String sFunction = "��������";
    print("λ�ã�"+sSite);
    print("���ܣ�"+sFunction);
  }
/**
 *<br>����˵����������֦
 *<br>���������
 *<br>�������ͣ�
 */
  public void branch()
  {
    String sSite = "������";
    String sFunction = "��������";
    print("λ�ã�"+sSite);
    print("���ܣ�"+sFunction);
  }
/**
 *<br>����˵��������Ҷ��
 *<br>���������
 *<br>�������ͣ�
 */
  public void leaf()
  {
    String sSite = "����";
    String sFunction = "�������";
    String sColor = "��ɫ";
    print("λ�ã�"+sSite);
    print("���ܣ�"+sFunction);
    print("��ɫ��"+sColor);
  }
/**
 *<br>����˵������ʾ��Ϣ
 *<br>���������Object oPara ��ʾ����Ϣ
 *<br>�������ͣ�
 */
  public void print(Object oPara)
  {
    System.out.println(oPara);
  }
/**
 *<br>����˵����������
 *<br>���������
 *<br>�������ͣ�
 */
  public static void  main(String[] arges)
  {
    tree t = new tree();
    t.print("����һ������");
    t.print("������");
    t.root();
    t.print("���ɣ�");
    t.bolo();
    t.print("��֦��");
    t.branch();
    t.print("��Ҷ��");
    t.leaf();
  }
}
/**
 * <p>Title: ��������</p>
 * <p>Description: ���������Ĳ���</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: </p>
 * @author �Ž�
 * @version 1.0
 */
class osier extends tree
{
 /**
 *<br>����˵��������������Ҷ
 *<br>���������
 *<br>�������ͣ�
 */
  public void leaf()
  {
    super.leaf();
    String sShape = "����";
    super.print("��״��"+sShape);
  }
  /**
 *<br>����˵������չ���Ļ�
 *<br>���������
 *<br>�������ͣ�
 */
  public void flower()
  {
    print("����������û�л�����");
  }
/**
 *<br>����˵����������
 *<br>���������
 *<br>�������ͣ�
 */
  public static void  main(String[] args)
  {
    osier o = new osier();
    o.print("����������");
    o.root();
    o.print("�������ɣ�");
    o.bolo();
    o.print("������֦��");
    o.branch();
    o.print("������Ҷ��");
    o.leaf();
    o.print("��������");
    o.flower();
  }
}