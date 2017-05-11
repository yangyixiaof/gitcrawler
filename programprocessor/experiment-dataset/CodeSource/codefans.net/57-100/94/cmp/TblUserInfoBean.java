package cmp;
import javax.ejb.*;

/**
 * <p>Title: CMPʵ�������</p>
 * <p>Description: CMPʵ���࣬����ʵ��EntityBean</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: TblUserInfoBean.java</p>
 * @author �Ž�
 * @version 1.0
 */
public abstract class TblUserInfoBean implements EntityBean {
  EntityContext entityContext;
/*����˵��������ʵ�ֵķ�������TblUserInfoHome��Create������Ӧ��
*����������������ͬ�����ͻ��˵���"TblUserInfoHome.create()"����ʱ��
*EJB�ҵ���Ӧ��ʵ��������ejbCreate������
*����CMP��ejbCreate������null������bean�Թ���EJB��˵���򷵻��������͡�
* @������java.lang.String name �û���
* @������java.lang.String phone ��ϵ�绰
* @������java.lang.String home ��ͥסַ
* @������java.sql.Date brithday ��������
* @�쳣��CreateException ����EJB����ʱ�׳�
*/
  public java.lang.Integer ejbCreate(java.lang.String name, java.lang.String phone, java.lang.String home, java.sql.Date brithday) throws CreateException {
    setName(name);
    setPhone(phone);
    setHome(home);
    setBrithday(brithday);
    return null;
  }
//EJB����ʵ�ֵķ�����������û��ʹ�á�
  public void ejbPostCreate(java.lang.String name, java.lang.String phone, java.lang.String home, java.sql.Date brithday) throws CreateException {  }
//EJB����ʵ�ֵķ�����ʵ���Ƴ����ݡ�
  public void ejbRemove() throws RemoveException { }
//setXXX�����������ݿ��ֶζ�Ӧ���ṩ�����ݵĸ���
  public abstract void setId(java.lang.Integer id);
  public abstract void setName(java.lang.String name);
  public abstract void setPhone(java.lang.String phone);
  public abstract void setHome(java.lang.String home);
  public abstract void setBrithday(java.sql.Date brithday);
//getXXX�����������ݿ��ֶζ�Ӧ���ṩ���ݵ���ȡ
  public abstract java.sql.Date getBrithday();
  public abstract java.lang.String getHome();
  public abstract java.lang.String getPhone();
  public abstract java.lang.String getName();
  public abstract java.lang.Integer getId();
// EJB����ʵ�ֵķ�����
  public void ejbLoad() { }
// EJB����ʵ�ֵķ�����
  public void ejbStore() { }
// EJB����ʵ�ֵķ�����
  public void ejbActivate() { }
// EJB����ʵ�ֵķ�����
  public void ejbPassivate() { }
// EJB����ʵ�ֵķ��������ʵ��������
  public void unsetEntityContext() {
    this.entityContext = null;
  }
// EJB����ʵ�ֵķ���������ʵ��������
  public void setEntityContext(EntityContext entityContext) {
    this.entityContext = entityContext;
  }
}
