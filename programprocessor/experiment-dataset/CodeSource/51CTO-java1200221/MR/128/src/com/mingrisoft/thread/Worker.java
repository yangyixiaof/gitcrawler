package com.mingrisoft.thread;

public class Worker implements Runnable {
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("��Java��̴ʵ䡷��" + i + "�θ��£�");// �û��߳��������һЩ���
        }
    }
}