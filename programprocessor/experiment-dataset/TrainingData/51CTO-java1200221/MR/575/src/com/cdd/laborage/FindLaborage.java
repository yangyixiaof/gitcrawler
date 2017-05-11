package com.cdd.laborage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FindLaborage {
    
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
    
    public List getPatindex() {
        conn = getConn(); // ��ȡ�����ݿ������
        ResultSet rest;
        List list = new ArrayList<FindLaborage>();
        try {
            Statement statement = conn.createStatement(); // ��ȡStatement����
            String sql = "select id,name,Base,round(Subsidy,0) as subsidy, round(deduct,0) as deduct from tb_particularLaborage"; // �����ѯ���
            rest = statement.executeQuery(sql); // ִ�в�ѯ����ȡ��ѯ�����
            while (rest.next()) { // ѭ��������ѯ�����
                ParticularLaborage laborage = new ParticularLaborage(); // ���������ݱ��Ӧ��JavaBean����
                laborage.setId(rest.getInt(1)); // ���ö�������
                laborage.setName(rest.getString(2));
                laborage.setBase(rest.getFloat(3));
                laborage.setSubsidy(rest.getFloat(4));
                laborage.setDeduct(rest.getFloat(5));
                list.add(laborage); // �򼯺�����Ӷ���
            }
            return list; // ���ز�ѯ���
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void main(String[] args) {
        FindLaborage findLaborage = new FindLaborage();
        List list = findLaborage.getPatindex();
        System.out.println("�����ݽ����������룺");
        for (int i = 0; i < list.size(); i++) {
            ParticularLaborage particularLaborage = (ParticularLaborage) list
                    .get(i);
            int id = particularLaborage.getId();
            String name = particularLaborage.getName();
            float base = particularLaborage.getBase();
            float subsidy = particularLaborage.getSubsidy();
            float deduct = particularLaborage.getDeduct();
            System.out.println("��ţ�" + id + " ������" + name + "  �������ʣ�" + base
                    + " ������" + subsidy + " �۳���" + deduct);
        }
    }
}
