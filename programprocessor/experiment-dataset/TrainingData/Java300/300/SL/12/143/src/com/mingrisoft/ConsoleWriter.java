package com.mingrisoft;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ConsoleWriter {

    public static void main(String[] args) {
        System.out.println("������Ҫ������ַ�����");// ��ʾ�û������ַ���
        Scanner scanner = new Scanner(System.in);// ��ÿ���̨������
        String text = scanner.nextLine();// ����û�����
        FileWriter writer = null;
        try {
            writer = new FileWriter("d://test.txt");// ����ļ�д����
            writer.write(text);// д���ַ���
            writer.flush();// ˢ�»���
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
