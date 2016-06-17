import java.sql.*;
/**
 * @author Administrator
 */
public class SubjoinDate {

	String url = "jdbc:jtds:sqlserver://localhost:1433;DatabaseName=master";
	String username = "sa";
	String password = "";
	private Connection con = null;
	private Statement stmt = null;
	private ResultSet rs = null;

	public SubjoinDate() { // ͨ�����췽���������ݿ�����
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
		} catch (Exception ex) {
			System.out.println("���ݿ����ʧ��");
		}
	}

    public boolean Connection() {        //�������ݿ�����
        try {
            con = DriverManager.getConnection(url, username, password);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("creatConnectionError!");
        }
        return true;
    }

    //�����ݿ�Ĳ�ѯ����
    public ResultSet selectStatic(String sql) throws SQLException {
         ResultSet rs=null;
        if (con == null) {
            Connection();
        }
               try {
        stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        rs = stmt.executeQuery(sql);
         } catch (SQLException e) {
                     e.printStackTrace();
                    }
         closeConnection();
        return rs;
    }
    //�������ݿ�
    
    public boolean executeUpdate(String dataName, String mPath, String lPath) {
        if (con == null) {
            Connection(); // ���ݿ�����
        }
        try {
            stmt = con.createStatement();
            int iCount = stmt.executeUpdate("EXEC sp_attach_db @dbname = '"
                    + dataName + "', @filename1='" + mPath
                    + "', @filename2 = '" + lPath + "'");// ִ�����ݿ⸽��
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        closeConnection(); // ���ùر����ݿ����ӷ���
        return true;
    }

    //�ر����ݿ�Ĳ���
    public void closeConnection() {
       if (con != null && stmt != null && rs != null) {
            try {
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Failed to close connection!");
            } finally {
                con = null;
            }
        }
    }  
}
