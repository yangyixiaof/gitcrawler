package com.mingrisoft.generic;

import java.util.List;

public class GenericQueryTest {
    public static void main(String[] args) {
        String sql = "select * from books;";
        List<Books> list = GenericQuery.query(sql, Books.class);
        System.out.println("���տƼ����飺");
        for (Books books : list) {
            System.out.println(books);
        }
    }
}
