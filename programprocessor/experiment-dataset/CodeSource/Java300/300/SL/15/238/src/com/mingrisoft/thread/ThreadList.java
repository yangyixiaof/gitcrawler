package com.mingrisoft.thread;

import java.util.ArrayList;
import java.util.List;

public class ThreadList {
    private static ThreadGroup getRootThreadGroups() {//��ø��߳���
        ThreadGroup rootGroup = Thread.currentThread().getThreadGroup();//��õ�ǰ�߳���
        while (true) {
            if (rootGroup.getParent() != null) {//���getParent()����ֵ�ǿ����Ǹ��߳���
                rootGroup = rootGroup.getParent();//��ø��߳���
            } else {
                break;//���������߳������˳�ѭ��
            }
        }
        return rootGroup;//���ظ��߳���
    }
    public static List<String> getThreads(ThreadGroup group) {//��ø����߳����������߳���
        List<String> threadList = new ArrayList<String>();      //���������߳������б�
        Thread[] threads = new Thread[group.activeCount()]; //���ݻ�߳��������߳�����
        int count = group.enumerate(threads, false);//�����̵߳��߳�����
        for (int i = 0; i < count; i++) {//�����߳����齫�߳������������鱣�浽�б���
            threadList.add(group.getName() + "�߳��飺" + threads[i].getName());
        }
        return threadList;//�����б�
    }
    public static List<String> getThreadGroups(ThreadGroup group) {//����߳����������߳�
        List<String> threadList = getThreads(group);    //��ø����߳������߳���
        ThreadGroup[] groups = new ThreadGroup[group.activeGroupCount()];//�����߳�������
        int count = group.enumerate(groups, false); //�������߳��鵽�߳�������
        for (int i = 0; i < count; i++) {//�����������߳���
            threadList.addAll(getThreads(groups[i]));// ����getThreads()��������߳����б�
        }
        return threadList;//���������߳���
    }
    public static void main(String[] args) {
        for (String string : getThreadGroups(getRootThreadGroups())) {
            System.out.println(string);//��������б��е��ַ���
        }
    }
}
