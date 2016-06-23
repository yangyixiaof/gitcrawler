import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLConn {
    Connection conn = null;
    
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver"); // ����MySQL���ݿ�����
            String url = "jdbc:mysql://localhost:3306/information_schema"; // �������������ݿ��url
            String user = "root"; // �����������ݿ���û���
            String passWord = "111"; // �����������ݿ������
            conn = DriverManager.getConnection(url, user, passWord); // ��������
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    
    // ��ȡMySQL�������ݿⷽ��
    public List getDatabase() {
        List list = new ArrayList(); // ����List���϶���
        Connection con = getConnection(); // ��ȡ���ݿ�����
        Statement st; // ����Statement����
        try {
            st = con.createStatement(); // ʵ����Statement����
            ResultSet rs = st.executeQuery("select schema_name from SCHEMATA"); // ָ����ѯ�������ݿⷽ��
            while (rs.next()) { // ѭ��������ѯ�����
                list.add(rs.getString(1)); // ����ѯ������ӵ�List������
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list; // ���ز�ѯ���
    }
    
    // �������ݿⷽ��

public boolean mysqldump(String database, String path) { // �������ݿ�
    try {
        Process p = Runtime.getRuntime().exec(
                "cmd.exe /c mysqldump -uroot -p111 " + database + " >"
                        + path + "");           //����������ݱ��ݵ����
        StringBuffer out1 = new StringBuffer(); //�����ַ����������
        byte[] b = new byte[1024];              //�����ֽ�����
        for (int i; ((i = p.getInputStream().read(b)) != -1);) { // ������д�뵽ָ���ļ���
            out1.append(new String(b, 0, i));   //������׷������
        }
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
    return true;
}
    
    
}
