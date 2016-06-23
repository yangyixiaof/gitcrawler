package com.cdd.innerJoin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateJion {
    private Connection conn;
    
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

public ResultSet getJoin() {
    conn = getConn(); // ��ȡ�����ݿ������
    ResultSet rest;
    try {
        Statement statement = conn.createStatement(); // ��ȡStatement����
        String sql = "select cName,tName from tb_course c inner join tb_teacher  t on c.id = t.cId "; // �����ѯ���
        rest = statement.executeQuery(sql); // ִ�в�ѯ����ȡ��ѯ�����
        return rest; // ���ز�ѯ���
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        CreateJion createJion = new CreateJion();
        ResultSet rest = createJion.getJoin();
        System.out.println("�����Ӳ�ѯĳ�γ̵Ľ�ʦ��Ϣ");
        try {
            while (rest.next()) {
                String cName = rest.getString(1);
                String tName = rest.getString(2);
                System.out.println(cName + "�εĽ�ʦ�ǣ�" + tName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
