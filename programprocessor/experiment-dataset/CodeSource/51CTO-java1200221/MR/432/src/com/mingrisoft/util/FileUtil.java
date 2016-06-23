package com.mingrisoft.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {
    public static void copySingleFile(File source, File target) throws IOException {
        FileInputStream input = new FileInputStream(source);// ���������
        FileOutputStream output = new FileOutputStream(target);// ��������
        byte[] b = new byte[1024 * 5];
        int length;
        while ((length = input.read(b)) != -1) {// ����ѭ����ȡ�������е�ȫ������
            output.write(b, 0, length);// ���������е�����д�뵽�������
        }
        output.flush();// ˢ�������
        output.close();// �ͷ��������Դ
        input.close();// �ͷ���������Դ
    }
    
    public static void copyDirectory(File source, File target) throws IOException {
        File[] files = source.listFiles();// ��Դ�ļ���ת����File����
        for (File file : files) {
            if (file.isFile()) {// �����һ���ļ��͵��ø����ļ��ķ���
                copySingleFile(file, new File(target.getAbsolutePath() + "/" + file.getName()));
            } else if (file.listFiles().length == 0) {// �����һ�����ļ��о͵��ô����ļ��еķ���
                new File(target.getAbsolutePath() + "/" + file.getName()).mkdir();
            } else {// �����һ���ǿ��ļ��о͵����������е���
                new File(target.getAbsolutePath() + "/" + file.getName()).mkdir();
                copyDirectory(file, new File(target.getAbsolutePath() + "/" + file.getName()));
            }
        }
    }
    
}
