package com.zzk;

/**
 * @author ������
 */
public class Farm {
    public int state = 0; // �����״̬ 0��ʾδ���֣�1��ʾ���֣�2��ʾ������3��ʾ������4��ʾ���
    
    /**
     * ����
     * 
     * @param picture
     */
    public String seed(Crop crop, String picture) {
        String returnValue = ""; // ����ֵ����
        if (state == 0) { // �ж������״̬�Ƿ�Ϊδ����
            crop.setIcon(picture); // ��������
            state = 1; // ����״̬����Ϊ����
        } else {
            returnValue = getMessage() + "�����ܲ��֣�"; // ������ʾ��Ϣ
        }
        return returnValue;
    }
    
    /**
     * ����
     * 
     * @param crop
     * @param picture
     */
    public String grow(Crop crop, String picture) {
        String returnValue = ""; // ����ֵ����
        if (state == 1) { // �ж������״̬�Ƿ�Ϊ����
            crop.setIcon(picture); // ��������Ϊ����״̬
            state = 2; // ����״̬����Ϊ����
        } else {
            returnValue = getMessage() + "������������"; // ������ʾ��Ϣ
        }
        return returnValue;
    }
    
    /**
     * ����
     * 
     * @param crop
     * @param picture
     */
    public String bloom(Crop crop, String picture) {
        String returnValue = ""; // ����ֵ����
        if (state == 2) { // �ж������״̬�Ƿ�Ϊ����
            crop.setIcon(picture); // ��������Ϊ����״̬
            state = 3; // ����״̬����Ϊ����
        } else {
            returnValue = getMessage() + "�����ܿ�����"; // ������ʾ��Ϣ
        }
        return returnValue;
    }
    
    /**
     * ���
     * 
     * @param crop
     * @param picture
     */
    public String fruit(Crop crop, String picture) {
        String returnValue = ""; // ����ֵ����
        if (state == 3) { // �ж������״̬�Ƿ�Ϊ����
            crop.setIcon(picture); // ��������Ϊ���״̬
            state = 4; // ����״̬����Ϊ���
        } else {
            returnValue = getMessage() + "�����ܽ����"; // ������ʾ��Ϣ
        }
        return returnValue;
    }
    
    /**
     * �ջ�
     * 
     * @param crop
     * @param picture
     */
    public String harvest(Crop crop, String picture) {
        String returnValue = ""; // ����ֵ����
        if (state == 4) { // �ж������״̬�Ƿ�Ϊ���
            crop.setIcon(picture); // ��������Ϊδ����״̬
            state = 0; // ����״̬����Ϊδ����
        } else {
            returnValue = getMessage() + "�������ջ�"; // ������ʾ��Ϣ
        }
        return returnValue;
    }
    
    /**
     * ���������״̬���ԣ�ȷ����Ӧ����ʾ��Ϣ
     * 
     * @return
     */
    public String getMessage() {
        String message = "";
        switch (state) {
            case 0:
                message = "���ﻹû�в���";
                break;
            case 1:
                message = "����ող���";
                break;
            case 2:
                message = "������������";
                break;
            case 3:
                message = "���������ڿ�����";
                break;
            case 4:
                message = "�����Ѿ����";
                break;
        }
        return message;
    }
}
