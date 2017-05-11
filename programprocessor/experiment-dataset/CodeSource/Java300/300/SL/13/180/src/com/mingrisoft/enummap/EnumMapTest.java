package com.mingrisoft.enummap;

import static com.mingrisoft.enummap.Weeks.MONDAY;
import static com.mingrisoft.enummap.Weeks.SATURADAY;
import static com.mingrisoft.enummap.Weeks.SUNDAY;

import java.util.EnumMap;

public class EnumMapTest {
    public static void main(String[] args) {
        EnumMap<Weeks, String> weeks = new EnumMap<Weeks, String>(Weeks.class);
        weeks.put(MONDAY, "����һ");
        weeks.put(SUNDAY, "������");
        System.out.println("EnumMap�еļ�ֵ�Ը�����" + weeks.size());
        System.out.println("EnumMap�еļ�ֵ�ԣ�" + weeks);
        System.out.println("EnumMap���Ƿ������SATURADAY��"
                + weeks.containsKey(SATURADAY));
        System.out.println("EnumMap���Ƿ����ֵ�����գ�" + weeks.containsValue("������"));
        weeks.remove(MONDAY);
        System.out.println("EnumMap�еļ�ֵ�ԣ�" + weeks);
        System.out.println("EnumMap�м�MONDAY��Ӧ��ֵ��" + weeks.get(MONDAY));
    }
}
