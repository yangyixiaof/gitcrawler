package com.mingrisoft.io;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileDeleteStrategy;

public class FileDeleteStrategyTest {
    public static void main(String[] args) {
        File rootFile = new File("d:\\���տƼ�\\�Ƽ�ͼ��");// ����Ҫɾ�����ļ��ж���
        System.out.println("��������ļ��ľ���·����");
        File[] list = rootFile.listFiles();
        for (File file : list) {
            System.out.println(file.getAbsolutePath());// ����ļ����е������ļ��ľ���·��
        }
        FileDeleteStrategy strategy = FileDeleteStrategy.NORMAL;// ʹ����ͨɾ������
        System.out.println("����ͨ����ɾ���ǿ��ļ���d:\\���տƼ���");
        try {
            strategy.delete(new File("d:\\���տƼ�"));
            System.out.println("�ļ���ɾ���ɹ���");// ���ɾ���ɹ�����ʾɾ���ɹ�
        } catch (IOException e) {
            System.out.println("�ļ���ɾ��ʧ�ܣ�");// ���ɾ��ʧ������ʾɾ��ʧ��
        }
        strategy = FileDeleteStrategy.FORCE;// ʹ��ǿ��ɾ������
        System.out.println("��ǿ�Ʋ���ɾ���ǿ��ļ���d:\\���տƼ���");
        try {
            strategy.delete(new File("d:\\���տƼ�"));
            System.out.println("�ļ���ɾ���ɹ���");// ���ɾ���ɹ�����ʾɾ���ɹ�
        } catch (IOException e) {
            System.out.println("�ļ���ɾ��ʧ�ܣ�");// ���ɾ��ʧ������ʾɾ��ʧ��
        }
    }
}
