import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class FindEmp {
    private Connection conn ;   //����Connection����
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


public List getSubTable() {
    List list = new ArrayList<Emp>(); // ����List���϶���
    conn = getConn(); // ��ȡ�����ݿ������
    try {
        Statement statement = conn.createStatement(); // ��ȡStatement����
        String sql = "select eName,headship,dept,laborage from tb_emp  where laborage >(select avg(laborage) from tb_emp)"; // �����ѯ���
        ResultSet rest = statement.executeQuery(sql); // ִ�в�ѯ����ȡ��ѯ�����
        while (rest.next()) { // ѭ��������ѯ�����
            Emp emp = new Emp(); // ���������ݱ��Ӧ��JavaBean����               
            emp.setName(rest.getString(1));
            emp.setHeadship(rest.getString(2));
            emp.setDept(rest.getString(3));
            emp.setLaborage(rest.getFloat(4));
            list.add(emp); // ��������ӵ�������
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list; // ���ز�ѯ���
}

    /**
     * @param args
     */
    public static void main(String[] args) {
       FindEmp findEmp = new FindEmp();
       List list = findEmp.getSubTable();
       System.out.println("��ѯ���ʸ���Ա��ƽ����˾��Ա����Ϣ��");
       for(int i = 0;i<list.size();i++){
          Emp emp = (Emp)list.get(i);
          System.out.println("������"+emp.getName()+" ְλ��"+emp.getHeadship()+" ���ţ�"+emp.getDept()+" ���ʣ�"+emp.getLaborage());
       }
        
    }
    
}
