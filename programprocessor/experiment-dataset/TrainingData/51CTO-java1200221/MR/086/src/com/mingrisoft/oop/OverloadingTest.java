package com.mingrisoft.oop;

public class OverloadingTest {
    
    public void info() {
        System.out.println("��ͨ���������տƼ�1���ˣ�");
    }
    
    public void info(int age) {
        System.out.println("���ط��������տƼ�" + age + "���ˣ�");
    }
    
    public static void main(String[] args) {
        OverloadingTest ot = new OverloadingTest();
        ot.info();
        for (int i = 1; i < 5; i++) {
            ot.info(i);
        }
    }
}
