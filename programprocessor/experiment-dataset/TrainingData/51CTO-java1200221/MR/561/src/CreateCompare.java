import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CreateCompare {
    private Connection conn; // ����Connection����
    
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
    
    /**
     * @param args
     */
    
    public List getCompare(String name1, String name2) {
        List list = new ArrayList<Grade>(); // ����List���϶���
        conn = getConn(); // ��ȡ�����ݿ������
        try {
            Statement statement = conn.createStatement(); // ��ȡStatement����
            String sql = "select name,chinese from tb_grade where chinese > (select chinese from tb_grade where name = '"
                    + name1
                    + "') "
                    + "and chinese < (select chinese from tb_grade where name = '"
                    + name2 + "')"; // �����ѯ���
            ResultSet rest = statement.executeQuery(sql); // ִ�в�ѯ����ȡ��ѯ�����
            while (rest.next()) { // ѭ��������ѯ�����
                Grade grade = new Grade(); // ���������ݱ��Ӧ��JavaBean����
                grade.setName(rest.getString(1));
                grade.setChinese(rest.getFloat(2));
                list.add(grade); // �򼯺������Ԫ��
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list; // ���ز�ѯ���
    }
    
}
