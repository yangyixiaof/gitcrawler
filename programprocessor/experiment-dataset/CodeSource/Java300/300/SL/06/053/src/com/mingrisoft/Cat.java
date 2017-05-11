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
    public String toString() {// ��дtoString()����
        StringBuilder sb = new StringBuilder();
        sb.append("���֣�" + name + "\n");
        sb.append("���䣺" + age + "\n");
        sb.append("������" + weight + "\n");
        sb.append("��ɫ��" + color + "\n");
        return sb.toString();
    }
}
