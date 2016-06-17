package com.cdd.util;
import java.io.*;
public class ReadFile {
    
public static void main(String args[]){
    int bytes[]={1,2,3,4,5};    //����д���ļ�������
    try {
        //����RandomAccessFile��Ķ���
        File file = new File("Example9.txt");
        if(!file.exists()){             //�жϸ��ļ��Ƿ����
            file.createNewFile();       //�½��ļ�
        }
        RandomAccessFile raf=new RandomAccessFile(file,"rw");   //����RandomAccessFile����
        for(int i=0;i<bytes.length;i++){        //ѭ����������
            raf.writeInt(bytes[i]);             //������д���ļ�
        }
        System.out.println("���������Ϣ");
        for(int i=bytes.length-1;i>=0;i--){     //�����������
            raf.seek(i*4);                      //int������ռ4���ֽ�
            System.out.println(+raf.readInt());
        }
        raf.close();                    //�ر���
    } catch (Exception e) {
        e.printStackTrace();
    }
}

}
