package com.cdd.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GetDescribe {
    Connection conn = null;
    
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
    //�����ȡ�ֶε�������Ϣ����

public List getDescribe(String tableName) {
    conn = getConn();           //��ȡ���ݿ�����
    List list = new ArrayList();    //����List���϶���
    try {
        Statement stmt = conn.createStatement();    //��ȡStatement����
        ResultSet rest = stmt
                .executeQuery("select c.name,b.value FROM sysobjects a,sysproperties b,syscolumns " +
                		"c where a.name='"+tableName+"' and a.id=b.id and b.id=c.id and b.smallid=c.colorder");   //ִ�в�ѯ���
        while(rest.next()){ //ѭ��������ѯ�����
            Describe describe = new Describe(); //���嶨���JavaBean����
            describe.setName(rest.getString(1));    //���ö�������
            describe.setValue(rest.getString(2));   
            list.add(describe);             //�򼯺�����Ӷ���
        }
    } catch (Exception e) {
        e.printStackTrace();
    } 
    return list;
}    
    
    //������
    public static void main(String[] args) {
        GetDescribe getDescribe = new GetDescribe();
        List list = getDescribe.getDescribe("tb_book");
        System.out.println("���ݱ�tb_book���ֶ���������ϢΪ��");
        for(int i = 0;i<list.size();i++){
            Describe describe = (Describe)list.get(i);
            System.out.println("   �ֶ�Ϊ��"+describe.getName()+"  ������ϢΪ��"+describe.getValue());
        }
    }
    
}
