package com.minrisoft;

public class Test {
    public static void main(String[] args) {
        System.out.println("��¡֮ǰ��");
        Employee employee1 = new Employee();// ����Employee����employee1
        employee1.setName("��XX");// Ϊemployee1��������
        employee1.setAge(30);// Ϊemployee1��������
        System.out.println("Ա��1����Ϣ��");
        System.out.println(employee1); // ���employee1����Ϣ
        System.out.println("��¡֮��");
        Employee employee2 = employee1; // ��employee1��ֵ��employee2
        employee2.setName("��XX");// Ϊemployee2��������
        employee2.setAge(24);// Ϊemployee2��������
        System.out.println("Ա��1����Ϣ��");
        System.out.println(employee1);// ���employee1����Ϣ
        System.out.println("Ա��2����Ϣ��");
        System.out.println(employee2);// ���employee2����Ϣ
    }
}
