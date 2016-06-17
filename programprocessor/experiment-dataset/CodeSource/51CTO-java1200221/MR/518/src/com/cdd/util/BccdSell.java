package com.cdd.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class BccdSell {
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
        String sql = "select * from tb_bccdSell where bccdDate > '2010/6/1' and bccdDate < '2010/6/30'"; // ��ѯ����������SQL���
        ResultSet set = staement.executeQuery(sql); // ִ�в�ѯ��䷵�ز�ѯ�����
        while (set.next()) { // ѭ��������ѯ�����
          Bccd bccd = new Bccd();
          bccd.setId(set.getInt(1));
          bccd.setBccdName(set.getString("bccdName"));
          bccd.setBccdCount(set.getInt("bccdCount"));
          bccd.setBccdPrice(set.getFloat("bccdPrice"));
          bccd.setBccdDate(set.getString("bccdDate"));
          list.add(bccd);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list; // ����List����
}
    
    public static void main(String[] args) {
        BccdSell sell = new BccdSell();
        List list = sell.getBccdSell();
        System.out.println("6�·������ۼ�¼�Ĵʵ���Ϣ��");
        for(int i = 0;i<list.size();i++){
            Bccd bccd = (Bccd)list.get(i);
            System.out.println("�ʵ䣺"+bccd.getBccdName()+" ��������"+bccd.getBccdCount()+" ,�ۼۣ�"+bccd.getBccdPrice()+" ,���ڣ�"+bccd.getBccdDate());
        }
    }
}
