package com.cdd.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderFormUtil {
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
    
public List getMAXOrder() {
    List list = new ArrayList(); // �������ڱ��淵��ֵ��List����
    conn = getConn(); // ��ȡ���ݿ�����
    try {
        Statement staement = conn.createStatement();
        String sql = "select clientName,clientArea,clientMoney,visePerson from tb_orderForm " +
        		"where clientMoney = (select max(clientMoney) from tb_orderForm)"; // �����ѯǩ���Ķ������������Ϣ
        ResultSet set = staement.executeQuery(sql); // ִ�в�ѯ��䷵�ز�ѯ�����
        while (set.next()) { // ѭ��������ѯ�����
            OrderForm orderForm = new OrderForm();
            orderForm.setClientArea(set.getString("clientArea"));
            orderForm.setVisePerson(set.getString("visePerson"));
            orderForm.setClientMoney(set.getFloat("clientMoney"));
            orderForm.setClientName(set.getString("clientName"));
            list.add(orderForm);        
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;        //����List����
}
    public static void main(String[] args) {
        OrderFormUtil util = new OrderFormUtil();
        List list = util.getMAXOrder();
        System.out.println("��ѯǩ���������Ŀͻ���Ϣ��");
        for(int i = 0;i<list.size();i++){
            OrderForm orderForm = (OrderForm)list.get(i);
            System.out.println("�ͻ����ڵ�����"+orderForm.getClientArea()+" ,�ͻ���"+orderForm.getClientName()+" ����"+orderForm.getClientMoney()
                    +" ��ǩԼ�ˣ�"+orderForm.getVisePerson());
        }
    }
}
