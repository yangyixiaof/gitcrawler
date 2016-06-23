package com.mingrisoft.thread;

public class DeadLock implements Runnable {
    private boolean flag;// ʹ��flag������Ϊ���벻ͬ��ı�־
    private static final Object o1 = new Object();
    private static final Object o2 = new Object();
    
    public void run() {
        String threadName = Thread.currentThread().getName();// ��õ�ǰ�̵߳�����
        System.out.println(threadName + ": flag = " + flag);// �����ǰ�̵߳�flag����ֵ
        if (flag == true) {
            synchronized (o1) {// Ϊo1����
                try {
                    Thread.sleep(1000);// �߳�����1����
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(threadName + "����ͬ����o1׼������o2");// ��ʾ����o1��
                synchronized (o2) {// Ϊo2����
                    System.out.println(threadName + "�Ѿ�����ͬ����o2");// ��ʾ����o2��
                }
            }
        }
        if (flag == false) {
            synchronized (o2) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(threadName + "����ͬ����o2׼������o1");// ��ʾ����o2��
                synchronized (o1) {
                    System.out.println(threadName + "�Ѿ�����ͬ����o1");// ��ʾ����o1��
                }
            }
        }
    }
    
    public static void main(String[] args) {
        DeadLock d1 = new DeadLock();// ����DeadLock����d1
        DeadLock d2 = new DeadLock();// ����DeadLock����d2
        d1.flag = true; // ��d1��flag����Ϊtrue
        d2.flag = false; // ��d2��flag����Ϊfalse
        new Thread(d1).start();// �����߳�������d1��run()����
        new Thread(d2).start();// �����߳�������d2��run()����
    }
}
