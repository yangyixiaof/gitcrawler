package com.mingrisoft;
public class Test {
    public static void main(String[] args) {
        Circle circle = new Circle(1);//����Բ�ζ��󲢽��뾶���ó�1
        System.out.println("ͼ�ε������ǣ�" + circle.getName());
        System.out.println("ͼ�ε�����ǣ�" + circle.getArea());
        Rectangle rectangle = new Rectangle(1, 1);//�������ζ��󲢽����Ϳ����ó�1
        System.out.println("ͼ�ε������ǣ�" + rectangle.getName());
        System.out.println("ͼ�ε�����ǣ�" + rectangle.getArea());
    }
}
