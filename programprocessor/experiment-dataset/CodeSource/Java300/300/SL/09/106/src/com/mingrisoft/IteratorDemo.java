package com.mingrisoft;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorDemo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();	// �����б�
        for (int i = 0; i < 10; i++) {					// ���б�������10��Ԫ��
            list.add(i);
        }
        System.out.println("�б��е�ȫ��Ԫ�أ�");
        for(Iterator<Integer> it = list.iterator();it.hasNext();) {
            System.out.print(it.next()+" ");
        }
    }
}
