import java.sql.*;

public class JDBCUtil {
    
    Connection conn = null;
    
    public Connection getConn() {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:jtds:sqlserver://localhost:1433;DatabaseName=db_database21"; // �������ݿ�URL
        String userName = "sa"; // �������ݿ���û���
        String passWord = ""; // �������ݿ�����
        try {
            conn = DriverManager.getConnection(url, userName, passWord); // ��ȡ���ݿ�����
            if (conn != null) {
                System.out.println("���ݿ����ӳɹ���");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn; // ����Connection����
    }
    
public void insertEmp(String[] str) {
    JDBCUtil iteacher = new JDBCUtil(); // �����������
    Connection conn = iteacher.getConn(); // ���û�ȡ���ݿ����ӷ���
    String sql = "insert into tb_empTable  values('" + str[0] + "','"
            + str[1] + "','" + str[2] + "','" + str[3] + "')"; // ���������ݿ�������ݵ�SQL���
    try {
        Statement statement = conn.createStatement();
        statement.executeUpdate(sql); // ִ�в����sql���
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    
}
