package com.cdd.format;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class GetFormat {
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
    
    // ����ͼ�м�������

public List selectView() {
    conn = getConn(); // ��ȡ���ݿ�����
    Statement cs = null; // ����CallableStatement����
    String sql = "Select * from v_emp"; // �����ѯ��ͼ��SQL���
    List list = new ArrayList(); // ���屣���ѯ�����List����
    try {
        cs = conn.createStatement(); // ʵ����Statement����
        ResultSet rest = cs.executeQuery(sql); // ִ��SQL���
        while (rest.next()) { // ѭ��������ѯ�����
            Emp emp = new Emp();
            emp.setName(rest.getString(1));
            emp.setEdate(rest.getString(2));
            list.add(emp);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}
   
    public static void main(String[] args) {
        GetFormat format = new GetFormat();
        List list = format.selectView();
        System.out.println("ʹ����ͼ���¸�ʽ��������������");
        for(int i = 0;i<list.size();i++){
           Emp emp = (Emp)list.get(i);
            System.out.println("Ա��������"+emp.getName()+"  ��˾ʱ�䣺"+emp.getEdate());
       }
   }
   
}
