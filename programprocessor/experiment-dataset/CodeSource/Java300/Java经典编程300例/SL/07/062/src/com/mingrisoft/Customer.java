package com.mingrisoft;
public class Customer {
    public static void main(String[] args) {
        System.out.println("�˿�Ҫ����BMW:");
        Car bmw = CarFactory.getCar("BMW");		//�û�Ҫ����BMW
        System.out.println("��ȡ������" + bmw.getInfo());	//��ȡBMW
        System.out.println("�˿�Ҫ����Benz:");
        Car benz = CarFactory.getCar("Benz");	//�û�Ҫ����Benz
        System.out.println("��ȡ������" + benz.getInfo());//��ȡBenz
    }
}

