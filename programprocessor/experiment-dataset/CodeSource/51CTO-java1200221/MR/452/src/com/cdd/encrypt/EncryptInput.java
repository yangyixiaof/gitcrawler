package com.cdd.encrypt;

import java.io.*;
import java.nio.channels.FileLock;

class EncryptInput {
    
    public static void fileLock(String file) {
        FileOutputStream fous = null; // ����FileOutputStream����
        FileLock lock = null; // ����FileLock����
        try {
            fous = new FileOutputStream(file); // ʵ����FileOutputStream����
            lock = fous.getChannel().tryLock(); // ��ȡ�ļ�����
            Thread.sleep(60 * 1000); // �߳�����1����
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        String file = "C://count.txt"; // �����ļ�����
        fileLock(file); // �����ļ���������
    }
}
