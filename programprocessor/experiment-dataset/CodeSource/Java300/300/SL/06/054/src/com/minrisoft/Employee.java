package com.minrisoft;

public class Employee {
    private String name;// ��ʾԱ��������
    private int age; // ��ʾԱ��������

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

    @Override
    public String toString() {// ��дtoString()����
        return "������" + name + ", ���䣺" + age;
    }

}
