package com.mingrisoft.generic;

public class GenericComparisonTest {
    public static void main(String[] args) {
        String[] books = { "Java�����ŵ���ͨ����2�棩", "Java��̱���", "ϸ˵Java", "��ƵѧJava" };
        System.out.println("���տƼ������б�");
        for (String book : books) {
            System.out.println(book);
        }
        String min = GenericComparison.getMin(books);
        System.out.println("����������ĵ�һ���飺");
        System.out.println(min);
    }
}
