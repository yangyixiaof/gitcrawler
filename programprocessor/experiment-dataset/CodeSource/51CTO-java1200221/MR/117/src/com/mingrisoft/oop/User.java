package com.mingrisoft.oop;

public class User {
    public static void main(String[] args) {
        System.out.print("�û�ѡ����GIF��ʽ��");
        ImageSaver saver = TypeChooser.getSaver("GIF");
        saver.save();
        System.out.print("�û�ѡ����JPEG��ʽ��");
        saver = TypeChooser.getSaver("JPEG");
        saver.save();
        System.out.print("�û�ѡ����PNG��ʽ��");
        saver = TypeChooser.getSaver("PNG");
        saver.save();
    }
}
