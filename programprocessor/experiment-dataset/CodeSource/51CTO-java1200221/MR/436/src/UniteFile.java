import java.io.*;
import java.util.*;
public class UniteFile {
    byte b1[];
    FileInputStream fi1;
    FileOutputStream fo;    
public void writeFiles(List<File>  files, String fileName) {      
    try {
        fo = new FileOutputStream(fileName, true);  //�����ļ������ַ����FileOutputStream����
        for (int i = 0; i < files.size(); i++) {    //ѭ������Ҫ���Ƶ��ļ�����        
            File file =  files.get(i);  //��ȡ�������ļ�����
            fi1 = new FileInputStream(file);    //����FileInputStream����
            b1 = new byte[fi1.available()];     //�����л�ȡ�ֽ���
            fi1.read(b1);               //��ȡ����
            fo.write(b1);               //���ļ���д����
        }
    } catch (Exception e) {            
        e.printStackTrace();
    }        
} 
}
