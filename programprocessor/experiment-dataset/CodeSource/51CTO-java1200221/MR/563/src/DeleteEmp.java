import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class DeleteEmp {
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
    //��ѯ���ű��е���������
    public List getDept() {
        List list = new ArrayList<Dept>(); // ����List���϶���
        conn = getConn(); // ��ȡ�����ݿ������
        try {
            Statement statement = conn.createStatement(); // ��ȡStatement����
            String sql = "select * from tb_mrdept "; // �����ѯ���
            ResultSet rest = statement.executeQuery(sql); // ִ�в�ѯ����ȡ��ѯ�����
            while (rest.next()) { // ѭ��������ѯ�����
                Dept dept = new Dept();
                dept.setId(rest.getInt(1));
                dept.setDeptName(rest.getString(2));
                list.add(dept);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list; // ���ز�ѯ���
    }

public void deleteEmp() {        
    conn = getConn(); // ��ȡ�����ݿ������
    try {
        Statement statement = conn.createStatement(); // ��ȡStatement����
        String sql = "delete from tb_mrdept where person not in (select name from tb_mremp )"; //��ɾ�������ʹ���Ӳ�ѯ
        statement.executeUpdate(sql);            //ִ��ɾ��SQL���
    } catch (Exception e) {
        e.printStackTrace();
    }        
}
    public static void main(String[] args) {
        DeleteEmp deleteEmp = new DeleteEmp();
        List list = deleteEmp.getDept();
        System.out.println("ɾ��ǰ�����ݣ�");
        for(int i  = 0;i<list.size();i++){
            Dept dept = (Dept)list.get(i);
            System.out.println("���Ϊ��"+dept.getId()+"  ��������Ϊ��"+dept.getDeptName());
        }
        System.out.println("\n ɾ��Ա�����в����ڵĲ�����Ϣ�󣬲�����Ϣ���е�����Ϊ��");
        deleteEmp.deleteEmp();
        List list2 = deleteEmp.getDept();
        for(int i  = 0;i<list2.size();i++){
            Dept dept = (Dept)list2.get(i);
            System.out.println("���Ϊ��"+dept.getId()+"  ��������Ϊ��"+dept.getDeptName());
        }
    }
    
}
