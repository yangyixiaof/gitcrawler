package com.mingrisoft.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.RandomStringUtils;

public class IOUtilsTest {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        FileOutputStream out = null;
        FileInputStream in = null;
        try {
            out = new FileOutputStream("d:\\���տƼ�.txt"); // �����ļ����������
            in = new FileInputStream("d:\\���տƼ�.txt"); // �����ļ�����������
            System.out.println("���ļ���д��5������ַ���");
            for (int i = 0; i < 5; i++) {// ���ļ���д��5������ַ���
                IOUtils.write(RandomStringUtils.randomAlphanumeric(5) + "\n", out);
            }
            System.out.println("����ļ��е�����ַ���");
            List<String> list = IOUtils.readLines(in);// ���ļ��ж�ȡ�ַ���
            for (String string : list) {
                System.out.println(string);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(out); // �ͷ���Դ
            IOUtils.closeQuietly(in); // �ͷ���Դ
        }
    }
}
