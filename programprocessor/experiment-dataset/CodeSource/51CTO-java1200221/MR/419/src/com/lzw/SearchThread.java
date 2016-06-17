package com.lzw;

import java.io.File;
import java.io.FileFilter;

import javax.swing.JProgressBar;
import javax.swing.table.DefaultTableModel;

public class SearchThread extends Thread {
    class TempFileFilter implements FileFilter {
        @Override
        public boolean accept(File pathname) {
            if (pathname.getName().endsWith(".tmp") || pathname.isDirectory())
                return true;
            return false;
        }
    }
    
    private File driver;
    private DefaultTableModel tableModel;
    private boolean searching = true;
    
    public boolean isSearching() {
        return searching;
    }
    
    public void setSearching(boolean searching) {
        this.searching = searching;
    }
    
    private TempFileFilter tempFileFilter = new TempFileFilter();
    private JProgressBar progressBar;
    
    public SearchThread(File driver, DefaultTableModel tableModel,
            JProgressBar progressBar) {
        this.driver = driver;
        this.tableModel = tableModel;
        progressBar.setStringPainted(true);
        this.progressBar = progressBar;
    }
    
    /*
     * (non-Javadoc)
     * @see java.lang.Thread#run()
     */
    public void run() {
        if (driver != null)
            listTempFiles(driver);
    }
    
    /**
     * �ݹ���������ļ��еķ���
     * 
     * @param driver
     */
    private void listTempFiles(File driver) {
        // ��ȡָ�����̻��ļ��е����б�
        File[] files = driver.listFiles(tempFileFilter);
        if (files == null)
            return;
        progressBar.setIndeterminate(true);// ���ý������Բ�ȷ����ʽ����
        for (File file : files) {// �����ļ�����
            progressBar.setString(file.toString());// ��������ʾ�����ļ���
            if (file.isFile() && searching) {// �����ļ�
                tableModel.addRow(new Object[] { file.getName(), file,
                        file.length(), "δ����" });// ����ļ���Ϣ�����ؼ�
            } else if (file.isDirectory() && searching) {// �����ļ���
                listTempFiles(file);// �ݹ鷽�������ļ���
            }
        }
        progressBar.setIndeterminate(false);// ֹͣ������
        progressBar.setString("�������");// ��ʾ�������
    }
}
