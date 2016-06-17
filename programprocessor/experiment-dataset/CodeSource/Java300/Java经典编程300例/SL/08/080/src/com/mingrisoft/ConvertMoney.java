package com.mingrisoft;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * ���ת��
 * 
 * @author YongQiang Lee
 */
public class ConvertMoney {
    // ��д����
    private final static String[] STR_NUMBER = { "��", "Ҽ", "��", "��", "��", "��",
            "½", "��", "��", "��" };
    private final static String[] STR_UNIT = { "", "ʰ", "��", "Ǫ", "��", "ʰ",
            "��", "Ǫ", "��", "ʰ", "��", "Ǫ" };// ������λ
    private final static String[] STR_UNIT2 = { "��", "��", "��" };// С����λ
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);// ����ɨ����
        System.out.println("������һ�����");
        // ��ȡ���ת������ַ���
        String convert = convert(scan.nextDouble());
        System.out.println(convert);// ���ת�����
    }
    
    /**
     * ��ȡ��������
     * 
     * @param num
     *            ���
     * @return �����������
     */
    public static String getInteger(String num) {
        if (num.indexOf(".") != -1) { // �ж��Ƿ����С����
            num = num.substring(0, num.indexOf("."));
        }
        num = new StringBuffer(num).reverse().toString(); // ��ת�ַ���
        StringBuffer temp = new StringBuffer(); // ����һ��StringBuffer����
        for (int i = 0; i < num.length(); i++) {// ���뵥λ
            temp.append(STR_UNIT[i]);
            temp.append(STR_NUMBER[num.charAt(i) - 48]);
        }
        num = temp.reverse().toString();// ��ת�ַ���
        num = numReplace(num, "��ʰ", "��"); // �滻�ַ������ַ�
        num = numReplace(num, "���", "��"); // �滻�ַ������ַ�
        num = numReplace(num, "��Ǫ", "��"); // �滻�ַ������ַ�
        num = numReplace(num, "����", "��"); // �滻�ַ������ַ�
        num = numReplace(num, "����", "��"); // �滻�ַ������ַ�
        num = numReplace(num, "����", "��"); // �滻�ַ������ַ�
        num = numReplace(num, "����", "��"); // �滻�ַ������ַ�
        // ����ַ��������β�����ȥ
        if (num.lastIndexOf("��") == num.length() - 1) {
            num = num.substring(0, num.length() - 1);
        }
        return num;
    }
    
    /**
     * ��ȡС������
     * 
     * @param num
     *            ���
     * @return ����С������
     */
    public static String getDecimal(String num) {
        // �ж��Ƿ����С����
        if (num.indexOf(".") == -1) {
            return "";
        }
        num = num.substring(num.indexOf(".") + 1);
        // ��ת�ַ���
        num = new StringBuffer(num).reverse().toString();
        // ����һ��StringBuffer����
        StringBuffer temp = new StringBuffer();
        // ���뵥λ
        for (int i = 0; i < num.length(); i++) {
            temp.append(STR_UNIT2[i]);
            temp.append(STR_NUMBER[num.charAt(i) - 48]);
        }
        num = temp.reverse().toString(); // �滻�ַ������ַ�
        num = numReplace(num, "���", "��"); // �滻�ַ������ַ�
        num = numReplace(num, "���", "��"); // �滻�ַ������ַ�
        num = numReplace(num, "����", "��"); // �滻�ַ������ַ�
        num = numReplace(num, "����", "��"); // �滻�ַ������ַ�
        // ����ַ��������β�����ȥ
        if (num.lastIndexOf("��") == num.length() - 1) {
            num = num.substring(0, num.length() - 1);
        }
        return num;
    }
    
    /**
     * �滻�ַ���������
     * 
     * @param num
     *            �ַ���
     * @param oldStr
     *            ���滻����
     * @param newStr
     *            ������
     * @return �滻����ַ���
     */
    public static String numReplace(String num, String oldStr, String newStr) {
        while (true) {
            // �ж��ַ������Ƿ����ָ���ַ�
            if (num.indexOf(oldStr) == -1) {
                break;
            }
            // �滻�ַ���
            num = num.replaceAll(oldStr, newStr);
        }
        // �����滻����ַ���
        return num;
    }
    
    /**
     * ���ת��
     * 
     * @param d
     *            ���
     * @return ת���ɴ�д��ȫ��
     */
    public static String convert(double d) {
        // ʵ����DecimalFormat����
        DecimalFormat df = new DecimalFormat("#0.###");
        // ��ʽ��double����
        String strNum = df.format(d);
        // �ж��Ƿ����С����
        if (strNum.indexOf(".") != -1) {
            String num = strNum.substring(0, strNum.indexOf("."));
            // �������ִ���12����ת��
            if (num.length() > 12) {
                System.out.println("����̫�󣬲������ת����");
                return "";
            }
        }
        String point = "";// С����
        if (strNum.indexOf(".") != -1) {
            point = "Ԫ";
        } else {
            point = "Ԫ��";
        }
        // ת�����
        String result = getInteger(strNum) + point + getDecimal(strNum);
        if (result.startsWith("Ԫ")) { // �ж����ַ����Ƿ���"Ԫ"��β
            result = result.substring(1, result.length()); // ��ȡ�ַ���
        }
        return result; // �����µ��ַ���
    }
}
