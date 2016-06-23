package com.mingrisoft.thread;

public class Bank {
    private int account = 100;// 假设账户的初始金额是100
    
    public void deposit(int money) {// 向账户存钱的方法
        account += money;
    }
    
    public int getAccount() {// 获得账户金额的方法
        return account;
    }
}
