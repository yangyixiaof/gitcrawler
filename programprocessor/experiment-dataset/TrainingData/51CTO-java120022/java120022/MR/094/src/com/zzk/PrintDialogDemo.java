package com.zzk;

import java.awt.print.*;

/**
 * @author ������
 *
 */
public class PrintDialogDemo {
    public static void main(String[] args) {
        PrinterJob job = PrinterJob.getPrinterJob(); // ��ô�ӡ����
        if (!job.printDialog()) { // �򿪴�ӡ�Ի���
            return; // ������ӡ�Ի����ȡ����ť��رմ�ӡ�Ի�����������ִ��
        }
        job.setJobName("���Դ�ӡ�Ի���"); // ���ô�ӡ���������
        String jobName = job.getJobName(); // ��� ��ӡ���������
        System.out.println("��ӡ����������ǣ�  " + jobName);
    }
}
