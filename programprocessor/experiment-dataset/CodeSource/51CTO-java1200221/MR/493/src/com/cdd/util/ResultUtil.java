package com.cdd.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResultUtil {
    // ��ȡ���ݿ�����
    private Connection conn;
    
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
                System.out.println("���ݿ����ӳɹ���");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn; // ����Connection����
    }
    
    // ��ѯͼ��������м�¼����
    
    public List getGrade() {
        conn = getConn(); // ��ȡ���ݿ�����
        List list = new ArrayList(); // ���屣�淵��ֵ��List����
        try {
            Statement staement = conn.createStatement(); // ����Statement����
            String sql = "select * from tb_Grade"; // �����ѯ��SQL���
            ResultSet set = staement.executeQuery(sql); // ִ�в�ѯ��䣬���ز�ѯ�����
            while (set.next()) { // ѭ��������ѯ�����
                Grade grade = new Grade();
                grade.setId(set.getInt(1));
                grade.setName(set.getString(2));
                grade.setChinses(set.getFloat(3));
                grade.setEnglish(set.getFloat("english"));
                grade.setHistory(set.getFloat("history"));
                grade.setMath(set.getFloat("math"));
                grade.setPhysics(set.getFloat("physics"));
                list.add(grade);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list; // ���ز�ѯ���
    }
    
    // ���尴ָ����ָ�����������ѯ���ݷ���
    
    public List getGradeDesc(String taxis) {
        List list = new ArrayList(); // �������ڱ��淵��ֵ��List����
        conn = getConn(); // ��ȡ���ݿ�����
        try {
            Statement staement = conn.createStatement();
            String sql = "select id,name,(chinese+math+english+physics+history) as sum from tb_grade order by sum "
                    + taxis; // ���彫ͼ�������Ϣ���������SQL���
            ResultSet set = staement.executeQuery(sql); // ִ�в�ѯ��䷵�ز�ѯ�����
            while (set.next()) { // ѭ��������ѯ�����
                Grade grade = new Grade(); // ������ѧ���ɼ����Ӧ��Grade����
                grade.setId(set.getInt(1)); // ���ö�������
                grade.setName(set.getString(2));
                grade.setSum(set.getFloat("sum"));
                list.add(grade); // ��Grade������ӵ�������
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list; // ���ز�ѯ����
    }
    
}
