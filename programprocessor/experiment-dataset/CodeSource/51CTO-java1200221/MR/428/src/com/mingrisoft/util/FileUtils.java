package com.mingrisoft.util;

import java.io.File;
import java.util.List;

public class FileUtils {
    // ɾ���ļ����������ļ�
    public static void deleteFiles(File rootFile) {
        if (rootFile.listFiles().length == 0) {// ����û��������ǿ��ļ��о��˳�����
            return;
        } else {
            File[] files = rootFile.listFiles();// ���ǿ��ļ���ת����File����
            for (File file : files) {
                if (file.isFile()) {
                    file.delete();// ɾ��ָ���ļ����µ������ļ�
                } else {
                    if (file.listFiles().length == 0) {
                        file.delete();// ɾ��ָ���ļ����µ����п��ļ���
                    } else {
                        deleteDirectories(file);// ɾ��ָ���ļ����µ����зǿ��ļ���
                    }
                }
            }
        }
    }
    
    // ɾ���ļ��м��ļ����������ļ�
    public static void deleteDirectories(File rootFile) {
        if (rootFile.isFile()) {
            rootFile.delete();// ���������File�������ļ���ֱ��ɾ��
        } else {// �����һ���ļ��оͽ���ת����File����
            File[] files = rootFile.listFiles();
            for (File file : files) {
                deleteDirectories(file);// ������ǿ��ļ�����͵���deleteDictionary()����
            }
            rootFile.delete();// ����ǿ��ļ��о�ֱ��ɾ��
        }
        
    }
    
    // ���ָ��Ŀ¼�µ������ļ���·��
    public static List<String> getFilePath(List<String> list, File rootFile) {
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
