package com.cdd.transfer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransferProcure {
    
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

public String executeQuery(String userName,String passWord){
    String message = "��֤ʧ��";            //���屣�淵��ֵ���ַ�������
    conn = getConn();                      //��ȡ���ݿ�����
    CallableStatement cs = null;            //����CallableStatement����
    String sql = "{call validateSelect('"+userName+"','"+passWord+"')}";    //������ô洢�������
    try {
        cs = conn.prepareCall(sql);         //���ô洢����
        ResultSet rest = cs.executeQuery(); //��ȡ�����
        while(rest.next()){                 //ѭ���������������
            message = "��֤�ɹ�";            //���ö�����Ϣ   
        }            
    } catch (SQLException e) {          
        e.printStackTrace();
    }
    return message;                          //����String����
}

}
