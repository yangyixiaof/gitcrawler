package com.mingrisoft.io;

import java.io.File;

import org.apache.commons.io.filefilter.SizeFileFilter;

public class SizeFileFilterTest {
    public static void main(String[] args) {
        File dir = new File("d:\\���տƼ�");// ����һ���ļ��ж���
        System.out.println("����ǰ�ļ����е��ļ���");
        File[] files = dir.listFiles();// ��ø��ļ����������ļ������ļ���
        for (File file : files) {// ����ļ������ļ������ֺʹ�С
            System.out.println(file.getName() + "�Ĵ�С�ǣ�" + file.length());
        }
        System.out.println("���˺��ļ����е��ļ���");
        String[] fileNames = dir.list(new SizeFileFilter(1024 * 1024));// ���˵�<1M���ļ�
        for (int i = 0; i < fileNames.length; i++) {
            System.out.println(fileNames[i]);
        }
    }
}
