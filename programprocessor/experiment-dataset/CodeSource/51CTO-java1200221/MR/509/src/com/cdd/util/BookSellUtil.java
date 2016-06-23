package com.cdd.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookSellUtil {
    
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
    
    public int getMAXOrder() {
        List list = new ArrayList(); // �������ڱ��淵��ֵ��List����
        conn = getConn(); // ��ȡ���ݿ�����
        int count = 0;
        try {
            Statement staement = conn.createStatement();
            String sql = "select count(bookName) as bookType from tb_bookSell where total >400"; // ��ѯ�����۶���400���ϵ�ͼ������
            ResultSet set = staement.executeQuery(sql); // ִ�в�ѯ��䷵�ز�ѯ�����
            while (set.next()) { // ѭ��������ѯ�����
                count = set.getInt("bookType");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count; // ����List����
    }
    
    public static void main(String[] args) {
        BookSellUtil util = new BookSellUtil();
        int count = util.getMAXOrder();
        System.out.println("�����۶��400��ͼ�������У�" + count + "��");
    }
}
