package com.cdd.Descartes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class GetDescartes {
    
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
    
    // ��ȡ�ѿ����˻�����

public List getDescsrtes() {
    List list = new ArrayList<MrEmp>();
    conn = getConn(); // ��ȡ�����ݿ������
    try {
        Statement statement = conn.createStatement(); // ��ȡStatement����
        String sql = "select tb_mrdept.*,tb_mremp.name,tb_mremp.sex,tb_mremp.schoolAge from tb_mrdept cross join tb_mremp"; // �����ѯ���
        ResultSet rest = statement.executeQuery(sql); // ִ�в�ѯ����ȡ��ѯ�����
        while (rest.next()) { // ѭ��������ѯ�����
            MrEmp mrEmp = new MrEmp();      //�������ѯ�������Ӧ��JavaBean����
            mrEmp.setId(rest.getInt(1));    //���ö�������
            mrEmp.setdName(rest.getString(2));
            mrEmp.setName(rest.getString(4));
            mrEmp.setPerson(rest.getString(3));
            mrEmp.setSex(rest.getString(5));
            mrEmp.setSchoolAge(rest.getString(6));
            list.add(mrEmp);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list; // ���ز�ѯ���
}
    
    public static void main(String[] args) {
        GetDescartes descartes = new GetDescartes();
        List list = descartes.getDescsrtes();
        System.out.println("�ѿ����˻���ѯ��");
        for (int i = 0; i < list.size(); i++) {
            MrEmp mrEmp = (MrEmp) list.get(i);
            System.out.println("��ţ�"+mrEmp.getId()+" �������ƣ�"+mrEmp.getdName()+"�������ˣ�"+mrEmp.getPerson()+" Ա��������"+mrEmp.getName()+
                    " Ա���Ա�"+mrEmp.getSex()+" ѧ����"+mrEmp.getSchoolAge());
        }
    }
    
}
