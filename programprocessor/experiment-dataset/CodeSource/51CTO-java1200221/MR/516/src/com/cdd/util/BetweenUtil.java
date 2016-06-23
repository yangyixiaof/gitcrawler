package com.cdd.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BetweenUtil {
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
    
public List getGrade() {
    List list = new ArrayList(); // �������ڱ��淵��ֵ��List����
    conn = getConn(); // ��ȡ���ݿ�����
    try {
        Statement staement = conn.createStatement();
        String sql = "select name,english from tb_Grade  where english  between 80 and 90"; // �����ѯӢ�ĳɼ���80��9֮���ѧ����ϢSQL���
        ResultSet set = staement.executeQuery(sql); // ִ�в�ѯ��䷵�ز�ѯ�����
        while (set.next()) { // ѭ��������ѯ�����
            Grade grade = new Grade(); // ������ѧ���ɼ����Ӧ��Grade����            
            grade.setName(set.getString(1));
            grade.setEnglish(set.getFloat(2));
            list.add(grade); // ��Grade������ӵ�������
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list; // ���ز�ѯ����
}    
    
    public static void main(String[] args) {
       BetweenUtil util = new BetweenUtil();
       List list = util.getGrade();
       System.out.println("��ѯӢ��ɼ���80��90֮���ѧ����");
       for(int i = 0;i<list.size();i++){
           Grade grade = (Grade)list.get(i);
           System.out.println("������"+grade.getName()+" ��Ӣ��ɼ���"+grade.getEnglish());
       }
    }
    
}
