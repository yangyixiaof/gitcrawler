package com.mingrisoft.oop;

import java.util.Date;

public class Test {
    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.setName("Java");
        employee.setSalary(100);
        employee.setBirthday(new Date());
        Manager manager = new Manager();
        manager.setName("���տƼ�");
        manager.setSalary(3000);
        manager.setBirthday(new Date());
        manager.setBonus(2000);
        System.out.println("Ա����������" + employee.getName());
        System.out.println("Ա���Ĺ��ʣ�" + employee.getSalary());
        System.out.println("Ա�������գ�" + employee.getBirthday());
        System.out.println("�����������" + manager.getName());
        System.out.println("����Ĺ��ʣ�" + manager.getSalary());
        System.out.println("��������գ�" + manager.getBirthday());
        System.out.println("����Ľ���" + manager.getBonus());
        
    }
}
