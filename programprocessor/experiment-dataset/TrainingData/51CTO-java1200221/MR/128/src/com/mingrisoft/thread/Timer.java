package com.mingrisoft.thread;

public class Timer implements Runnable {
    public void run() {
        long currentTime = System.currentTimeMillis();// ���ϵͳ��ǰʱ��
        long processTime = 0;// ����ϵͳ����ʱ��Ϊ0
        while (true) {// ���ϵͳ����ʱ�䷢���仯�����
            if ((System.currentTimeMillis() - currentTime) > processTime) {
                processTime = System.currentTimeMillis() - currentTime;
                System.out.println("��������ʱ�䣺" + processTime);
            }
        }
    }
}