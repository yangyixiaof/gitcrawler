package com.cdd.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BccdSell {
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
    
public List getBccdSell() {
    List list = new ArrayList(); // �������ڱ��淵��ֵ��List����
    conn = getConn(); // ��ȡ���ݿ�����    
    try {
        Statement staement = conn.createStatement();
        String sql = "select * from tb_emp where convert(varchar(10),ddate,21) " +
        		"like (select convert(varchar(10),ddate,21) from tb_emp where name = '�ž�')"; // �����ѯ���ݵ�SQL���
        ResultSet set = staement.executeQuery(sql); // ִ�в�ѯ��䷵�ز�ѯ�����
        while (set.next()) { // ѭ��������ѯ�����
            Emp emp = new Emp();
            emp.setId(set.getInt(1));
            emp.setName(set.getString(2));
            emp.setSex(set.getString("sex"));
            emp.setDdate(set.getString("ddate"));
            emp.setDept(set.getString("dept"));
            list.add(emp);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list; // ����List����
}
    
    public static void main(String[] args) {
        BccdSell sell = new BccdSell();
        List list = sell.getBccdSell();
        System.out.println("���ž�ͬһ����˾��Ա���У�");
        for(int i = 0;i<list.size();i++){
            Emp emp = (Emp)list.get(i);
            System.out.println("������ "+emp.getName()+"  ���Ա�"+emp.getSex()+" �����ţ�"+emp.getDept()+"   ����˾ʱ�䣺"+emp.getDdate());
         }
    }
}
