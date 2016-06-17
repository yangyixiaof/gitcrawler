package com.mingrisoft;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JOptionPane;

public class DownMultiThread implements Runnable{
    private String sUrl = "";// ������Դ��ַ
    private File desFile;// ��Ҫд���Ŀ���ļ�����
    private long startPos;// д��Ŀ�ʼλ��
    private long endPos;// д��Ľ���λ��
    /**
     * @param sUrl ������Դ��ַ
     * @param file ��Ҫд���Ŀ���ļ�����
     * @param startPos д��Ŀ�ʼλ��
     * @param endPos д��Ľ���λ��
     */
    public DownMultiThread(String sUrl,File desFile,long startPos,long endPos) {
        this.sUrl = sUrl;
        this.desFile = desFile;
        this.startPos = startPos;
        this.endPos = endPos;
    }
    @Override
    public void run() {
        try {
            URL url = new URL(sUrl);// ����������Դ��URL����
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();// �����Ӷ���
            conn.setRequestProperty("User-Agent", "NetFox");// ������������
            String rangeProperty = "bytes="+startPos+"-";// ���巶Χ����
            if (endPos > 0){
                rangeProperty = "bytes="+startPos+"-" + endPos;// ������Χ���Ե�ֵ
            }
            conn.setRequestProperty("RANGE", rangeProperty);// ָ����Χ����
            RandomAccessFile out = new RandomAccessFile(desFile, "rw");// �����ɶ�д��������
            out.seek(startPos);// ָ����д�Ŀ�ʼ���
            InputStream in = conn.getInputStream();// ���������Դ������������
            BufferedInputStream bin = new BufferedInputStream(in);// �������뻺��������
            byte[] buff = new byte[2048];// �����ֽ�����
            int len = -1;// ������Ŷ�ȡ�ֽ����ı���
            len=bin.read(buff);// ��ȡ�����ݲ���ӵ��ֽ�����
            while (len!=-1){
                out.write(buff,0,len);// д������ļ�
                len=bin.read(buff);// ��ȡ�����ݲ���ӵ��ֽ�����
            }
            out.close();// �ر���
            bin.close();// �ر���
            conn.disconnect();// �Ͽ�����
        }catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}
