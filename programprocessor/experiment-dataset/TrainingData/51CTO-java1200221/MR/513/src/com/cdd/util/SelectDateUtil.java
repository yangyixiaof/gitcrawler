package com.cdd.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SelectDateUtil {
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
    //���������ڲ���ѧ����Ϣ����

public List getStuUseDate(String sDate) {
    List list = new ArrayList(); // �������ڱ��淵��ֵ��List����
    conn = getConn(); // ��ȡ���ݿ�����
    try {
        Statement staement = conn.createStatement();
        String sql = "select * from tb_StuInfo where sBrithday = '"+sDate+"'"; // �����ѯ���ݵ�SQL���
        ResultSet set = staement.executeQuery(sql); // ִ�в�ѯ��䷵�ز�ѯ�����
        while (set.next()) { // ѭ��������ѯ�����
           StuInfo stuInfo = new StuInfo();     //���������ݱ��Ӧ��JavaBean����
           stuInfo.setId(set.getInt(1));        //���ö�������
           stuInfo.setsName(set.getString(2));
           stuInfo.setsSex(set.getString(3));
           stuInfo.setsBrithday(set.getString(4));
           stuInfo.setsSpeciality(set.getString(5));
           stuInfo.setsAddress(set.getString(6));
           list.add(stuInfo);                   //�򼯺�����Ӷ���
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list; // ����List����
}
    //��ѯ����ѧ���������ڷ���
    public List getStuDateList() {
        List list = new ArrayList(); // �������ڱ��淵��ֵ��List����
        conn = getConn(); // ��ȡ���ݿ�����
        try {
            Statement staement = conn.createStatement();
            String sql = "select sBrithday from tb_StuInfo "; // �����ѯ���ݵ�SQL���
            ResultSet set = staement.executeQuery(sql); // ִ�в�ѯ��䷵�ز�ѯ�����
            while (set.next()) { // ѭ��������ѯ�����
               String sDate = new String();
               sDate = set.getString(1);
               list.add(sDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list; // ����List����
    }
}
