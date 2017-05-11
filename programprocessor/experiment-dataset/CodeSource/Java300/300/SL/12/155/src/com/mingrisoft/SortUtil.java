package com.mingrisoft;

import java.io.*;
import java.util.*;

public class SortUtil {
    // ��ȡ���������ļ�����
    public List<String> getList(String path) {
        LinkedList<File> list = new LinkedList<File>();
        ArrayList<String> listPath = new ArrayList<String>();
        File dir = new File(path);
        File file[] = dir.listFiles();
        for (int i = 0; i < file.length; i++) {
            if (file[i].isDirectory())
                list.add(file[i]);
            else {
                listPath.add(file[i].getAbsolutePath());
            }
        }
        File tmp;
        while (!list.isEmpty()) {
            tmp = list.removeFirst(); // �Ƴ������ؼ����е�һ��
            if (tmp.isDirectory()) {
                file = tmp.listFiles();
                if (file == null)
                    continue;
                for (int i = 0; i < file.length; i++) {
                    if (file[i].isDirectory())
                        list.add(file[i]);
                    else {
                        listPath.add(file[i].getAbsolutePath());
                    }

                }
            } else {

            }
        }
        return listPath;
    }

    // �����ļ��з���

    public void createFolder(String strPath) {
        try {
            File myFilePath = new File(strPath); // �����ļ���ַ����File����
            if (!myFilePath.exists()) { // ���ָ����File���󲻴���
                myFilePath.mkdir(); // ����Ŀ¼
            }
        } catch (Exception e) {
            System.out.println("�½��ļ��в�������");
            e.printStackTrace();
        }
    }

    // �����ļ�����
    public void copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) { // �ļ�����ʱ
                InputStream inStream = new FileInputStream(oldPath); // ����ԭ�ļ�
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; // �ֽ��� �ļ���С
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
