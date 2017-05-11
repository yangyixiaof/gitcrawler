package com.cdd.more;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class FindMore {
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

public ResultSet getMore() {
    conn = getConn(); // ��ȡ�����ݿ������
    ResultSet rest;       
    try {
        Statement statement = conn.createStatement(); // ��ȡStatement����
        String sql = "select p.id,p.sName,w.wId,w.wage,l.pID,l.monthL,l.lDate,l.lMoney from (tb_personnel p left join tb_wage w on p.id = w.perId)" +
        		" left join tb_leave l on l.pID = p.id"; // �����ѯ���
        rest = statement.executeQuery(sql); // ִ�в�ѯ����ȡ��ѯ�����           
        return rest; // ���ز�ѯ���
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}
    public static void main(String[] args) {
        FindMore findMore = new FindMore();
        ResultSet rest = findMore.getMore();
        try {
            System.out.println("ʹ�������ӽ��ж���ѯ");
            while(rest.next()){
                int id = rest.getInt(1);
                String sName = rest.getString(2);
                String wId = rest.getString(3);
                float wage = rest.getFloat("wage");
                String pID = rest.getString("pID");
                int mothl = rest.getInt("monthL");
                float dateL = rest.getFloat("lDate");
                float lMoney = rest.getFloat("lMoney");
                System.out.println("��ţ�"+id+" ������"+sName+" ���ʱ�ţ�"+wId+" ���ʣ�"+wage+" ��ٱ�ţ�"+pID+" ����·ݣ�"
                        +mothl+" ���������"+dateL+" �۳����ʣ�"+lMoney);
                
            }
        } catch (SQLException e) {           
            e.printStackTrace();
        }
    }
}
