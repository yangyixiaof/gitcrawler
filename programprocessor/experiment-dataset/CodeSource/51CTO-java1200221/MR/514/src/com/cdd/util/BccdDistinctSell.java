package com.cdd.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BccdDistinctSell {
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

public List getBccdSell() {
    List list = new ArrayList(); // �������ڱ��淵��ֵ��List����
    conn = getConn(); // ��ȡ���ݿ�����
    try {
        Statement staement = conn.createStatement();
        String sql = "select distinct bccdName , sum(bccdCount)  from tb_bccdSell group by bccdName"; // �����ѯ���ݵ�SQL���
        ResultSet set = staement.executeQuery(sql); // ִ�в�ѯ��䷵�ز�ѯ�����
        while (set.next()) { // ѭ��������ѯ�����
            Bccd bccd = new Bccd(); //���������ݶ�Ӧ��JavaBean����
            bccd.setBccdName(set.getString(1)); //���ö�������
            bccd.setSum(set.getInt(2));
            list.add(bccd);         //�򼯺�����Ӷ���
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list; // ����List����
}

    public static void main(String[] args) {
        BccdDistinctSell bccdSell = new BccdDistinctSell();
        List list = bccdSell.getBccdSell();
        System.out.println("�������ظ�ֵ��ѯ��");
        for(int i = 0;i<list.size();i++){
            Bccd bccd = (Bccd)list.get(i);
            System.out.println("���ƣ�"+bccd.getBccdName()+" �������۶"+bccd.getSum());
        }
    }
}
