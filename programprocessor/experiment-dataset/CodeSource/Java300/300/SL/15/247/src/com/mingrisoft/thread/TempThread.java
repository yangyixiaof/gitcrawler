package com.mingrisoft.thread;

public class TempThread implements Runnable {// �����õ�Runnable�ӿ�ʵ����
    private int id = 0;
    
    @Override
    public void run() {// run()������id����������
        id++;
    }
}