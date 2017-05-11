package com.mingrisoft.beanutils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BasicDynaClass;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;

public class Test {
    public static void main(String[] args) {
        DynaProperty[] properties = new DynaProperty[3];// ��������3������ֵ������
        // ָ���������ƺ�����
        properties[0] = new DynaProperty("name", String.class);
        properties[1] = new DynaProperty("phoneNumber", String[].class, String.class);
        properties[2] = new DynaProperty("address", Map.class, String.class);
        BasicDynaClass dynaClass = new BasicDynaClass("employee", null, properties);
        DynaBean employee = null;
        try {
            employee = dynaClass.newInstance();// ���DynaBean��ʵ��
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        // Ϊ���Ը�ֵ
        employee.set("name", "���տƼ�");
        employee.set("phoneNumber", new String[10]);// ��������Ҫ�ȳ�ʼ��
        employee.set("phoneNumber", 0, "1234567");
        employee.set("address", new HashMap<String, String>());// ӳ������Ҫ�ȳ�ʼ��
        employee.set("address", "home", "�й�");
        String name = (String) employee.get("name");
        String phoneNumber = (String) employee.get("phoneNumber", 0);
        String address = (String) employee.get("address", "home");
        // �������ֵ
        System.out.println("�½�JavaBean��name���ԣ�" + name);
        System.out.println("�½�JavaBean��phoneNumber���Եĵ�һ��ֵ��" + phoneNumber);
        System.out.println("�½�JavaBean��address����home������Ӧ��ֵ��" + address);
    }
}
