package com.mingrisoft.lang;

import org.apache.commons.lang.RandomStringUtils;

public class RandomStringUtilsTest {
    public static void main(String[] args) {
        System.out.println("���ɳ���Ϊ5������ĸ��ɵ��ַ���");
        String randomString = RandomStringUtils.randomAlphabetic(5); // �������ַ���
        System.out.println(randomString);
        System.out.println("���ɳ���Ϊ5������ĸ��������ɵ��ַ���");
        randomString = RandomStringUtils.randomAlphanumeric(5); // �������ַ���
        System.out.println(randomString);
        System.out.println("���ɳ���Ϊ5����ASCII������32~126���ַ���ɵ��ַ���");
        randomString = RandomStringUtils.randomAscii(5); // �������ַ���
        System.out.println(randomString);
        System.out.println("���ɳ���Ϊ5����������ɵ��ַ���");
        randomString = RandomStringUtils.randomNumeric(5); // �������ַ���
        System.out.println(randomString);
    }
}
