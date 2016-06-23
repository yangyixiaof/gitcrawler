package com.mingrisoft.time;

import java.util.TimeZone;

public class LocaleTimeZone {
    public static void main(String[] args) {
        TimeZone zone = TimeZone.getDefault();// ��õ�ǰʱ��
        System.out.println("��ǰ��������ʱ����" + zone.getDisplayName());// ���ʱ��������
        zone = TimeZone.getTimeZone("Asia/Taipei");// ���̨��ʱ��
        System.out.println("�й�̨������ʱ����" + zone.getDisplayName());
        System.out.println("ʱ�����������ƣ�" + zone.getDisplayName(true, TimeZone.LONG));
        System.out.println("ʱ������д���ƣ�" + zone.getDisplayName(true, TimeZone.SHORT));
    }
}
