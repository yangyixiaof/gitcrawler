package com.mingrisoft;

public class Emperor {
    private static Emperor emperor = null;// ����һ��Emperor�������

    private Emperor() {// �����췽��˽��
    }

    public static Emperor getInstance() {// ʵ��������
        if (emperor == null) {
            emperor = new Emperor();
        }
        return emperor;
    }

    public void getName() {// ʹ����ͨ��������ʵ۵�����
        System.out.println("���ǻʵۣ����տƼ�");
    }

}
