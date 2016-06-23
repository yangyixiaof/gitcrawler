package com.mingrisoft.beanutils;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;

public class Test {
    public static void main(String[] args) {
        Employee employee = new Employee();// ���һ��Employee����
        // ���Employee���������ֵ���������Ȳ�δ���丳ֵ������Ӧ��Ϊ��
        String name = employee.getName();
        String phoneNumber = employee.getPhoneNumber()[0];
        String address = employee.getAddress().get("home");
        // ����ջ�õ�����ֵ
        System.out.println("��������ֵ֮ǰ��");
        System.out.println("name���ԣ�" + name);
        System.out.println("phoneNumber���Եĵ�һ��ֵ��" + phoneNumber);
        System.out.println("address����home������Ӧ��ֵ��" + address);
        try {// ʹ��PropertyUtils�����ط�����Employee�������ֵ
            PropertyUtils.setSimpleProperty(employee, "name", "���տƼ�");
            PropertyUtils.setIndexedProperty(employee, "phoneNumber", 0, "1234567");
            PropertyUtils.setMappedProperty(employee, "address", "home", "�й�");
            // ���Employee���������ֵ�����ڸոն��丳ֵ������Ӧ��Ϊ����
            name = (String) PropertyUtils.getSimpleProperty(employee, "name");
            phoneNumber = (String) PropertyUtils.getIndexedProperty(employee, "phoneNumber", 0);
            address = (String) PropertyUtils.getMappedProperty(employee, "address", "home");
            // ����ջ�õ�����ֵ
            System.out.println("��������ֵ֮��");
            System.out.println("name���ԣ�" + name);
            System.out.println("phoneNumber���Եĵ�һ��ֵ��" + phoneNumber);
            System.out.println("address����home������Ӧ��ֵ��" + address);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
