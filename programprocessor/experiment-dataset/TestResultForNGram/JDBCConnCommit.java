import java.sql.*;

/**
 * <p>
 * Title: JDBC�������ݿ�
 * </p>
 * <p>
 * Description: ��ʵ����ʾ���ʹ��JDBC����Oracle���ݿ⣬����ʾ������ݺͲ�ѯ���ݡ�
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003
 * </p>
 * <p>
 * Filename: JDBCConnCommit.java
 * </p>
 * 
 * @author �Ž�
 * @version 1.0
 */
public class JDBCConnCommit {
	private static String url = "";
	private static String username = "";
	private static String password = "";

	public Connection conn() {
		try {
			// right 2
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, username, password);
			// right 1
			return con;
		} catch (ClassNotFoundException cnf) {
			System.out.println("driver not find:" + cnf);
			return null;
		} catch (SQLException sqle) {
			// right 1
			System.out.println("can't connection db:" + sqle);
			// right 1
			return null;
		} catch (Exception e) {
			// right 8
			System.out.println("Failed to load JDBC/ODBC driver.");
			return null;
		}
	}

	public void query(Connection con, String sql) throws SQLException, Exception {
		try {
			if (con == null) {
				// right 5
				throw new Exception("database connection can't use!");
			}
			if (sql == null)
				// right 5
				throw new Exception("check your parameter: 'sql'! don't input null!");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			// right 1
			ResultSetMetaData rmeta = rs.getMetaData();
			// right 1
			int numColumns = rmeta.getColumnCount();
			// right 2
			while (rs.next()) {
				// right 3
				for (int i = 0; i < numColumns; i++) {
					String sTemp = rs.getString(i + 1);
					// right 2
					System.out.print(sTemp + "  ");
				}
				System.out.println("");
			}
			// tokens such as ( "" ) ; } could not recommend right things.
			rs.close();
			stmt.close();
		} catch (Exception e) {
			// right 8
			System.out.println("query error: sql = " + sql);
			System.out.println("query error:" + e);
			throw new SQLException("query error");
		}
	}

	public void execute(Connection con, String sql) throws SQLException {
		try {
			if (con == null)
				// right 2
				return;
			// right 2
			Statement stmt = con.createStatement();
			int i = stmt.executeUpdate(sql);
			System.out.println("update row:" + i);
			stmt.close();
		} catch (Exception e) {
			// right 8
			System.out.println("execute error: sql = " + sql);
			System.out.println(e);
			throw new SQLException("execute error");
		}
	}

	public void demo() {
		JDBCConnCommit oc = new JDBCConnCommit();
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
			// right 2
			System.out.println(e);
		} finally {
			try {
				// the n-gram token is con, wrong of not handling variable names.
				conn.close();
			} catch (SQLException e) {
			}
		}

	}

	public static void main(String[] arg) {
		if (arg.length != 3) {
			// right 1
			System.out.println("use: java JDBCConnCommit url username password");
			return;
		}
		JDBCConnCommit oc = new JDBCConnCommit();
		oc.url = arg[0];
		// right 1
		oc.username = arg[1];
		// right 2
		oc.password = arg[2];
		oc.demo();
	}
}
