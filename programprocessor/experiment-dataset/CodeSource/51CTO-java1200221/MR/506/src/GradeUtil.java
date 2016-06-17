

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.metal.MetalIconFactory.FolderIcon16;

public class GradeUtil {
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
    
    // ���尴ָ����ָ�����������ѯ���ݷ���

public ResultSet getResult() {
    conn = getConn(); // ��ȡ���ݿ�����
    float sum = 0;
    ResultSet set = null;
    try {
        Statement staement = conn.createStatement();
        String sql = "select avg(chinese)as chinese,avg(math) as math,avg(english) as english" +
        		",avg(physics) as physics,avg(history)as history from tb_Grade"; // ������Ҹ��Ƴɼ���ƽ��ֵ��SQL���
       set = staement.executeQuery(sql); // ִ�в�ѯ��䷵�ز�ѯ�����        
    } catch (Exception e) {
        e.printStackTrace();
    }
    return set;
}
    
    public static void main(String[] args) {
        GradeUtil util = new GradeUtil();
        ResultSet set = util.getResult();
        System.out.println("���Ƴɼ���ƽ���ɼ�Ϊ��");
        try {
            while(set.next()){
                float chinese = set.getFloat("chinese");
                float math = set.getFloat("math");
                float english = set.getFloat("english");
                float physics = set.getFloat("physics");
                float history = set.getFloat("history");
                System.out.println("���ģ�"+chinese+" ,��ѧ��"+math+" ,Ӣ�"+english+" ,����"+physics+" ����ʷ��"+history);
            }
        } catch (SQLException e) {          
            e.printStackTrace();
        }
    }
}
