package com.mingrisoft;
public class Circle extends Shape {
    private double radius;
    public Circle(double radius) {//���Բ�εİ뾶
        this.radius = radius;
    }
    @Override
    public double getArea() {//����Բ�ε����
        return Math.PI * Math.pow(radius, 2);
    }
}
