package com.mingrisoft.io;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.io.comparator.SizeFileComparator;

public class SizeFileComparatorTest {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        File rootFile = new File("D:\\���տƼ�");// ����һ���ļ��ж���
        File[] files = rootFile.listFiles();// ��ø��ļ����������ļ����У�
        System.out.println("�ļ����У���ԭʼ����");
        for (File file : files) {
            System.out.print(file.getName() + "\t");// ����ļ������ļ����У�������
        }
        System.out.println();
        Arrays.sort(files, SizeFileComparator.SIZE_COMPARATOR);// ��files�����������
        System.out.println("�ļ����У���SIZE_COMPARATOR����");
        for (File file : files) {
            System.out.print(file.getName() + "\t");// ����ļ������ļ����У�������
        }
        System.out.println();
        Arrays.sort(files, SizeFileComparator.SIZE_REVERSE); // ��files�����������
        System.out.println("�ļ����У���SIZE_REVERSE����");
        for (File file : files) {
            System.out.print(file.getName() + "\t");// ����ļ������ļ����У�������
        }
        System.out.println();
        Arrays.sort(files, SizeFileComparator.SIZE_SUMDIR_COMPARATOR);
        System.out.println("�ļ����У���SIZE_SUMDIR_COMPARATOR����");
        for (File file : files) {
            System.out.print(file.getName() + "\t");// ����ļ������ļ����У�������
        }
        System.out.println();
        Arrays.sort(files, SizeFileComparator.SIZE_SUMDIR_REVERSE);
        System.out.println("�ļ����У���SIZE_SUMDIR_REVERSE����");
        for (File file : files) {
            System.out.print(file.getName() + "\t");// ����ļ������ļ����У�������
        }
    }
}
