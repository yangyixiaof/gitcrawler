package com.cdd.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class FindMessage {
  private Connection conn; // ����Connection����
    
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
    /**
     * @param args
     */
    
public ResultSet getMessage() {
    ResultSet rest = null;
    conn = getConn(); // ��ȡ�����ݿ������
    try {
        Statement statement = conn.createStatement(); // ��ȡStatement����
        String sql = "select name,college,address from tb_student I where exists " +
        		"(select name from tb_grade M where M.name=I.name and english >90)"; // �����ѯ���
        rest = statement.executeQuery(sql); // ִ�в�ѯ����ȡ��ѯ�����           
    } catch (Exception e) {
        e.printStackTrace();
    }
    return rest; // ���ز�ѯ���
}    
    /**
     * @param args
     */
    public static void main(String[] args) {
       FindMessage findMessage = new FindMessage();
       ResultSet rest = findMessage.getMessage();
       System.out.println("��ѯӢ�ĳɼ���90�����ϵ�ѧ����Ϣ��");
       try {
        while(rest.next()){
               String name = rest.getString(1);
               String college = rest.getString(2);
               String address = rest.getString(3);
               System.out.println("����Ϊ��"+name+" ѧԺΪ��"+college+" ��ַΪ��"+address);
           }
    } catch (Exception e) {       
        e.printStackTrace();
    }
    }
    
}
