package com.mingrisoft.proxy;

import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args) {
        Seller seller = new HouseSeller();
        System.out.println("��ʹ�ô���ʽ��");
        seller.sell();// ��ͨ��ʽ����sell()����
        System.out.println("ʹ�ô���ʽ��");
        ClassLoader loader = Seller.class.getClassLoader();// ���Seller����������
        seller = (Seller) Proxy.newProxyInstance(loader, new Class[] { Seller.class }, new Agency());
        seller.sell();// ����ʽ����sell()����
    }
}
