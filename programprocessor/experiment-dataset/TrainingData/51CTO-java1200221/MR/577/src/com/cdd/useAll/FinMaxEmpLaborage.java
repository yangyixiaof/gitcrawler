package com.cdd.useAll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FinMaxEmpLaborage {
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
    
    public List getLaborage() {
        conn = getConn(); // ��ȡ�����ݿ������
        ResultSet rest;
        List list = new ArrayList<Emp>();
        try {
            Statement statement = conn.createStatement(); // ��ȡStatement����
            String sql = "select eName,headship,laborage from tb_emp  where laborage > all(select laborage from tb_emp where dept = '������')"; // �����ѯ���
            rest = statement.executeQuery(sql); // ִ�в�ѯ����ȡ��ѯ�����
            while (rest.next()) { // ѭ��������ѯ�����
                Emp emp = new Emp(); // ���������ݱ��Ӧ��JavaBean����
                emp.seteName(rest.getString(1)); // ���ö�������
                emp.setHeadship(rest.getString(2));
                emp.setLaborage(rest.getFloat(3));
                list.add(emp); // �򼯺�����Ӷ���
            }
            return list; // ���ز�ѯ���
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void main(String[] args) {
        FinMaxEmpLaborage maxLaborage = new FinMaxEmpLaborage();
        List list = maxLaborage.getLaborage();
        System.out.println("��ѯ��������������Ա�����ʶ��ߵ�Ա�����������");
        for (int i = 0; i < list.size(); i++) {
            Emp emp = (Emp) list.get(i);
            System.out.println("������" + emp.geteName() + "  ���ţ�"
                    + emp.getHeadship() + "  ���ʣ�" + emp.getLaborage());
        }
    }
}
