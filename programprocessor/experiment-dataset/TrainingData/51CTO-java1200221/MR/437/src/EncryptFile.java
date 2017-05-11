import java.io.*;

public class EncryptFile {
    // �ļ��򵥼��ܷ���
public void encry(String frontFile, String backFile) {
    try {
        File f = new File(frontFile);   //���ݼ����ļ���ַ�����ļ�����
        FileInputStream fileInputStream = new FileInputStream(f);   //����FileInputStream����
        byte[] buffer = new byte[fileInputStream.available()];  //�����ж�ȡ�ֽ�
        fileInputStream.read(buffer);       //�����ж�ȡ�ֽ�
        fileInputStream.close();            //��������ر�
        for (int i = 0; i < buffer.length; i++) {   //ѭ�����������ж�ȡ������
            int ibt = buffer[i];
            ibt += 100;                     //���������������������
            ibt %= 256;
            buffer[i] = (byte) ibt;
        }
        FileOutputStream fileOutputStream = new FileOutputStream(new File(
                backFile));                 //���ݼ��ܺ��ļ������ַ�������������
        fileOutputStream.write(buffer, 0, buffer.length);   //���������д����
        fileOutputStream.close();   //�����ر�
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    // �ļ��򵥽��ܷ���

public void unEncry(String frontFile, String backFile) {
    try {
        File f = new File(frontFile);               //����Ҫ��ѹ���ļ�����
        FileInputStream fileInputStream = new FileInputStream(f);   //�����ļ�����������
        byte[] buffer = new byte[fileInputStream.available()];  //�����л�ȡ�ɶ����ֽ���
        fileInputStream.read(buffer);          //�����ж�ȡ�ֽ�
        fileInputStream.close();                //�ر���
        for (int i = 0; i < buffer.length; i++) {   
            int ibt = buffer[i];    
            ibt -= 100;             //�Դ����ж�ȡ�����ݽ������㴦��
            ibt += 256;
            ibt %= 256;
            buffer[i] = (byte) ibt;
        }
        FileOutputStream fileOutputStream = new FileOutputStream(new File(backFile));   //����Ҫд����ļ���ַ���������
        fileOutputStream.write(buffer, 0, buffer.length);       //���������д����
        fileOutputStream.close();       //�����ر�
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
}
