package com.mingrisoft;

public class Book {
    private String title; // ��������
    private String author; // ��������
    private double price; // ����۸�

    public Book(String title, String author, double price) {// ���ù��췽����ʼ����
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public String getTitle() { // �������
        return title;
    }

    public String getAuthor() { // �������
        return author;
    }

    public double getPrice() { // ��ü۸�
        return price;
    }
}
