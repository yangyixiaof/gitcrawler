package com.mingrisoft;
public class Rectangle extends Shape {
    private double length;
    private double width;
    public Rectangle(double length, double width) {//��þ��εĳ��Ϳ�
        this.length = length;
        this.width = width;
    }
    @Override
    public double getArea() {//������ε����
        return length * width;
    }
}
