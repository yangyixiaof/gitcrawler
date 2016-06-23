import java.io.*;
import java.util.*;

public class CopyUtil {
    // ��ȡ���������ļ�����
public List getList(String path) {
        LinkedList<File> list = new LinkedList<File>();     //���屣��Ŀ¼�ļ��϶���
        ArrayList<String> listPath = new ArrayList<String>();   //�����ļ���ַ�ļ��϶���
        File dir = new File(path);      //�����ļ���ַ����File����
        File file[] = dir.listFiles();  //��ȡ�ļ����µ��ļ�����
        for (int i = 0; i < file.length; i++) { //ѭ����������
            if (file[i].isDirectory())  //�ж��ļ��Ƿ���һ��Ŀ¼
                list.add(file[i]);      //�򼯺������Ԫ��
            else {
                listPath.add(file[i].getAbsolutePath());    //���ļ�·����ӵ�������
            }
        }
        File tmp;
        while (!list.isEmpty()) {   //������汣���ļ�·���ļ��ϲ�Ϊ��
            tmp = list.removeFirst(); // �Ƴ������ؼ����е�һ��
            if (tmp.isDirectory()) {    
                file = tmp.listFiles();
                if (file == null)
                    continue;
                for (int i = 0; i < file.length; i++) { //ѭ����������
                    if (file[i].isDirectory())  //����ļ���ʾһ��Ŀ¼
                        list.add(file[i]);      
                    else {          //���Ϊһ���ļ�����
                        listPath.add(file[i].getAbsolutePath());
                    }
                }
            }
        }
        return listPath;
    }
    
    /**
     *  �÷����Ը����ļ���·�������ƺ��ļ���·����Ϊ����
     * @param args
     */
public void copyFile(String oldPath, String newPath) {       
    try {
        int bytesum = 0;
        int byteread = 0;
        File oldfile = new File(oldPath);
        if (oldfile.exists()) { // �ļ�����ʱ
            InputStream inStream = new FileInputStream(oldPath); // ����ԭ�ļ�
            FileOutputStream fs = new FileOutputStream(newPath);
            byte[] buffer = new byte[1444];
            while ((byteread = inStream.read(buffer)) != -1) {  //ѭ����ȡ�ļ�
                bytesum += byteread; // ��ȡ�ļ���С
                fs.write(buffer, 0, byteread);  //���ļ���д����
            }
            inStream.close();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}    
}
