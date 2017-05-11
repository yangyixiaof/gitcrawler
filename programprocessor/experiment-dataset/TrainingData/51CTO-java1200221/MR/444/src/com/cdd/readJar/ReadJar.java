package com.cdd.readJar;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
public class ReadJar {

static List process(String fileName){
    List list = new ArrayList();        //����List���϶���
    try {            
        JarFile jarFile = new JarFile(fileName);    //����JarFile����
        Enumeration en = jarFile.entries();            
        while(en.hasMoreElements()){            //����ö�����Ƿ���������Ԫ��
            FileName file = new FileName();     //����JavaBean����
            JarEntry entry = (JarEntry)en.nextElement();    //��ȡ�����е�Ԫ��
            String name = entry.getName();      //��ȡ�ļ�����
            long size = entry.getSize();         //��ȡ�ļ���С     
            file.setName(name);                 
            file.setSize(size+"");
            list.add(file);                     //��������ӵ�������
        }
    } catch (Exception e) {           
        e.printStackTrace();
    }        
    return list;
}
}
