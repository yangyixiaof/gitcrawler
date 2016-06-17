package com.zzk;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
/**
 * @author 张振坤
 *
 */
public class QueryResultSet {
	/**
	 * 定义静态语句块，用于加载数据库驱动
	 */
	static {
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");			// 加载数据库驱动
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
    /**
     * 获得查询结果集
     */
    public static ResultSet gainReport() {
        Connection conn = null; // 声明连接
        Statement st = null; // 声明Statement对象
        ResultSet rs = null; // 声明结果集对象
        try {
            String url = "jdbc:jtds:sqlserver://localhost:1433/db_database"; // 数据库db_database的URL
            String username = "sa"; // 数据库的用户名
            String password = ""; // 数据库密码
            conn = DriverManager.getConnection(url, username, password); // 建立连接
            st = conn.createStatement(); // 创建Statement对象
            String sql = "select * from tb_employee"; // 定义SQL查询语句
            rs = st.executeQuery(sql); // 执行SQL语句获得结果集
            return rs;// 返回结果集
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "数据库异常：\n" + e.getMessage());
            return null;
        }
    }
    /**
     * 获得查询结果集
     */
    public static ResultSet gainRecord(){
        Connection conn=null;                                  // 声明连接
        Statement st=null;                                      // 声明Statement对象
        ResultSet rs=null;                                      // 声明结果集对象
        try {
            String url="jdbc:jtds:sqlserver://192.168.1.122:1433/db_database";      // 数据库db_database的URL
            String username="sa";                                   // 数据库的用户名
            String password="";                                 // 数据库密码
            conn = DriverManager.getConnection(url, username, password);    // 建立连接
            st=conn.createStatement();                              // 创建Statement对象
            String sql="select avg(age) as avgAge,sex,address from tb_employee group by address,sex";                   // 定义SQL查询语句
            rs=st.executeQuery(sql);                                // 执行SQL语句获得结果集
            return rs;// 返回结果集
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "数据库异常：\n" + e.getMessage());
            return null;
        }
    }
}
