package com.zzk;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ReportToExcelFrame extends JFrame {
    
    private JTable table;
    private String[] title = { "���", "����", "�Ա�", "����", "��ַ", "�ʱ�", "�绰" };
    
    /**
     * @return JTable���ı�������
     */
    public Vector getTitleVector() {
        Vector titleVec = new Vector();
        for (int i = 0; i < title.length; i++) {
            titleVec.add(title[i]);
        }
        return titleVec;
    }
    
    /**
     * @return JTable������������
     */
    public Vector getDataVectors() {
        ResultSet rs = QueryResultSet.gainRecord();
        Vector dataVector = new Vector();
        try {
            while (rs.next()) {
                Vector rowVector = new Vector();
                for (int i = 0; i < title.length; i++) {
                    if (i == 0 || i == 3) {
                        rowVector.add(rs.getInt(i + 1));
                    } else {
                        rowVector.add(rs.getString(i + 1));
                    }
                }
                dataVector.add(rowVector);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataVector;
    }
    
    /**
     * Launch the application
     * 
     * @param args
     */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ReportToExcelFrame frame = new ReportToExcelFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * Create the frame
     */
    public ReportToExcelFrame() {
        super();
        setTitle("��������Excel���");
        setBounds(100, 100, 420, 281);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    writeExcel("c:/report.xls");// ���÷�������������Excel
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        button.setText("������Excel");
        panel.add(button);
        
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_1.setText("�˳�ϵͳ");
        panel.add(button_1);
        
        final JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        
        table = new JTable(getDataVectors(), getTitleVector());
        scrollPane.setViewportView(table);
        // 
    }
    
    @SuppressWarnings("deprecation")
    public void writeExcel(String filename) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();// ��������������
        HSSFSheet sheet = workbook.createSheet("ReportToExcel");// ��������Ϊ��ReportToExcel���Ĺ�����
        HSSFRow row = sheet.createRow(0);// �ڹ������д����У���������Ϊ0
        HSSFCell cell = null;// ������Ԫ��
        // �����ı�����Excel�еı���
        for (int i = 0; i < title.length; i++) {
            cell = row.createCell((short) i);// ������Ԫ��
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);// ���õ�Ԫ������
            cell.setCellValue(title[i]);// ���õ�Ԫ�������
        }
        // �����ı�����Excel�е�����
        ResultSet rs = QueryResultSet.gainRecord();// ������ݿ��Ľ��������
        int rowIndex = 1;// ��Ԫ�����ݵ�������ֵ
        try {
            while (rs.next()) {// ���������
                row = sheet.createRow(rowIndex);
                for (int i = 0; i < title.length; i++) {
                    String cellContent = "";// �����ŵ�Ԫ�����ݵı���
                    if (i == 0 || i == 3) {
                        cellContent = String.valueOf(rs.getInt(i + 1));// �Ӽ�¼����õ�Ԫ������
                    } else {
                        cellContent = rs.getString(i + 1);// �Ӽ�¼����õ�Ԫ������
                    }
                    cell = row.createCell((short) i);// ������Ԫ�����
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);// ���õ�Ԫ������
                    cell.setCellValue(cellContent);// ָ����Ԫ���ֵ
                }
                rowIndex++;// ������Ԫ�����ݵ�������ֵ
            }
            FileOutputStream fout = new FileOutputStream(filename);// ��������Excel�������
            workbook.write(fout);// ��������д�������
            fout.flush();// ˢ���������ǿ��д�����л��������ֽ�
            fout.close();// �ر����������
            JOptionPane.showMessageDialog(null, "�Ѿ��ɹ��ؽ�����\n��������C:/report.xls���С�");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
