import java.sql.*;
import java.util.*;


public class BatchInsert {
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
    public List executeTeacher() {
        conn = getConn();                                // ��ȡ���ݿ�����
        Statement cs = null;                                 // ����CallableStatement����
        String sql = "select * from tb_teacher";         // ������ô�����̵�SQL���
        List list = new ArrayList();
        try {
            cs = conn.createStatement();                     // ʵ����Statement����
            ResultSet rest = cs.executeQuery(sql);       // ִ��SQL���
            while (rest.next()) {                        // ѭ��������ѯ�����
                Teacher teacher = new Teacher();         //���������ݿ���Ӧ��JavaBean����
                teacher.setId(rest.getInt(1));           //���ö���Ĳ���ֵ
                teacher.settName(rest.getString(2));
                teacher.setCourse(rest.getString(3));
                list.add(teacher);                       //�򼯺�����Ӷ���
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public void insertBatch() {
        conn = getConn();                                // ��ȡ���ݿ�����
        Statement cs = null;                                 // ����Statement����
        try {
            cs = conn.createStatement();                     // ʵ����Statement����
            List list = executeTeacher();
            for (int i = 0; i < list.size(); i++) {
                Teacher teacher = (Teacher) list.get(i);
                cs.addBatch("insert into tb_elective values ('"
                        + teacher.getCourse() + "','" + teacher.gettName()
                        + "','����')");                //���SQL���
            }
            System.out.println("������ӳɹ���");
            cs.executeBatch();                       // ����ִ��SQL���
            cs.close();                              // ��Statement����ر�
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        BatchInsert bi = new BatchInsert();
        bi.insertBatch();
    }

}
