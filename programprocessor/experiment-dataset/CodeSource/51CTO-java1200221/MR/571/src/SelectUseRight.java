import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SelectUseRight {
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
    public List getRight() {
        conn = getConn(); // ��ȡ�����ݿ������
        ResultSet rest;
        List list = new ArrayList<MrEmp>();
        try {
            Statement statement = conn.createStatement(); // ��ȡStatement����
            String sql = "select e.id,dName,person,name,sex,schoolAge  from tb_mrdept d right join tb_mremp e on d.id = e.dId"; // �����ѯ���
            rest = statement.executeQuery(sql); // ִ�в�ѯ����ȡ��ѯ�����
            while (rest.next()) {
                MrEmp mrEmp = new MrEmp(); // ���������ݱ��Ӧ��JavaBean����
                mrEmp.setId(rest.getInt(1)); // ���ö�������
                mrEmp.setdName(rest.getString(2));
                mrEmp.setPerson(rest.getString(3));
                mrEmp.setName(rest.getString(4));
                mrEmp.setSex(rest.getString(5));
                mrEmp.setSchoolAge(rest.getString(6));
                list.add(mrEmp);
            }
            return list; // ���ز�ѯ���
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
