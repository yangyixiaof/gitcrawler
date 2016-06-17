import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class InsertInfo {
 static Connection conn = null;
    
    // ��ȡ���ݿ�����
    public Connection getConn() {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver"); // �������ݿ�����
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:jtds:sqlserver://localhost:1433;DatabaseName=db_database22"; // �������ݿ�URL
        String userName = "sa"; // �������ݿ���û���
        String passWord = ""; // �������ݿ�����
        try {
            conn = DriverManager.getConnection(url, userName, passWord); // ��ȡ���ݿ�����
            if (conn != null) {
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn; // ����Connection����
    }
    //���û���¼��Ϣ���뵽���ݿⷽ��

public void insertUser(User user) {
    conn = getConn(); // ��ȡ���ݿ�����
    try {
        PreparedStatement statement = conn
                .prepareStatement("insert into tb_user values(?,?,?)"); // ����������ݿ��Ԥ�������          
        statement.setString(1, user.getUserName());     //����Ԥ����������
        statement.setString(2, user.getPassWord());
        SimpleDateFormat date_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //����ָ����ʽ����SimpleDateFormat����
        String datetime = date_time.format(new Date());     //�Ե�ǰ���ڽ��и�ʽ��
        statement.setString(3, datetime);
        statement.executeUpdate(); // ִ��Ԥ�������
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
  
    
}
