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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn; // ����Connection����
    }
  
    
    // ���尴ָ����ָ�����������ѯ���ݷ���

public List getGradeDesc() {
    List list = new ArrayList(); // �������ڱ��淵��ֵ��List����
    conn = getConn(); // ��ȡ���ݿ�����
    try {
        Statement staement = conn.createStatement();
        String sql = "select top 3 id ,name,english from tb_Grade order by english desc"; // ���彫ͼ�������Ϣ���������SQL���
        ResultSet set = staement.executeQuery(sql); // ִ�в�ѯ��䷵�ز�ѯ�����
        while (set.next()) { // ѭ��������ѯ�����
            Grade grade = new Grade(); // ������ѧ���ɼ����Ӧ��Grade����
            grade.setId(set.getInt(1)); // ���ö�������
            grade.setName(set.getString(2));
            grade.setEnglish(set.getFloat(3));
            list.add(grade); // ��Grade������ӵ�������
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list; // ���ز�ѯ����
}
    
    public static void main(String[] args) {
        ResultUtil util = new ResultUtil();
        List list = util.getGradeDesc();
        System.out.println("Ӣ��ɼ�����ǰ3��ͬѧ�ǣ�");
        for(int i = 0;i < list.size();i++){
            Grade grade = (Grade)list.get(i);
            System.out.println("��ţ�"+grade.getId()+" ��������"+grade.getName()+"  ��Ӣ��ɼ���"+grade.getEnglish());
        }
    }
    
}
