package com.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class GoDanger {
    private Connection conn;
    // ��ȡ���ݿ�����
    public Connection getConn() {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver"); // �������ݿ�����
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:jtds:sqlserver://localhost:1433;DatabaseName=db_database22"; // �������ݿ�URL
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
    public void insertBookSell(BookSell bookSell) {
        conn = getConn(); // ��ȡ���ݿ�����
        try {
            PreparedStatement statement = conn
                    .prepareStatement("insert into tb_bookSell values(?,?,?,?)"); // ����������ݿ��Ԥ�������
         
            statement.setString(1,bookSell.getBookName() );
            statement.setString(2, bookSell.getStock());
            statement.setFloat(3, bookSell.getPrice());
            statement.setString(4, bookSell.getBookConcern());
            statement.executeUpdate(); // ִ��Ԥ�������
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
