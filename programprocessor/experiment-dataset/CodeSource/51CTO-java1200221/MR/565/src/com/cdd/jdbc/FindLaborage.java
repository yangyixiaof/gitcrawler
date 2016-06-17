package com.cdd.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FindLaborage {
    
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
    
    public ResultSet getMessageEmp() {
        conn = getConn(); // ��ȡ�����ݿ������
        try {
            Statement statement = conn.createStatement(); // ��ȡStatement����
            String sql = "select distinct dName,laborage.name,laborage.laborage,lYear,lDate from tb_laborage laborage,tb_dept dept,tb_employee emp "
                    + "where laborage.name in(select name from tb_employee where job = '���ž���' "
                    + "and schoolAge = '����' and dID =  dept.id )"; // �����ѯ���
            ResultSet rest = statement.executeQuery(sql); // ִ�в�ѯ����ȡ��ѯ�����
            return rest; // ���ز�ѯ���
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void main(String[] args) {
        FindLaborage Dlaborage = new FindLaborage(); // �����������
        ResultSet rest = Dlaborage.getMessageEmp(); // ���ò�ѯ����
        System.out.println("��ѯ���ƵĲ��ž�������������");
        try {
            while (rest.next()) { // ѭ��������ѯ�����
                String dName = rest.getString(1); // ��ȡ���������Ϣ
                String name = rest.getString(2);
                float laborage = rest.getFloat(3);
                int lYear = rest.getInt(4);
                int lDate = rest.getInt(5);
                System.out.println("������" + name + " ���ţ�" + dName + " ���ʣ�"
                        + laborage + " ��ݣ�" + lYear + " �·ݣ�" + lDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
