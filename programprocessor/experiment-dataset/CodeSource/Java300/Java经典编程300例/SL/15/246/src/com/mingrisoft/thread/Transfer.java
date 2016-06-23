package com.mingrisoft.thread;

import java.util.concurrent.Callable;

import javax.swing.JTextArea;

public class Transfer implements Callable<Integer> {
    private Bank bank;
    private JTextArea textArea;
    
    public Transfer(Bank bank, JTextArea textArea) {// ���ù��췽����ʼ������
        this.bank = bank;
        this.textArea = textArea;
    }
    
    public Integer call() {
        for (int i = 0; i < 10; i++) {// ѭ��10�����˻��д�Ǯ
            bank.deposit(10);
            String text = textArea.getText();
            textArea.setText(text + "�˻�������ǣ�" + bank.getAccount() + "\n");
        }
        return bank.getAccount();// ����˻������
    }
}
