
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {
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
    
    public void insertEmp(Emp emp) {
        conn = getConn(); // ��ȡ���ݿ�����
        try {
            PreparedStatement statement = conn
                    .prepareStatement("insert into tb_emp values(?,?,?,?,?,?)"); // ����������ݿ��Ԥ�������
            statement.setString(1, emp.getName()); // ����Ԥ�������Ĳ���ֵ
            statement.setString(2, emp.getSex());
            statement.setInt(3, emp.getAge());
            statement.setString(4, emp.getDept());
            statement.setString(5, emp.getPhone());
            statement.setString(6, emp.getRemark());
            statement.executeUpdate(); // ִ��Ԥ�������
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public int selectEmpUseName(String name) {
        conn = getConn(); // ��ȡ���ݿ�����
        Statement statment;
        int id = 0; // ���屣�淵��ֵ��int����
        try {
            statment = conn.createStatement(); // ��ȡStatement����
            String sql = "select id from tb_emp where name = '" + name + "'"; // �����ѯSQL���
            ResultSet rest = statment.executeQuery(sql); // ִ�в�ѯ����ȡ��ѯ�����
            while (rest.next()) { // ѭ��������ѯ�����
                id = rest.getInt(1); // ��ȡ��ѯ���
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id; // ���ز�ѯ���
    }
    
}
