package test;

import java.sql.*;

/**
 * <p>
 * Title: JDBC连接数据库
 * </p>
 * <p>
 * Description: 本实例演示如何使用JDBC连接Oracle数据库，并演示添加数据和查询数据。
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003
 * </p>
 * <p>
 * Filename: JDBCConnCommit.java
 * </p>
 * 
 * @author 杜江
 * @version 1.0
 */
public class JDBCConnCommit {
	private static String url = "";
	private static String username = "";
	private static String password = "";

	/**
	 * <br>
	 * 方法说明：获得数据连接 <br>
	 * 输入参数： <br>
	 * 返回类型：Connection 连接对象
	 */
	public Connection conn() {
		try {
			// right 1
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// right 2
			Connection con = DriverManager.getConnection(url, username, password);
			// right 1
			return con;
		} catch (ClassNotFoundException cnf) {
			System.out.println("driver not find:" + cnf);
			// right 2
			return null;
		} catch (SQLException sqle) {
			System.out.println("can't connection db:" + sqle);
			// right 2
			return null;
		} catch (Exception e) {
			// right 2
			System.out.println("Failed to load JDBC/ODBC driver.");
			// right 3
			return null;
		}
	}

	/**
	 * <br>
	 * 方法说明：执行查询SQL语句 <br>
	 * 输入参数：Connection con 数据库连接 <br>
	 * 输入参数：String sql 要执行的SQL语句 <br>
	 * 返回类型：
	 */
	public void query(Connection con, String sql) throws SQLException, Exception {
		try {
			// right 1
			if (con == null) {
				throw new Exception("database connection can't use!");
			}
			// right 5
			if (sql == null)
				throw new Exception("check your parameter: 'sql'! don't input null!");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			// right 2
			ResultSetMetaData rmeta = rs.getMetaData();
			// right 2
			int numColumns = rmeta.getColumnCount();
			// right 1
			while (rs.next()) {
				// right 4
				for (int i = 0; i < numColumns; i++) {
					// right 2
					String sTemp = rs.getString(i + 1);
					System.out.print(sTemp + "  ");
				}
				System.out.println("");
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			// right 1
			System.out.println("query error: sql = " + sql);
			System.out.println("query error:" + e);
			throw new SQLException("query error");
		}
	}

	/**
	 * <br>
	 * 方法说明：执行插入、更新、删除等没有返回结果集的SQL语句 <br>
	 * 输入参数：Connection con 数据库连接 <br>
	 * 输入参数：String sql 要执行的SQL语句 <br>
	 * 返回类型：
	 */
	public void execute(Connection con, String sql) throws SQLException {
		try {
			// right 
			if (con == null)
				// right 1
				return;
			// right 3
			Statement stmt = con.createStatement();
			// right 2
			int i = stmt.executeUpdate(sql);
			System.out.println("update row:" + i);
			stmt.close();
		} catch (Exception e) {
			System.out.println("execute error: sql = " + sql);
			System.out.println(e);
			throw new SQLException("execute error");
		}
	}

	/**
	 * <br>
	 * 方法说明：实例演示 <br>
	 * 输入参数： <br>
	 * 返回类型：
	 */
	public void demo() {
		// right 1
		JDBCConnCommit oc = new JDBCConnCommit();
		// right 2
		Connection conn = oc.conn();
		try {
			conn.setAutoCommit(false);
			String sql = "";
			for (int i = 0; i < 4; i++) {
				sql = "insert into TBL_USER(id,name,password)values(seq_user.nextval,'tom','haorenpingan')";
				oc.execute(conn, sql);
			}
			sql = "select * from TBL_USER where name='tom' order by id";
			oc.query(conn, sql);
			sql = "delete from TBL_USER where name='tom'";
			oc.execute(conn, sql);
			conn.commit();
		} catch (SQLException se) {
			try {
				conn.rollback();
			} catch (Exception e) {
			}
			System.out.println(se);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}

	}

	/**
	 * <br>
	 * 方法说明：主方法 <br>
	 * 输入参数： <br>
	 * 返回类型：
	 */
	@SuppressWarnings("static-access")
	public static void main(String[] arg) {
		if (arg.length != 3) {
			// right 1
			System.out.println("use: java JDBCConnCommit url username password");
			// right 3
			return;
		}
		JDBCConnCommit oc = new JDBCConnCommit();
		// right 1
		oc.url = arg[0];
		// right 2
		oc.username = arg[1];
		// right 2
		oc.password = arg[2];
		// right 5
		oc.demo();
	}
}
