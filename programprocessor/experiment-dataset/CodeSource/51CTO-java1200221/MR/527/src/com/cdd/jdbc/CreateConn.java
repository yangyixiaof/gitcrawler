package com.cdd.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class CreateConn {
    
private Connection conn ;   //����Connection����
public Connection getConnection(){  //�����������ݿⷽ��
    try {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  //�������ݿ�����
        System.out.println("���ݿ��������سɹ���");
        String url = "jdbc:sqlserver://localhost:1433;DatabaseName=db_database22";  //�����������ݿ�URL
        String userName = "sa";
        String passWord = "";
        conn = DriverManager.getConnection(url,userName ,passWord);       //��ȡ���ݿ�����
        if(conn != null){
            System.out.println("�ѳɹ�����SQLServer2005���ݿ⽨�����ӣ�");
        }
    } catch (Exception e) {          
        e.printStackTrace();
    }
    return conn;
}
    
    /**
     * @param args
     */
    
    public static void main(String[] args) {
        CreateConn conn = new CreateConn();
        conn.getConnection();        
    }
    
}
