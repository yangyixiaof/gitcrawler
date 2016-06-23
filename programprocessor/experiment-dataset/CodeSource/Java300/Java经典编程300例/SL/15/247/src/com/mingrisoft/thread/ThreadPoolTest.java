package com.mingrisoft.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest {
    public static void main(String[] args) {
        Runtime run = Runtime.getRuntime();// ����Runtime����
        run.gc();// �����������������������Լ������
        long freeMemory = run.freeMemory();// ��õ�ǰ������Ŀ����ڴ�
        long currentTime = System.currentTimeMillis();// ��õ�ǰ�������ʱ��
        for (int i = 0; i < 10000; i++) {// ��������1000���߳�
            new Thread(new TempThread()).start();
        }
        System.out.println("��������1000���߳���ռ�õ��ڴ棺" + (freeMemory - run.freeMemory()) + "�ֽ�");// �鿴�ڴ�ı仯
        System.out.println("��������1000���߳������ĵ�ʱ�䣺" + (System.currentTimeMillis() - currentTime) + "����");// �鿴ʱ��ı仯
        
        run.gc();// ��������������
        freeMemory = run.freeMemory();// ��õ�ǰ������Ŀ����ڴ�
        currentTime = System.currentTimeMillis();// ��õ�ǰ�������ʱ��
        ExecutorService executorService = Executors.newFixedThreadPool(2);// �����̳߳�
        for (int i = 0; i < 1000; i++) {// ʹ���̳߳�����1000���߳�
            executorService.submit(new TempThread());
        }
        System.out.println("ʹ�����ӳ�����1000���߳���ռ�õ��ڴ棺" + (freeMemory - run.freeMemory()) + "�ֽ�");// �鿴�ڴ�ı仯
        System.out.println("ʹ�����ӳش���1000���߳������ĵ�ʱ�䣺" + (System.currentTimeMillis() - currentTime) + "����");// �鿴ʱ��ı仯
    }
}
