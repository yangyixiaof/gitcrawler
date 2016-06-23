import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AlterProce {
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
    
    public boolean executeUpdate(String sql) {
        conn = getConn(); // ��ȡ���ݿ�����
        try {
            Statement stmt = conn.createStatement(); // ʵ����Statement����
            int iCount = stmt.executeUpdate(sql); // ִ���޸����
            System.out.println("�����ɹ�����Ӱ��ļ�¼��Ϊ" + String.valueOf(iCount)); // ������ʾ��Ϣ
            conn.close(); // �ر�����
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
