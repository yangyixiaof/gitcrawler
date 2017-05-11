import java.sql.*;
import java.util.*;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;

public class ResultUtil {
    // ��ȡ���ݿ�����
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
    public List getGradeDesc() {
        List list = new ArrayList();    // �������ڱ��淵��ֵ��List����
        conn = getConn();               // ��ȡ���ݿ�����
        try {
            Statement staement = conn.createStatement();
               // ���彫ͼ�������Ϣ���������SQL���
            String sql = "select * from tb_abroad order by substring(name,1,1)"; 
            ResultSet set = staement.executeQuery(sql);     // ִ�в�ѯ��䷵�ز�ѯ�����
            while (set.next()) {                        // ѭ��������ѯ�����
                Abroad abrod = new Abroad();                // ������ѧ���ɼ����Ӧ��Grade����
                abrod.setId(set.getInt(1));                 // ���ö�������
                abrod.setName(set.getString(2));
                abrod.setSurname(set.getString(3));
                abrod.setNationality(set.getString(4));
                list.add(abrod);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;                                    // ���ز�ѯ����
    }    
    public static void main(String[] args) {
        ResultUtil util = new ResultUtil();
        List list = util.getGradeDesc();
        System.out.println("����ѧ������������");
        for(int i = 0;i<list.size();i++){
            Abroad abrod = (Abroad)list.get(i);         // ������ѧ���ɼ����Ӧ��Grade����
            System.out.println("���Ϊ��"+abrod.getId()+"  ����Ϊ��"+abrod.getName()+"  ��Ϊ��"+abrod.getSurname()+
                    "  ����Ϊ��"+abrod.getNationality());
        }
        
    }
}
