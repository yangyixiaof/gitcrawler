package com.mingrisoft;

public class ProductPrice {
    public static void main(String[] args) {
        float money = 1170; // ���
        String rebate = ""; // �ۿ�
        if (money > 200) {
            int grade = (int) money / 200; // �ȼ�
            switch (grade) { // ���ݵȼ������ۿ۱���
                case 1:
                    rebate = "������";
                    break;
                case 2:
                    rebate = "����";
                    break;
                case 3:
                    rebate = "������";
                    break;
                case 4:
                    rebate = "������";
                    break;
                case 5:
                    rebate = "����";
                    break;
                case 6:
                    rebate = "�߰���";
                    break;
                case 7:
                    rebate = "������";
                    break;
                case 8:
                    rebate = "������";
                    break;
                case 9:
                    rebate = "����";
                    break;
                case 10:
                    rebate = "������";
                    break;
                default:
                    rebate = "����";
            }
        }
        System.out.println("�����ۼ����ѽ��Ϊ��" + money);// ������ѽ��
        System.out.println("��������" + rebate + "�Żݣ�"); // ����ۿ۱���
    }
}
