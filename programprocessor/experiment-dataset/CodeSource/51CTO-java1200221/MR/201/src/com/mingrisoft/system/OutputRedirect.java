package com.mingrisoft.system;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

public class OutputRedirect {
    public static void main(String[] args) throws IOException {
        File file = new File("d:\\debug.txt");// ����һ���ļ��������ض����������ı���Ϣ
        PrintStream out = new PrintStream(file);
        PrintStream cloneOut = System.out;// ʹ�ñ����������̨���
        System.setOut(out);// ����׼����ض���PrintStream
        System.out.println("���տƼ������ݣ�");// ���ñ�׼���������
        System.out.println("Java�����ŵ���ͨ����2�棩");// ���ñ�׼���������
        System.out.println("��ƵѧJava");// ���ñ�׼���������
        System.out.println("ϸ˵Java");// ���ñ�׼���������
        out.close();// �ر�PrintStream
        System.setOut(cloneOut);// ����׼����ض��򵽿���̨
        BufferedReader in = new BufferedReader(new FileReader(file));// ��ȡ�ļ�
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(line);// �ڿ���̨������ļ�
        }
        in.close();// �ر�������
    }
}
