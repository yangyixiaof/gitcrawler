package com.cdd.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConditionUtil {
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
    //�ڲ�ѯ�������ȥ���ַ����еĿո�     

public List getBookSell() {
    List list = new ArrayList(); // �������ڱ��淵��ֵ��List����
    conn = getConn(); // ��ȡ���ݿ�����
    try {
        Statement staement = conn.createStatement();
        String sql = "select * from tb_book"; // �����ѯͼ�����۱��е�ȫ������
        ResultSet set = staement.executeQuery(sql); // ִ�в�ѯ��䷵�ز�ѯ�����
        while (set.next()) { // ѭ��������ѯ�����
            BookSell sell = new BookSell();     //���������ݱ��Ӧ��JavaBean����
            sell.setId(set.getInt(1));          //���ö�������
            sell.setBookName(set.getString(2).replace(" ", ""));
            sell.setStock(set.getString(3).replace(" ", ""));
            sell.setPrice(set.getFloat(4));
            sell.setBookConcern(set.getString(5).replace(" ", ""));
            list.add(sell);                     //��������ӵ�������
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;                    //���ز�ѯ���
}
    
}
