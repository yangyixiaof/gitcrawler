package com.mingrisoft.oop;

public class PersonTest {
    public static void main(String[] args) {
        Person person1 = new Person();
        Person person2 = new Person("���տƼ�", "��", 11);
        System.out.println("Ա��1����Ϣ");
        System.out.println("Ա��������" + person1.getName());
        System.out.println("Ա���Ա�" + person1.getGender());
        System.out.println("Ա�����䣺" + person1.getAge());
        System.out.println("Ա��2����Ϣ");
        System.out.println("Ա��������" + person2.getName());
        System.out.println("Ա���Ա�" + person2.getGender());
        System.out.println("Ա�����䣺" + person2.getAge());
    }
}
