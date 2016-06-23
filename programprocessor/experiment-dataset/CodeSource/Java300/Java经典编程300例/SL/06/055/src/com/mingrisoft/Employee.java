package com.mingrisoft;

public class Employee implements Cloneable {
    private String name; // ��ʾԱ��������
    private int age; // ��ʾԱ��������
    private Address address;// ��ʾԱ���ĵ�ַ

    public Employee(String name, int age, Address address) {// ���ù��췽�����г�ʼ��
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {// ��дtoString()����
        StringBuilder sb = new StringBuilder();
        sb.append("������" + name + ", ");
        sb.append("���䣺" + age + "\n");
        sb.append("��ַ��" + address);
        return sb.toString();
    }

    @Override
    public Employee clone() {// ʵ��ǳ��¡
        Employee employee = null;
        try {
            employee = (Employee) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return employee;
    }
}
