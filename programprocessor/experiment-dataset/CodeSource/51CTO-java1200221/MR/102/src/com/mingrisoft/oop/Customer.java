package com.mingrisoft.oop;

public class Customer {
    
    public static void main(String[] args) {
        System.out.println("�˿�Ҫ����BMW:");
        Car bmw = CarFactory.getCar("BMW");
        System.out.println("��ȡ������" + bmw.getInfo());
        System.out.println("�˿�Ҫ����Benz:");
        Car benz = CarFactory.getCar("Benz");
        System.out.println("��ȡ������" + benz.getInfo());
    }
}
