import java.sql.Connection;
import java.sql.DriverManager;


public class CreateOracleJoin {

public boolean Connection(){
    try {
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");  //�������ݿ�����
        System.out.println("���ݿ��������سɹ�����");
        Connection con = DriverManager.getConnection("jdbc:odbc:data","system","aaa");   //��ȡ���ݿ�����
        if(con != null){        //�ж�Connection�����Ƿ�Ϊ��
            System.out.println("�ɹ�����oracle���ݿ⽨�����ӣ���");  //������ʾ��Ϣ
        }
        return true;
    } catch (Exception e) {       
        e.printStackTrace();
        return false;
    }
}
    public static void main(String[] args) {
        CreateOracleJoin createJoin = new CreateOracleJoin();
        createJoin.Connection();
    }
    
}
