package com.mingrisoft;

import java.io.*;
import java.util.*;

public class UniteUtil {
    /**
     * @param file
     *            :Ҫ���кϲ����ļ��������
     * @param cunDir
     *            ���ϲ����ļ��ı���·��
     * @param hz
     *            ���ϲ����ļ��ĸ�ʽ
     */
    public void heBing(File[] file, File cunDir, String hz) {
        try {
            File heBingFile = new File(cunDir.getAbsoluteFile() + "\\UNTIE" + hz); // ָ���ָ���ļ����ļ���
            if (!heBingFile.isFile()) {
                heBingFile.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(heBingFile); // ����FileOutputStream����
            for (int i = 0; i < file.length; i++) { // ѭ������Ҫ���кϲ����ļ��������
                FileInputStream fis = new FileInputStream(file[i]);
                int len = (int) file[i].length(); // ��ȡ�ļ�����
                byte[] bRead = new byte[len];
                fis.read(bRead); // ��ȡ�ļ�
                fos.write(bRead); // д���ļ�
                fis.close(); // �����ر�
            }
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

}
