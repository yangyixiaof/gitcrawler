package com.mingrisoft;


import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ListIteratorDemo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();// �����б�
        for (int i = 0; i < 10; i++) {// ���б�������10��Ԫ��
            list.add(i);
        }
        System.out.println("�б��е�ȫ��Ԫ�أ�" + list);
        System.out.println("��������б��е�Ԫ�أ�");
        ListIterator<Integer> li = list.listIterator();// ���ListIterator����
        for (li = list.listIterator(); li.hasNext();) {// ���α궨λ���б��β
            li.next();
        }
        for (; li.hasPrevious();) {// ��������б��е�Ԫ��
            System.out.print(li.previous() + " ");
        }
    }
}
