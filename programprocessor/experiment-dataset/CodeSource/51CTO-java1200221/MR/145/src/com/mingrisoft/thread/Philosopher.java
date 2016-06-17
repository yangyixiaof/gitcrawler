package com.mingrisoft.thread;

import java.util.Random;

import javax.swing.JTextArea;

public class Philosopher implements Runnable {
    
    private int id;
    private ChopstickArray chopstickArray;
    private boolean state;
    private JTextArea thinkingTextArea;
    private JTextArea eatingTextArea;
    private JTextArea waitingTextArea;
    
    public Philosopher(int id, ChopstickArray chopstickArray, JTextArea thinkingTextArea, JTextArea eatingTextArea, JTextArea waitingTextArea) {
        this.id = id;
        this.chopstickArray = chopstickArray;
        this.thinkingTextArea = thinkingTextArea;
        this.eatingTextArea = eatingTextArea;
        this.waitingTextArea = waitingTextArea;
    }
    
    public synchronized void thinking() {
        if (state) {
            chopstickArray.get(id).setAvailable(true);
            chopstickArray.getLast(id).setAvailable(true);
            String text = thinkingTextArea.getText();
            thinkingTextArea.setText(text + this + " ��˼��\n");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        state = false;
    }
    
public synchronized void eating() {
    if (!state) {// state��һ������ֵ��true��ʾ��ѧ�Ҹղŵ�״̬�ǳԷ���false��ʾ˼��
        if (chopstickArray.get(id).isAvailable()) {// �����ѧ�����ֱߵĿ��ӿ���
            if (chopstickArray.getLast(id).isAvailable()) {// �����ѧ�����ֱߵĿ��ӿ���
                chopstickArray.get(id).setAvailable(false);// �������ֿ��Ӳ�����
                chopstickArray.getLast(id).setAvailable(false);// �������ֿ��Ӳ�����
                String text = eatingTextArea.getText();
                eatingTextArea.setText(text + this + " �ڳԷ�\n");// ��ʾ��ѧ���ڳԷ�
                try {
                    Thread.sleep(100);// �Է�ʱ�����ó�0.1��
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {// �����ѧ�����ֱߵĿ��Ӳ����ã�������Ӧ���ı�������ʾ�ȴ���Ϣ
                String text = waitingTextArea.getText();
                waitingTextArea.setText(text + this + " �ڵȴ� " + chopstickArray.getLast(id) + "\n");
                try {
                    wait(new Random().nextInt(100));// �ȴ�С��0.1��ʱ���������Ƿ����
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {// �����ѧ�����ֱߵĿ��Ӳ����ã�������Ӧ���ı�������ʾ�ȴ���Ϣ
            String text = waitingTextArea.getText();
            waitingTextArea.setText(text + this + " �ڵȴ� " + chopstickArray.get(id) + "\n");
            try {
                wait(new Random().nextInt(100));// �ȴ�С��0.1��ʱ���������Ƿ����
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    state = true;// ����state��ֵΪtrue��ʾ��ѧ�ҵ�״̬�ǳԷ�
}
    
    @Override
    public void run() {
        
        for (int i = 0; i < 10; i++) {
            thinking();
            eating();
        }
    }
    
    @Override
    public String toString() {
        return " ��ѧ�� " + id;
    }
    
}
