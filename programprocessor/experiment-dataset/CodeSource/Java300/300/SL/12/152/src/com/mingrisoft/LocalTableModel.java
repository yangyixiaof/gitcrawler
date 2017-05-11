package com.mingrisoft;

public class LocalTableModel extends javax.swing.table.DefaultTableModel {
    /**
     * 
     */
    private static final long serialVersionUID = 8746141681804141757L;
    Class<?>[] types = new Class[] { java.lang.Object.class, java.lang.String.class };
    boolean[] canEdit = new boolean[] { false, false };

    public LocalTableModel() {
        super(new Object[][] {}, new String[] { "�ļ�", "��С" });
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return types[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit[columnIndex];
    }
}