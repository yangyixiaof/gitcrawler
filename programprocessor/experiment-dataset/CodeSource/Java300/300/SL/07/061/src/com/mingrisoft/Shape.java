package com.mingrisoft;
public abstract class Shape {
    public String getName() {//���ͼ�ε�����
        return this.getClass().getSimpleName();
    }
    public abstract double getArea();//���ͼ�ε����
}
