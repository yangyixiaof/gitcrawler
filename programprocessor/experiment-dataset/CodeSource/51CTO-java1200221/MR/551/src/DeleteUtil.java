import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DeleteUtil {
    static Connection conn = null;
    
    // ��ȡ���ݿ�����
    public static Connection getConn() {
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
    

    // �����ѯ����ͬѧ��Ϣ����
    public List selectStu() {
        conn = getConn();
        Statement statement;
        List list = new ArrayList<Stu>();
        try {
            statement = conn.createStatement();
            ResultSet rest = statement.executeQuery("select * from tb_stu");
            while (rest.next()) {
                Stu stu = new Stu();
                stu.setId(rest.getInt(1));
                stu.setName(rest.getString(2));
                stu.setGrade(rest.getString("grade"));
                stu.setSex(rest.getString(3));
                stu.setSpecialty(rest.getString(4));
                list.add(stu);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }
    
    // ����ɾ��ָ����¼�ķ���
    public void deleteStu(int id) {
        conn = getConn(); // ��ȡ���ݿ�����
        try {
            Statement statement = conn.createStatement();// �������SQL���
            statement.executeUpdate("delete from tb_stu where id= " + id); // ִ��Ԥ�������
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
