package com.mingrisoft;
import java.util.Scanner;


public class Demo {
	/**
	 * @param args
	 */
	public static void main(String[] args) {	//������
        Scanner scan = new Scanner(System.in);
        System.out.println("������һ����ݣ�");	//�����̨���һ����ʾ��Ϣ
        long year;
		try {
			year = scan.nextLong();
			if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) { // ������
				System.out.print(year + "�����꣡");
			} else { 								// ��������
				System.out.print(year + "�������꣡");
			}
		} catch (Exception e) {
			System.out.println("������Ĳ�����Ч����ݣ�");
		}
	}
}
