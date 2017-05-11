package com.cdd.avg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GetEnglishAvg {
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
    
    public List getAvg() {
        List list = new ArrayList<Grade>(); // ����List���϶���
        conn = getConn(); // ��ȡ�����ݿ������
        try {
            Statement statement = conn.createStatement(); // ��ȡStatement����
            String sql = "select id,name,english,( select avg(english) from tb_grade ) as avgEnglish,"
                    + "(english-( select avg(english) from tb_grade )) as diffAvgEnglish from tb_grade"; // �����ѯ���
            ResultSet rest = statement.executeQuery(sql); // ִ�в�ѯ����ȡ��ѯ�����
            while (rest.next()) { // ѭ��������ѯ�����
                Grade grade = new Grade(); // ���������ݱ��Ӧ��JavaBean����
                grade.setId(rest.getInt(1)); // ���ö�������
                grade.setName(rest.getString(2));
                grade.setEnglish(rest.getFloat(3));
                grade.setAvgEng(rest.getFloat(4));
                grade.setBalance(rest.getFloat(5));
                list.add(grade); // �򼯺������Ԫ��
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list; // ���ز�ѯ���
    }    
    public static void main(String[] args) {
        GetEnglishAvg getEnglishAvg = new GetEnglishAvg();
        List list = getEnglishAvg.getAvg();
        System.out.println("��ѯѧ��Ӣ��ɼ���Ӣ��ƽ���ɼ����ɼ���");
        for (int i = 0; i < list.size(); i++) {
            Grade grade = (Grade) list.get(i);
            System.out.println("��ţ�" + grade.getId() + " ������" + grade.getName()
                    + "  Ӣ��ɼ���" + grade.getEnglish() + " ƽ���ɼ���"
                    + grade.getAvgEng() + " �ɼ���" + grade.getBalance());
        }
        
    }
    
}
