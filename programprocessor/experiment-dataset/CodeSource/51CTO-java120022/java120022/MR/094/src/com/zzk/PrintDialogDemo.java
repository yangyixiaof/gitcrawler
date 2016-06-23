package com.zzk;

import java.awt.print.*;

/**
 * @author 张振坤
 *
 */
public class PrintDialogDemo {
    public static void main(String[] args) {
        PrinterJob job = PrinterJob.getPrinterJob(); // 获得打印对象
        if (!job.printDialog()) { // 打开打印对话框
            return; // 单击打印对话框的取消按钮或关闭打印对话框结束程序的执行
        }
        job.setJobName("测试打印对话框"); // 设置打印任务的名称
        String jobName = job.getJobName(); // 获得 打印任务的名称
        System.out.println("打印任务的名称是：  " + jobName);
    }
}
