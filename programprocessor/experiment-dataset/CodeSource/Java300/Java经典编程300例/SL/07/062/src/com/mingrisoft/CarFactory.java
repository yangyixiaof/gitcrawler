package com.mingrisoft;
public class CarFactory {
    public static Car getCar(String name) {
        if (name.equalsIgnoreCase("BMW")) {//�����ҪBMW�򴴽�BMW����
            return new BMW();
        } else if (name.equalsIgnoreCase("Benz")) {//�����ҪBenz�򴴽�Benz����
            return new Benz();
        } else {//��ʱ����֧����������
            return null;
        }
    }
}
