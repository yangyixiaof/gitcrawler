package com.mingrisoft.exceptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExceptionTest {
    public static void main(String[] args) {
        FileInputStream fis = null;// ����һ���ļ�����������
        try {
            File file = new File("d:\\kira.txt");// ����һ���ļ�����
            fis = new FileInputStream(file);// ��ʼ���ļ�����������
        } catch (FileNotFoundException e) {// �����쳣
            e.printStackTrace();
        } finally {
            try {
                fis.close();// �ͷ���Դ
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
