package com.mingrisoft;

public class Person {
    private String name; // ��������
    private String gender; // �����Ա�
    private int age; // ��������

    public Person() {// ����û�в����Ĺ��췽��
        System.out.println("ʹ���޲ι��췽����������");
    }

    public Person(String name, String gender, int age) {// ���ù��췽����ʼ����
        this.name = name;
        this.gender = gender;
        this.age = age;
        System.out.println("ʹ���вι��췽����������");
    }

    public String getName() { // �������
        return name;
    }

    public String getGender() { // ����Ա�
        return gender;
    }

    public int getAge() { // �������
        return age;
    }

}
