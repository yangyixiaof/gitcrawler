package com.mingrisoft.lang;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Arrays;

import org.apache.commons.lang.SerializationUtils;

public class SerializationUtilsTest {
    public static void main(String[] args) {
        Student student = new Student();// ����student����
        student.setId(10);// ��ʼ��id����
        student.setName("���տƼ�");// ��ʼ��name����
        System.out.println("��student�������л���byte����");
        byte[] studentByte = SerializationUtils.serialize(student);// ������ת����byte����
        System.out.println("������л����飺");
        System.out.println(Arrays.toString(studentByte));// ���byte����
        System.out.println("��student�������л��������ļ�");
        FileOutputStream out = null;// �����ļ����������
        try {
            out = new FileOutputStream(new File("d:\\student.txt").getAbsoluteFile());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        SerializationUtils.serialize(student, out);// ������д�뵽student.txt�ļ�
        System.out.println("�ļ����ɳɹ���");
        System.out.println("�ӱ����ļ������л�student����");
        FileInputStream in = null;// �����ļ�����������
        try {
            in = new FileInputStream(new File("d:\\student.txt").getAbsoluteFile());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Student newStudent = (Student) SerializationUtils.deserialize(in);// �������
        System.out.println("�鿴student���������");
        System.out.println(newStudent);
    }
}
