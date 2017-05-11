package com.mingrisoft;

import java.awt.Color;

public class Cat {
    private String name; // ��ʾè�������
    private int age; // ��ʾè�������
    private double weight; // ��ʾè�������
    private Color color; // ��ʾè�����ɫ

    public Cat(String name, int age, double weight, Color color) {// ��ʼ��è�������
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.color = color;
    }

    @Override
    public boolean equals(Object obj) {// �����������ж�è���Ƿ���ͬ
        if (this == obj) {// �������è����ͬһ����������ͬ
            return true;
        }
        if (obj == null) {// �������è����һ��Ϊnull��ͬ
            return false;
        }
        if (getClass() != obj.getClass()) {// �������è������Ͳ�ͬ��ͬ
            return false;
        }
        Cat cat = (Cat) obj;
        return name.equals(cat.name) && (age == cat.age)
                && (weight == cat.weight) && (color.equals(cat.color));// �Ƚ�è�������
    }

    @Override
    public String toString() {// ��дtoString()����
        StringBuilder sb = new StringBuilder();
        sb.append("���֣�" + name + "\n");
        sb.append("���䣺" + age + "\n");
        sb.append("������" + weight + "\n");
        sb.append("��ɫ��" + color + "\n");
        return sb.toString();
    }

}
