
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserUtil {
    
    private Connection conn;
    
    public Connection getConn() {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver"); // �������ݿ�����
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:jtds:sqlserver://localhost:1433;DatabaseName=db_database24"; // �������ݿ�URL
        String userName = "sa"; // �������ݿ���û���
        String passWord = ""; // �������ݿ�����
        try {
            conn = DriverManager.getConnection(url, userName, passWord); // ��ȡ���ݿ�����
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn; // ����Connection����
    }    

public List executeUpdate() {
    conn = getConn(); // ��ȡ���ݿ�����
    CallableStatement cs = null; // ����CallableStatement����
    String sql = "{call selectUser}"; // ������ô�����̵�SQL���
    List list = new ArrayList();
    try {
        cs = conn.prepareCall(sql); // ʵ����CallableStatement����
        ResultSet rest = cs.executeQuery(); // ִ��SQL���
        while(rest.next()){             //ѭ��������ѯ�����
            User user = new User();     //���������ݱ��Ӧ��JavaBean����
            user.setId(rest.getInt(1));     //���ö�������
            user.setUserName(rest.getString(2));
            user.setPassword(rest.getString(3));
            user.setAge(rest.getInt(4));
            user.setSex(rest.getString(5));
            user.setJob(rest.getString(6));
            list.add(user);             //�򼯺�����Ӷ���
        }            
    } catch (SQLException e) {
        e.printStackTrace();
       
    }
    return list;
}      
}
