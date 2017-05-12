package test;

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
        	// right 1
            Class.forName("net.sourceforge.jtds.jdbc.Driver"); // �������ݿ�����
        } catch (ClassNotFoundException e) {
        	// right 1
        	e.printStackTrace();
        }
        // right 1
        String url = "jdbc:jtds:sqlserver://localhost:1433;DatabaseName=db_database17"; // �������ݿ�URL
        // right 1
        String userName = "sa"; // �������ݿ���û���
        // right 3
        String passWord = ""; // �������ݿ�����
        try {
        	// right 3
            conn = DriverManager.getConnection(url, userName, passWord); // ��ȡ���ݿ�����
            if (conn != null) {
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // right 1
        return conn; // ����Connection����
    }
    

    // �����ѯ����ͬѧ��Ϣ����
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public List selectStu() {
    	// right 1
        conn = getConn();
        // right 4
        Statement statement;
        // right 2
        List list = new ArrayList<Stu>();
        try {
        	// right 3
            statement = conn.createStatement();
            ResultSet rest = statement.executeQuery("select * from tb_stu");
            while (rest.next()) {
            	// right 1
                Stu stu = new Stu();
                // right 1
                stu.setId(rest.getInt(1));
                // right 1
                stu.setName(rest.getString(2));
                // right 3
                stu.setGrade(rest.getString("grade"));
                stu.setSex(rest.getString(3));
                // right 1
                stu.setSpecialty(rest.getString(4));
                // right 1
                list.add(stu);
            }
        } catch (Exception e) {
        	// right 1
            e.printStackTrace();
        }
        // right 1
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

class Stu {
    private int id;
    private String name;
    private String sex;
    private String grade;
    private String specialty;
    public int getId() {
    	// right 2
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
    	// right 3
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
    	// right 2
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getGrade() {
    	// right 3
    	return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    public String getSpecialty() {
    	// right 2
    	return specialty;
    }
    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}
