package com.mingrisoft;

public class OverloadingTest {
    public void info() {// ����û�в�����info()����
        System.out.println("��ͨ���������տƼ�1���ˣ�");
    }

    public void info(int age) {// ����������Ͳ�����info()����
        System.out.println("���ط��������տƼ�" + age + "���ˣ�");
    }

    public static void main(String[] args) {
        OverloadingTest ot = new OverloadingTest();// ����OverloadingTest�����
        ot.info();// �����޲���info()����
        for (int i = 1; i < 5; i++) {// �����в���info()����
            ot.info(i);
        }
    }

}
