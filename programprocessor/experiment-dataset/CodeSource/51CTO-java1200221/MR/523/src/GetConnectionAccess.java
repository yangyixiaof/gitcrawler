import java.sql.*;
public class GetConnectionAccess {

public boolean Connection(){
    try {
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");  //�������ݿ�����
        Connection con = DriverManager.getConnection("jdbc:odbc:access");   //��ȡ���ݿ�����
        if(con != null){
            System.out.println("ͨ��JDBC-ODBC������Access���ݿ�");
        }
        return true;
    } catch (Exception e) {       
        e.printStackTrace();
        return false;
    }
}

    public static void main(String[] args) {
        GetConnectionAccess access = new GetConnectionAccess();
        access.Connection();
    }
}
