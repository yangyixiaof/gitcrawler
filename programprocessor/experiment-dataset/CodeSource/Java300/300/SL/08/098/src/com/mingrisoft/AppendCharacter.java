package com.mingrisoft;

public class AppendCharacter {
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        String appendStr = "";// �����ַ�������
        long startTime = System.nanoTime();// ��ʼ����
        for (int i = 20000; i < 50000; i++) {// ����30000���ַ�
            appendStr += (char) i;// �ַ�����ÿ���ַ�ִ�����Ӳ���
        }
        long endTime = System.nanoTime();// ������ʱ
        System.out.println("String׷���ַ�3�����");
        // �����ʱ
        System.out.println("��ʱ��" + (endTime - startTime) / 1000000d + "����");
        // ///////////////////////////////////////////////////////////////
        StringBuilder strBuilder = new StringBuilder();// �����ַ���������
        startTime = System.nanoTime();// ��ʼ��ʱ
        for (int i = 20000; i < 50000; i++) {// ����30000���ַ�
            strBuilder.append((char) i);// ��ÿ���ַ�׷�ӵ�������
        }
        endTime = System.nanoTime();// ��������
        System.out.println("�ַ���������׷���ַ�3�����");
        // �����ʱ
        System.out.print("��ʱ��" + (endTime - startTime) / 1000000d + "����");
    }
    
}
