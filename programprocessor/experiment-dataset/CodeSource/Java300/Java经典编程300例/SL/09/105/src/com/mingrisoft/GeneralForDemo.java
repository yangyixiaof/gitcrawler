package com.mingrisoft;

import java.util.ArrayList;
import java.util.List;

public class GeneralForDemo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();						// �����б�
        for (int i = 0; i < 10; i++) {										// ���б�������10��Ԫ��
            list.add(i);
        }
        System.out.println("�б��е�Ԫ�أ�" + list);						// ����б���ȫ����Ԫ��
        System.out.println("�б��е��������Ԫ�أ�");
        for (int i = 1; i < list.size(); i += 2) {								// ����б������Ϊ������Ԫ��
            System.out.print(list.get(i)+"  ");
        }
    }
}

