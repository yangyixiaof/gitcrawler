package com.mingrisoft.oop;

public class Test {
    public static void main(String[] args) {
        System.out.println("��¡֮ǰ��");
        Employee employee1 = new Employee();
        employee1.setName("���տƼ�");
        employee1.setAge(12);
        System.out.println("Ա��1����Ϣ��");
        System.out.println(employee1);
        System.out.println("��¡֮��");
        Employee employee2 = employee1;
        employee2.setName("���Ͻ�ͨ��ѧ");
        employee2.setAge(114);
        System.out.println("Ա��2����Ϣ��");
        System.out.println(employee2);
        System.out.println("Ա��1����Ϣ��");
        System.out.println(employee1);
    }
}
