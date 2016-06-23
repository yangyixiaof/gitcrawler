package com.taxis.bean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUtil {
    
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
    
    // ���尴ָ����ָ�����������ѯ���ݷ���
    
public ResultSet getBooKDesc() {
    List list = new ArrayList(); // �������ڱ��淵��ֵ��List����
    conn = getConn(); // ��ȡ���ݿ�����
    ResultSet set = null;
    try {
        Statement staement = conn.createStatement();
        String sql = "select cargoId,cargoName,cargoCount,sum(sellCount)as count "
                + " from tb_cargo,tb_sell where cargoId = sellId group by sellId,cargoName,cargoId,cargoCount"; // �����ѯ���ݿ��SQL���
        set = staement.executeQuery(sql); // ִ�в�ѯ��䷵�ز�ѯ�����            
    } catch (Exception e) {
        e.printStackTrace();
    }
    return set;
}
    public static void main(String[] args) {
        JDBCUtil util = new JDBCUtil();
        ResultSet set = util.getBooKDesc();
        try {
            System.out.println("�����ۼ�¼����Ʒ��������������");
            while(set.next()){
                String cargoId = set.getString("cargoId");
                String cargoName = set.getString("cargoName");
                int cargoCont = set.getInt("cargoCount");
                int count = set.getInt("count");
                System.out.println("��ţ�"+cargoId+"   ���ƣ�"+cargoName+"   ��������"+cargoCont+"   ������"+count);
            }
        } catch (SQLException e) {         
            e.printStackTrace();
        }
    }
}
