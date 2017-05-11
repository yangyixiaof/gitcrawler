package com.mingrisoft.thread;

public class Bank {
    // ʹ��ThreadLocal�������������account
    private static ThreadLocal<Integer> account = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 100;// ��дinitialValue()��������account�ĳ�ʼֵ��Ϊ100
        }
    };
    
    public void deposit(int money) {
        account.set(account.get() + money);// ����account��get()��set()����ʵ�ִ�Ǯ
    }
    
    public int getAccount() {// ����˻����
        return account.get();
    }
}
