package com.mingrisoft.beanutils;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

public class Test {
    public static void main(String[] args) {
        Employee employee1 = new Employee();// ����Employee����
        Employee employee2 = new Employee();// ����Employee����
        try {
            // Ϊemployee1��ֵ
            PropertyUtils.setSimpleProperty(employee1, "name", "���տƼ�");
            PropertyUtils.setIndexedProperty(employee1, "phoneNumber", 0, "1234567");
            PropertyUtils.setMappedProperty(employee1, "address", "home", "�й�");
            BeanUtils.copyProperties(employee2, employee1);// ��employee1���Ƶ�employee2
            // ���employee2������ֵ
            String name = (String) PropertyUtils.getSimpleProperty(employee2, "name");
            String phoneNumber = (String) PropertyUtils.getIndexedProperty(employee2, "phoneNumber", 0);
            String address = (String) PropertyUtils.getMappedProperty(employee2, "address", "home");
            // ���employee2������ֵ
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
