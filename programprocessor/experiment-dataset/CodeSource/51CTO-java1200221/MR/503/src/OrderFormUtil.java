import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderFormUtil {
    private Connection conn;
    
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
    
    // ���尴ָ����ָ�����������ѯ���ݷ���
    
public List getOrderDesc() {
    List list = new ArrayList(); // �������ڱ��淵��ֵ��List����
    conn = getConn(); // ��ȡ���ݿ�����
    try {
        Statement staement = conn.createStatement();
        String sql = "select visePerson,sum(clientMoney) as money from tb_orderForm  group by visePerson order by money desc"; // ���彫��������з���ͳ�Ƶ�SQL���
        ResultSet set = staement.executeQuery(sql); // ִ�в�ѯ��䷵�ز�ѯ�����
        while (set.next()) { // ѭ��������ѯ�����
            OrderForm orderForm = new OrderForm();             
            orderForm.setClientMoney(set.getFloat("money"));
            orderForm.setVisePerson(set.getString("visePerson"));
            list.add(orderForm);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}
    public static void main(String[] args) {
        OrderFormUtil util = new OrderFormUtil();
        List list = util.getOrderDesc();
        System.out.println("Ա��ǩԼ���а�");
        for(int i = 0;i<list.size();i++){
            OrderForm orderForm = (OrderForm)list.get(i);
            System.out.println("ǩԼ�ˣ� "+orderForm.getVisePerson()+"��  ������ "+orderForm.getClientMoney());
        }
    }
}
