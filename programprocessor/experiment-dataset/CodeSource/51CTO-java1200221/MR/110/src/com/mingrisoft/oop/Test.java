package com.mingrisoft.oop;

public class Test {
    public static void main(String[] args) {
        System.out.println("��¡֮ǰ��");
        Address address = new Address("�й�", "����", "����");
        Employee employee1 = new Employee("���տƼ�", 12, address);
        System.out.println("Ա��1����Ϣ��");
        System.out.println(employee1);
        System.out.println("��¡֮��");
        Employee employee2 = employee1.clone();
        employee2.getAddress().setState("�й�");
        employee2.getAddress().setProvince("�Ĵ�");
        employee2.getAddress().setCity("�ɶ�");
        employee2.setName("���Ͻ�ͨ��ѧ");
        employee2.setAge(114);
        System.out.println("Ա��2����Ϣ��");
        System.out.println(employee2);
        System.out.println("Ա��1����Ϣ��");
        System.out.println(employee1);
    }
}