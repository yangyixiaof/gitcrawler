package com.mingrisoft;

import java.io.File;
import java.lang.reflect.Constructor;

public class NewClassTest {
    public static void main(String[] args) {
        try {//���File���Constructor����
            Constructor<File> constructor = 
                                              File.class.getDeclaredConstructor(String.class);
            System.out.println("ʹ�÷��䴴��File����");
            File file = constructor.newInstance("d://���տƼ�.txt");
            System.out.println("ʹ��File������D�̴����ļ������տƼ�.txt");
            file.createNewFile();//�����µ��ļ�
            System.out.println("�ļ��Ƿ񴴽��ɹ���" + file.exists());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
