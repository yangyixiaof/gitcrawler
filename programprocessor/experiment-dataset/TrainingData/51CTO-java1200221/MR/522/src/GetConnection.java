import java.sql.Connection;
import java.sql.DriverManager;


public class GetConnection {
    
private Connection conn;        //����Connection����
public String con(){       
    try {
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");  //����ODBC���ݿ�����
        conn = DriverManager.getConnection("jdbc:odbc:db_database22","sa","");  //��ȡ���ݿ�����
        return "���ݿ����ӳɹ���";  //�������Ӷ���
    } catch (Exception e) {          
        e.printStackTrace();
        return "���ݿ�����ʧ�ܣ�";
    }
}
    public static void main(String[] args) {
       GetConnection getConn = new GetConnection();
       System.out.println(getConn.con());
        
    }    
}
