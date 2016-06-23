package com.mingrisoft.proxy;

import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args) {
        Seller seller = new HouseSeller();
        System.out.println("不使用代理方式：");
        seller.sell();// 普通方式调用sell()方法
        System.out.println("使用代理方式：");
        ClassLoader loader = Seller.class.getClassLoader();// 获得Seller类的类加载器
        seller = (Seller) Proxy.newProxyInstance(loader, new Class[] { Seller.class }, new Agency());
        seller.sell();// 代理方式调用sell()方法
    }
}
