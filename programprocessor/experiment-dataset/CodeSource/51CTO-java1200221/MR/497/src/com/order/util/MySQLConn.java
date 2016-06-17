package com.order.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLConn {
    
    Connection conn = null;    
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver"); // ����MySQL���ݿ�����
            String url = "jdbc:mysql://localhost:3306/db_database21"; // �������������ݿ��url
            String user = "root"; // �����������ݿ���û���
            String passWord = "111"; // �����������ݿ������
            conn = DriverManager.getConnection(url, user, passWord); // ��������
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }    
    // ���尴ָ����ָ�����������ѯ���ݷ���    
    public List getOrderDesc() {
        List list = new ArrayList(); // �������ڱ��淵��ֵ��List����
        conn = getConnection(); // ��ȡ���ݿ�����
        try {
            Statement staement = conn.createStatement();
            String sql = "select id,name,sex,className,chinese from tb_student order by chinese limit 0,3"; // �����ѯ���ݱ��к�3����¼��SQL���
            ResultSet set = staement.executeQuery(sql); // ִ�в�ѯ��䷵�ز�ѯ�����
            while (set.next()) { // ѭ��������ѯ�����
                Student student = new Student(); // ���������ݿ��Ӧ��JavaBean����
                student.setId(set.getInt(1)); // ���ö�������ֵ
                student.setName(set.getString("name"));
                student.setSex(set.getString("sex"));
                student.setClassName(set.getString("className"));
                student.setChinese(set.getFloat("chinese"));
                list.add(student); // ��JavaBean��ӵ�������
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public static void main(String[] args) {
        MySQLConn mySqlConn = new MySQLConn();
        List list = mySqlConn.getOrderDesc();
        System.out.println("��ѯ���ĳɼ����ں�3����ͬѧ��Ϣ��");
        for (int i = 0; i < list.size(); i++) {
            Student student = (Student) list.get(i);
            System.out.println("���Ϊ��" + student.getId() + "��������"
                    + student.getName() + "���Ա�" + student.getSex() + "�����ĳɼ���"
                    + student.getChinese());
        }
    }
}
