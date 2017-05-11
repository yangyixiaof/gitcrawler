package com.mingrisoft;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WareUtil {
    Connection conn = null;    
    // ��ȡ���ݿ�����
    public Connection getConn() {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver"); // �������ݿ�����
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:jtds:sqlserver://localhost:1433;DatabaseName=db_database17"; // �������ݿ�URL
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
    
public void insertWare(Ware ware) {
    conn = getConn(); // ��ȡ���ݿ�����
    try {
        PreparedStatement statement = conn
                .prepareStatement("insert into tb_ware values(?,?,?,?,?,?,?)"); // ����������ݿ��Ԥ�������
        statement.setString(1,ware.getSID() ); // ����Ԥ�������Ĳ���ֵ
        statement.setString(2,ware.getsName());
        statement.setString(3, ware.getSpec());
        statement.setString(4,ware.getCasing());
        statement.setString(5,ware.getUnit() );
        statement.setString(6, ware.getsDate());
        statement.setInt(7, ware.getAmout());
        statement.executeUpdate(); // ִ��Ԥ�������
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    
    public List selectWare() {
        conn = getConn(); // ��ȡ���ݿ�����
        Statement statment;
        List list = new ArrayList<Ware>();
        try {
            statment = conn.createStatement(); // ��ȡStatement����
            String sql = "select * from tb_ware"; // �����ѯSQL���
            ResultSet rest = statment.executeQuery(sql); // ִ�в�ѯ����ȡ��ѯ�����
            while (rest.next()) { // ѭ��������ѯ�����
               Ware ware = new Ware();              //
               ware.setSID(rest.getString(1));
               ware.setsName(rest.getString(2));
               ware.setSpec(rest.getString(3));
               ware.setCasing(rest.getString(4));
               ware.setUnit(rest.getString(5));
               ware.setsDate(rest.getString(6));
               ware.setAmout(rest.getInt(7));
               list.add(ware);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list; // ���ز�ѯ���
    }
    public static String getDateTime(){     //�÷�������ֵΪString����
        SimpleDateFormat format;
                            //simpleDateFormat�����ѡ���κ��û����������-ʱ���ʽ��ģʽ
        Date date = null; 
        Calendar myDate = Calendar.getInstance();
                            //Calendar�ķ���getInstance()���Ի�ô����͵�һ��ͨ�õĶ���
        myDate.setTime(new java.util.Date());
                            //ʹ�ø�����Date���ô�Calendar��ʱ��
        date = myDate.getTime();
                            //����һ����ʾ��Calendarʱ��ֵ������Ԫ�����ڵĺ���ƫ��������Date����
        format = new SimpleDateFormat("yyyy-MM-dd");
                            //��д��ʽ��ʱ��Ϊ����-��-�� ʱ���֣��롱
        String strRtn = format.format(date);
                            //��������Date��ʽ��Ϊ����/ʱ���ַ��������������ֵ��������String
        return strRtn;          //���ر��淵��ֵ����
    }

   
}
