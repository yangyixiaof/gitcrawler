package com.mingrisoft.io;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class FileUtilsTest {
    public static void main(String[] args) throws IOException {
        File srcDir = new File("D:\\���տƼ�");
        File destDir = new File("E:\\���տƼ�");
        List<String> list = new ArrayList<String>();
        System.out.println("����֮ǰ�ļ����е��ļ���");
        getFilePath(list, destDir);
        for (String string : list) {
            System.out.println(string);// �������ǰ�ļ����������ļ�
        }
        System.out.println();
        System.out.println("����֮���ļ����е��ļ���");
        FileUtils.copyDirectory(srcDir, destDir);
        getFilePath(list, destDir);
        for (String string : list) {
            System.out.println(string); // ������ƺ��ļ����������ļ�
        }
    }
    
    // ���rootFile�ļ����������ļ��ľ���·�������䱣����list��
    private static List<String> getFilePath(List<String> list, File rootFile) {
        File[] files = rootFile.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                getFilePath(list, file);
            } else {
                list.add(file.getAbsolutePath().replace("\\", File.separator));
            }
        }
        return list;
    }
}
