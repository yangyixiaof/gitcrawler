package com.cdd.openWord;

import java.io.*;
import java.text.*;
import java.util.*;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class FileHeald {
    
    private Dispatch doc;
    private ActiveXComponent word; // word���г������
    private Dispatch documents; // ����word�ĵ�����
    
    public FileHeald() {
        if (word == null) {
            word = new ActiveXComponent("Word.Application"); // ����word
            word.setProperty("Visible", new Variant(true)); // ����wordΪ����״̬
        }
        if (documents == null)
            documents = word.getProperty("Documents").toDispatch();// ��ȡ����ֵ
    }
    
    // ��ȡ�����ļ�������
    public List getFileList(String strPath) {
        LinkedList filelist = new LinkedList();
        File dir = new File(strPath);
        File[] file = dir.listFiles();
        if ((file != null) && file.length > 0) {
            for (int i = 0; i < file.length; i++) {
                if (file[i].isDirectory()) {
                    getFileList(file[i].getAbsolutePath());
                } else {
                    filelist.add(file[i]);
                }
            }
        }
        return filelist;
    }
    
    // ��ȡ���������ļ�����
    public List getList(String path) {
        LinkedList<File> list = new LinkedList<File>();
        ArrayList<String> listPath = new ArrayList<String>();
        File dir = new File(path);
        File file[] = dir.listFiles();
        for (int i = 0; i < file.length; i++) {
            if (file[i].isDirectory())
                list.add(file[i]);
            else {
                listPath.add(file[i].getAbsolutePath());
            }
        }
        File tmp;
        while (!list.isEmpty()) {
            tmp = list.removeFirst(); // �Ƴ������ؼ����е�һ��
            if (tmp.isDirectory()) {
                file = tmp.listFiles();
                if (file == null)
                    continue;
                for (int i = 0; i < file.length; i++) {
                    if (file[i].isDirectory())
                        list.add(file[i]);
                    else {
                        listPath.add(file[i].getAbsolutePath());
                    }
                    
                }
            } else {
                
            }
        }
        return listPath;
    }    

    /**
     * ��һ���Ѵ��ڵ��ĵ�
     * 
     * @param docPath
     */
    
public void openDocument(String docPath) {  
    doc = Dispatch.call(documents, "Open", docPath).toDispatch(); // ���ô�word�ĵ�����        
}

}
