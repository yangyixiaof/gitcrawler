package com.mingrisoft.oop;


public class Test {
    public static void main(String[] args) {
        System.out.println("�Զ�����ͨ��������");
        Car car = new Car();
        car.setName("Adui");
        car.setSpeed(60);
        System.out.println(car);
        System.out.println("�Զ���GPS������");
        GPSCar gpsCar = new GPSCar();
        gpsCar.setName("Audi");
        gpsCar.setSpeed(60);
        System.out.println(gpsCar);
    }
}
