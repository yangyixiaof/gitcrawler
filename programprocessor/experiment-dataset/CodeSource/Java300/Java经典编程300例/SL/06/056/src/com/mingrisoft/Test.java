package com.mingrisoft;

public class Test {
    public static void main(String[] args) {
        System.out.println("��¡֮ǰ��");
        Address address = new Address("�й�", "����", "����");// ����address����
        Employee employee1 = new Employee("��XX", 30, address);// ����employee1����
        System.out.println("Ա��1����Ϣ��");
        System.out.println(employee1);// ���employee1����
        System.out.println("��¡֮��");
        Employee employee2 = employee1.clone();// ʹ�ÿ�¡����employee2����
        employee2.getAddress().setState("�й�"); // �޸�Ա����ַ
        employee2.getAddress().setProvince("�Ĵ�"); // �޸�Ա����ַ
        employee2.getAddress().setCity("�ɶ�"); // �޸�Ա����ַ
        employee2.setName("��XX"); // �޸�Ա������
        employee2.setAge(24);// �޸�Ա������
        System.out.println("Ա��1����Ϣ��");
        System.out.println(employee1);// ���employee1����
        System.out.println("Ա��2����Ϣ��");
        System.out.println(employee2);// ���employee2����
    }

}
