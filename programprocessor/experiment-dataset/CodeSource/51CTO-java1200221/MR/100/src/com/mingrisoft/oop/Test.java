package com.mingrisoft.oop;

public class Test {
    public static void main(String[] args) {
        Circle circle = new Circle(1);
        System.out.println("ͼ�ε������ǣ�" + circle.getName());
        System.out.println("ͼ�ε�����ǣ�" + circle.getArea());
        Rectangle rectangle = new Rectangle(1, 1);
        System.out.println("ͼ�ε������ǣ�" + rectangle.getName());
        System.out.println("ͼ�ε�����ǣ�" + rectangle.getArea());
    }
}
