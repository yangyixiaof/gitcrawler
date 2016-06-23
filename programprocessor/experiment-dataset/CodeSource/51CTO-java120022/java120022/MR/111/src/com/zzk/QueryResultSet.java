package com.zzk;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
/**
 * @author ������
 *
 */
public class QueryResultSet {
	/**
	 * ���徲̬���飬���ڼ������ݿ�����
	 */
	static {
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");			// �������ݿ�����
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
    /**
     * ��ò�ѯ�����
     */
    public static ResultSet gainReport() {
        Connection conn = null; // ��������
        Statement st = null; // ����Statement����
        ResultSet rs = null; // �������������
        try {
            String url = "jdbc:jtds:sqlserver://localhost:1433/db_database"; // ���ݿ�db_database��URL
            String username = "sa"; // ���ݿ���û���
            String password = ""; // ���ݿ�����
            conn = DriverManager.getConnection(url, username, password); // ��������
            st = conn.createStatement(); // ����Statement����
            String sql = "select * from tb_employee"; // ����SQL��ѯ���
            rs = st.executeQuery(sql); // ִ��SQL����ý����
            return rs;// ���ؽ����
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "���ݿ��쳣��\n" + e.getMessage());
            return null;
        }
    }
    /**
     * ��ò�ѯ�����
     */
    public static ResultSet gainRecord(){
        Connection conn=null;                                  // ��������
        Statement st=null;                                      // ����Statement����
        ResultSet rs=null;                                      // �������������
        try {
            String url="jdbc:jtds:sqlserver://192.168.1.122:1433/db_database";      // ���ݿ�db_database��URL
            String username="sa";                                   // ���ݿ���û���
            String password="";                                 // ���ݿ�����
            conn = DriverManager.getConnection(url, username, password);    // ��������
            st=conn.createStatement();                              // ����Statement����
            String sql="select avg(age) as avgAge,sex,address from tb_employee group by address,sex";                   // ����SQL��ѯ���
            rs=st.executeQuery(sql);                                // ִ��SQL����ý����
            return rs;// ���ؽ����
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "���ݿ��쳣��\n" + e.getMessage());
            return null;
        }
    }
}
