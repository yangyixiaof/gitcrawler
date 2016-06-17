import java.io.*;
import java.util.*;

public class GetProperties {
    
    public String getProperties(String keyName) {
        InputStream ins = getClass().getResourceAsStream(
                "ApplicationResources.properties"); // ���������ļ�����InputStream����
        Properties props = new Properties(); // ����Properties����
        String value = "";
        try {
            props.load(ins); // ���������ж�ȡ�����ļ�����Ϣ
            value = props.getProperty(keyName); // ��ȡָ������������ֵ
        } catch (IOException e) {
            
            e.printStackTrace();
        }
        return value;
    }
    
}
