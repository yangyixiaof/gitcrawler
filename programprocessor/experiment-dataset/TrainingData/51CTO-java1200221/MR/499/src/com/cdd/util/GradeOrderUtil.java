package com.cdd.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GradeOrderUtil {
    // ��ȡ���ݿ�����
    private Connection conn;
    
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
    
    /**
     * @return
     */
    
public List getGradeOrder() {
    List list = new ArrayList(); // �������ڱ��淵��ֵ��List����
    conn = getConn(); // ��ȡ���ݿ�����
    try {
        Statement staement = conn.createStatement();
        String sql = "select * from tb_Grade order by name  collate chinese_prc_stroke_cs_as_ks_ws"; // ���尴�ʻ������SQL���
        ResultSet set = staement.executeQuery(sql); // ִ�в�ѯ��䷵�ز�ѯ�����
        while (set.next()) { // ѭ��������ѯ�����
            Grade grade = new Grade();  //ʵ����ѧ���ɼ�����
            grade.setId(set.getInt(1)); //���ö���ı��
            grade.setName(set.getString(2));
            grade.setChinses(set.getFloat("chinese"));
            grade.setEnglish(set.getFloat("english"));
            grade.setHistory(set.getFloat("history"));
            grade.setMath(set.getFloat("math"));
            grade.setPhysics(set.getFloat("physics"));
            list.add(grade);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list; // ���ز�ѯ����
}

    public static void main(String[] args) {
        GradeOrderUtil util = new GradeOrderUtil();
        List list = util.getGradeOrder();
        System.out.println("�����ϱʻ�����");
        for (int i = 0; i < list.size(); i++) {
            Grade grade = (Grade)list.get(i);
            System.out.println("���Ϊ��"+grade.getId()+"  ������"+grade.getName()+
                    "  ���ģ�"+grade.getChinses()+"  Ӣ�"+grade.getEnglish()+
                    "  ��ʷ��"+grade.getHistory()+"  ��ѧ��"+grade.getMath()+
                    "  ����"+grade.getPhysics());            
        }
    }
}
