import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class BccdSell {
    Connection conn = null;    
    // ��ȡ���ݿ�����
    
    public Connection getConn() {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver"); // �������ݿ�����
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:jtds:sqlserver://localhost:1433;DatabaseName=db_database21"; // �������ݿ�URL
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
    
public int getBccdSell() {
    List list = new ArrayList(); // �������ڱ��淵��ֵ��List����
    conn = getConn(); // ��ȡ���ݿ�����
    int count = 0;
    try {
        Statement staement = conn.createStatement();
        String sql = "select sum(bccdCount)  from tb_bccdSell where month(bccdDate) = 6"; // ��ѯ��̴ʵ���������6�µ�������
        ResultSet set = staement.executeQuery(sql); // ִ�в�ѯ��䷵�ز�ѯ�����
        while (set.next()) { // ѭ��������ѯ�����
           count = set.getInt(1);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return count; // ����List����
}
    
    public static void main(String[] args) {
        BccdSell sell = new BccdSell();
        int count = sell.getBccdSell();
        System.out.println("��̴ʵ�6�µ����۶��ǣ�"+count);
    }
}
