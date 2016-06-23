package com.mingrisoft.proxy;

public class HouseSeller implements Seller {
    public void sell() {
        System.out.println("销售人员在卖房子");// 实现接口的方法，用输出来区别该类
    }
}
