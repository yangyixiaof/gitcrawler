package com.mingrisoft;

import java.io.FileInputStream;
import java.io.*;
import javax.swing.JFrame;
import javax.swing.ProgressMonitorInputStream;
public class ProgressMonitorTest {
    
    public void useProgressMonitor(JFrame frame, String copyPath, String newPath) {
        try {
            File file = new File(copyPath); // ����Ҫ���Ƶ��ļ�����File����
            File newFile = new File(newPath); // ���ݸ��ƺ��ļ��ı����ַ����File����
            FileOutputStream fop = new FileOutputStream(newFile); // ����FileOutputStream����
            InputStream in = new FileInputStream(file);
            // ��ȡ�ļ�������ܺ�ʱ����2�룬�����Զ�����һ�����ȼ��Ӵ��ڡ�
            ProgressMonitorInputStream pm = new ProgressMonitorInputStream(
                    frame, "�ļ������У����Ժ�...", in);
            int c = 0;
            byte[] bytes = new byte[1024]; // ����byte����
            while ((c = pm.read(bytes)) != -1) { // ѭ����ȡ�ļ�
                fop.write(bytes, 0, c); // ͨ����д����
            }
            fop.close(); // �ر������
            pm.close(); // �ر�������
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
