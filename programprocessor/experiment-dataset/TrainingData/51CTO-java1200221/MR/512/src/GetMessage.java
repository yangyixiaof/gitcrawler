import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GetMessage {
    Connection conn = null;
    
    // ��ȡ���ݿ�����
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

public List getBccdSell() {
    List list = new ArrayList(); // �������ڱ��淵��ֵ��List����
    conn = getConn(); // ��ȡ���ݿ�����
    try {
        Statement staement = conn.createStatement();
        String sql = "select * from tb_bccdSell where bccdDate in ('2010/6/20','2010/6/21')"; // �����ѯ���ݵ�SQL���
        ResultSet set = staement.executeQuery(sql); // ִ�в�ѯ��䷵�ز�ѯ�����
        while (set.next()) { // ѭ��������ѯ�����
            Bccd bccd = new Bccd(); // ���������ݶ�Ӧ��JavaBean����
            bccd.setId(set.getInt(1));      //���ö�������
            bccd.setBccdName(set.getString(2));
            bccd.setBccdCount(set.getInt(3));
            bccd.setBccdPrice(set.getFloat(4));
            bccd.setBccdDate(set.getString(5));
            list.add(bccd); // �򼯺�����Ӷ���
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list; // ����List����
}
    
    public static void main(String[] args) {
        GetMessage message = new GetMessage();
        List list = message.getBccdSell();
        System.out.println("��2010/6/20��2010/6/21�����ۼ�¼�ı�̴ʵ���");
        for (int i = 0; i < list.size(); i++) {
            Bccd bccd = (Bccd) list.get(i);            
            String bccdName = bccd.getBccdName();
            int count = bccd.getBccdCount();
            float price = bccd.getBccdPrice();
            String date = bccd.getBccdDate();
            System.out.println(" ���ƣ�" + bccdName + " ������" + count
                    + " �۸�" + price + " ���ڣ�" + date);
        }
    }
}
