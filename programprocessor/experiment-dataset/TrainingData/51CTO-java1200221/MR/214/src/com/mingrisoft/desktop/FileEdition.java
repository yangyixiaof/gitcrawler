package com.mingrisoft.desktop;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class FileEdition {
    public static void main(String[] a) {
        if (Desktop.isDesktopSupported()) {// ����Desktop���ڵ�ǰƽ̨�Ƿ����
            Desktop desktop = Desktop.getDesktop();// ���Desktop���ʵ��
            try {
                desktop.edit(new File("d:\\������.txt"));// �༭�����ļ�
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
