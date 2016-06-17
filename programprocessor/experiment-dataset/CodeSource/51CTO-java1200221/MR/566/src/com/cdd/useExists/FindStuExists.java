package com.cdd.useExists;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FindStuExists {

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

public List getMessageEmp() {
    conn = getConn(); // ��ȡ�����ݿ������
    List list = new ArrayList();
    try {
        Statement statement = conn.createStatement(); // ��ȡStatement����
        String sql = "select * from tb_student s where exists (select name from tb_grade g where chinese >=90 and s.name = g.name)"; // �����ѯ���
        ResultSet rest = statement.executeQuery(sql); // ִ�в�ѯ����ȡ��ѯ�����
        while(rest.next()){
            Student student = new Student();    //���������ݱ��Ӧ��JavaBean����
            student.setId(rest.getInt(1));      //���ö�������
            student.setName(rest.getString(2));
            student.setCollege(rest.getString(3));
            student.setSpeciality(rest.getString(4));
            student.setAddress(rest.getString(5));
            list.add(student);              //�򼯺�����Ӷ���
        }   
        return list; // ���ز�ѯ���
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        FindStuExists stuExists = new FindStuExists();
        List list = stuExists.getMessageEmp();
        System.out.println("����EXISTS��ѯ���ĳɼ�����90�ֵ�ѧϰ��Ϣ��");
        for(int i = 0;i<list.size();i++){
            Student student  = (Student)list.get(i);
            System.out.println("��ţ�"+student.getId()+" ������"+student.getName()+
                    "  ѧԺΪ��"+student.getCollege()+"  רҵ��"+student.getSpeciality()+
                    " ��ַ��"+student.getAddress());
        }
        
    }
    
}
