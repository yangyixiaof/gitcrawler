package com.cdd.util;
import java.io.*;
public class Employ {
public static void main(String args[]) {
    File file = new File("Example8.txt");
    try {
        if (!file.exists())                      // ����ļ�������
            file.createNewFile();                // �������ļ�
        InputStreamReader isr = new InputStreamReader(System.in);   //��������������
        BufferedReader br = new BufferedReader(isr);            
        System.out.println("�����룺");
        String str = br.readLine();             //��ȡ�û��������Ϣ
        System.out.println("������������ǣ�" + str);
        FileWriter fos = new FileWriter(file, true);         // �����ļ������
        BufferedWriter bw = new BufferedWriter(fos);
        bw.write(str);                          //���ļ�д����Ϣ
        br.close();
        bw.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

}
