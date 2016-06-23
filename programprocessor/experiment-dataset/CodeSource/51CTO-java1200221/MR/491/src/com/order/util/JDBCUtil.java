package com.order.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUtil {
    
    Connection conn = null;
    
    // ��ȡ���ݿ�����

public Connection getConn() {
    try {
        Class.forName("net.sourceforge.jtds.jdbc.Driver");  //�������ݿ�����
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
    String url = "jdbc:jtds:sqlserver://localhost:1433;DatabaseName=db_database21"; // �������ݿ�URL
    String userName = "sa"; // �������ݿ���û���
    String passWord = ""; // �������ݿ�����
    try {
        conn = DriverManager.getConnection(url, userName, passWord); // ��ȡ���ݿ�����
        if (conn != null) {
            System.out.println("���ݿ����ӳɹ���");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return conn; // ����Connection����
}
    
    //��ѯͼ��������м�¼����

public List getBook() {
    conn = getConn();   //��ȡ���ݿ�����
    List list = new ArrayList();    //���屣�淵��ֵ��List����
    try {
        Statement staement = conn.createStatement();    //����Statement����
        String sql = "select * from tb_book";      //�����ѯ��SQL���
        ResultSet set = staement.executeQuery(sql); //ִ�в�ѯ��䣬���ز�ѯ�����
        while (set.next()) {            //ѭ��������ѯ�����
            Book book = new Book();     //���������ݱ��Ӧ��JavaBean����
            book.setId(set.getInt(1));  //���ö����idֵ
            book.setBookName(set.getString(2));
            book.setAuthor(set.getString("author"));
            book.setPrice(set.getFloat("price"));
            book.setStock(set.getInt("stock"));
            list.add(book);             //�򼯺�����Ӷ���
        }
    } catch (Exception e) { 
        e.printStackTrace();
    }
    return list;                        //���ز�ѯ���
}
    
    //���尴ָ����ָ�����������ѯ���ݷ���

public List getBooKDesc(String term){
    List list = new ArrayList();    //�������ڱ��淵��ֵ��List����
    conn = getConn();               //��ȡ���ݿ�����
    try {
        Statement staement = conn.createStatement();
        String sql = "select * from tb_book order by "+term +" desc";   //���彫ͼ�������Ϣ���������SQL���
        ResultSet set = staement.executeQuery(sql); //ִ�в�ѯ��䷵�ز�ѯ�����
        while (set.next()) {    //ѭ��������ѯ�����
            Book book = new Book();     
            book.setId(set.getInt(1));
            book.setBookName(set.getString(2));
            book.setAuthor(set.getString("author"));
            book.setPrice(set.getFloat("price"));
            book.setStock(set.getInt("stock"));
            list.add(book);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;    
}    
}
