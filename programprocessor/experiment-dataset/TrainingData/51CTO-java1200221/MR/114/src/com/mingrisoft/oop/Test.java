package com.mingrisoft.oop;

public class Test {
    
    public static void main(String[] args) {
        Login login1 = new Login("mingrisoft", "mr");
        System.out.println("���ԭʼ�������Ϣ��");
        System.out.println(login1);
        System.out.println("�����¡�������Ϣ��");
        Login login2 = login1.clone();
        System.out.println(login2);
    }
}
