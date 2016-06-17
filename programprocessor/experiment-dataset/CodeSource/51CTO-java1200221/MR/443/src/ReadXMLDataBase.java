import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class ReadXMLDataBase {
private Document document;    //����Document����
public String readXml(String passWord) {
    File xml_file = new File("users.xml");  //����XML�ļ���ַ
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  //�����XMl�ĵ���ȡ����DOM����Ľ�����
    try {
        DocumentBuilder builder = factory.newDocumentBuilder(); 
        document = builder.parse(xml_file); //����XML��ȡDOM�ĵ�ʵ��
    } catch (Exception e) {
        e.printStackTrace();
    }      
    String subNodeTag = document.getElementsByTagName(passWord).item(0)
            .getFirstChild().getNodeValue();    //��ȡָ���ڵ㱣���ֵ
   return subNodeTag;           //���ض�ȡ����Ϣ
}
    
}
