package com.mingrisoft.oop;

public class Test {
    public static void main(String[] args) {
        Box box = new Box();
        System.out.println("�����ӵĳ������ó�-1");
        box.setLength(-1);
        System.out.println("�����ӵĿ�����ó�-1");
        box.setWidth(-1);
        System.out.println("�����ӵĸ߶����ó�-1");
        box.setHeight(-1);
        System.out.println("���ӵĳ����ǣ�" + box.getLength());
        System.out.println("���ӵĿ���ǣ�" + box.getWidth());
        System.out.println("���ӵĸ߶��ǣ�" + box.getHeight());
    }
}
