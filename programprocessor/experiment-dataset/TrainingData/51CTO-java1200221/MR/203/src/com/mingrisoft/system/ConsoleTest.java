package com.mingrisoft.system;

import java.io.Console;
import java.util.Arrays;

public class ConsoleTest {
    public static void main(String[] args) {
        Console console = System.console();// ���Console����
        String username = console.readLine("�������û�����"); // ����û���
        char[] password = console.readPassword("���������룺"); // �������
        System.out.println("�����û����ǣ�" + username);// ����û���
        System.out.print("���������ǣ�");
        for (char c : password) {
            System.out.print(c);// �������
        }
        Arrays.fill(password, 'a');// ���������������Ԫ��ȫ������Ϊ��a��
    }
}
