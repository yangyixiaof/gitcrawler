import java.io.FileOutputStream;
import java.util.Properties;

public class SaveProperties {
    
    /**
     * @param args
     */
    
public void saveProperties(String key, String value) {
    Properties properties = new Properties(); // ����Properties����
    properties.setProperty(key, value); // ���������ļ�ֵ
    try {
        FileOutputStream out = new FileOutputStream(
                "C://message.properties");// �������������
        properties.store(out, "test"); // ����Ϣͨ����д�뵽�����ļ�
        out.close(); // �ر���
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
   
}
