package com.mingrisoft.oop;

public class Person {
    private String name;
    private String gender;
    private int age;
    
    public Person() {
        System.out.println("ʹ���޲ι��췽����������");
    }
    
    public Person(String name, String gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        System.out.println("ʹ���вι��췽����������");
    }
    
    public String getName() {
        return name;
    }
    
    public String getGender() {
        return gender;
    }
    
    public int getAge() {
        return age;
    }
    
}
