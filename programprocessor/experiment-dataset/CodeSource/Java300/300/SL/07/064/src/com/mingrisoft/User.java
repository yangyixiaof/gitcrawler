package com.mingrisoft;
public class User {
    public static void main(String[] args) {
        System.out.print("�û�ѡ����GIF��ʽ��");
        ImageSaver saver = TypeChooser.getSaver("GIF");//��ñ���ͼƬΪGIF���͵Ķ���
        saver.save();
        System.out.print("�û�ѡ����JPEG��ʽ��");//��ñ���ͼƬΪJPEG���͵Ķ���
        saver = TypeChooser.getSaver("JPEG");
        saver.save();
        System.out.print("�û�ѡ����PNG��ʽ��");//��ñ���ͼƬΪPNG���͵Ķ���
        saver = TypeChooser.getSaver("PNG");
        saver.save();
    }
}
