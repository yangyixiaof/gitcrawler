package com.mingrisoft.oop;

public class BookTest {
    public static void main(String[] args) {
        Book book = new Book("��Java�����ŵ���ͨ����2�棩��", "���տƼ�", 59.8);
        System.out.println("������" + book.getTitle());
        System.out.println("���ߣ�" + book.getAuthor());
        System.out.println("�۸�" + book.getPrice() + "Ԫ");
    }
}
