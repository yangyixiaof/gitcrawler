package com.mingrisoft.thread;

public class DaemonThreadTest {
    public static void main(String[] args) {
        Thread userThread = new Thread(new Worker()); // �����û��߳�
        Thread daemonThread = new Thread(new Timer()); // �����ػ��߳�
        daemonThread.setDaemon(true); // �����ػ��߳�
        userThread.start(); // �����û��߳�
        daemonThread.start(); // �����ػ��߳�
    }
}
