package com.mingrisoft.thread;

import javax.swing.JTextArea;

public class Transfer implements Runnable {
    private Bank bank;
    private JTextArea textArea;
    
    public Transfer(Bank bank, JTextArea textArea) {// 利用构造方法初始化变量
        this.bank = bank;
        this.textArea = textArea;
    }
    
    public void run() {
        for (int i = 0; i < 10; i++) {// 循环10次向账户存钱
            bank.deposit(10);// 向账户存入10块钱
            String text = textArea.getText();// 获得文本域内容
            textArea.setText(text + "账户的余额是：" + bank.getAccount() + "\n");
        }
    }
}
