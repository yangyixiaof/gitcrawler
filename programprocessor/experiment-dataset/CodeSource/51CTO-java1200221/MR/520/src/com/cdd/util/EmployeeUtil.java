package com.cdd.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeUtil {
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

public List getEmp() {
    List list = new ArrayList(); // �������ڱ��淵��ֵ��List����
    conn = getConn(); // ��ȡ���ݿ�����
    try {
        Statement staement = conn.createStatement();
        String sql = "select id,eName,convert( varchar(20),convert(money,oldEarning,1),1) as oldEarning from tb_employeePay"; // �����ѯ���ݵ�SQL���
        ResultSet set = staement.executeQuery(sql); // ִ�в�ѯ��䷵�ز�ѯ�����
        while (set.next()) { // ѭ��������ѯ�����
            EmployeePay pay = new EmployeePay();    //���������ݿ��Ӧ��JavaBean����
            pay.setId(set.getInt(1));               //���ö�������
            pay.seteName(set.getString(2));
            pay.setOldEarning(set.getString(3));
            list.add(pay);                          //��������ӵ�������
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list; // ����List����
}
   
}
