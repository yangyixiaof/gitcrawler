
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class FinDept {
    private Connection conn;
    
    public Connection getConn() {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver"); // �������ݿ�����
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:jtds:sqlserver://localhost:1433;DatabaseName=db_database23"; // �������ݿ�URL
        String userName = "sa"; // �������ݿ���û���
        String passWord = ""; // �������ݿ�����
        try {
            conn = DriverManager.getConnection(url, userName, passWord); // ��ȡ���ݿ�����
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn; // ����Connection����
    }
    
    public ResultSet getDept() {
        conn = getConn(); // ��ȡ�����ݿ������
        ResultSet rest;
        try {
            Statement statement = conn.createStatement(); // ��ȡStatement����
            String sql = "select * from tb_dept union all select * from tb_dept2"; // �����ѯ���
            rest = statement.executeQuery(sql); // ִ�в�ѯ����ȡ��ѯ�����
            return rest; // ���ز�ѯ���
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void main(String[] args) {
        FinDept dept = new FinDept();
        ResultSet rest = dept.getDept();
        System.out.println("ʹ��UNION ALL�����ظ�����");
        try {
            while (rest.next()) {
                int id = rest.getInt(1);
                String name = rest.getString(2);
                System.out.println("��ţ�" + id + " �������ƣ�" + name);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
