package com.cdd.substring;
public class LocalTableModel extends javax.swing.table.DefaultTableModel {
	Class[] types = new Class[] { java.lang.Object.class,
			java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,java.lang.String.class};
	boolean[] canEdit = new boolean[] { false, false, false,false,false };
	public LocalTableModel() {
		super(new Object[][] {}, new String[] { "���", "����", "���֤����","����","ѧ��","����" });
	}
	public Class getColumnClass(int columnIndex) {
		return types[columnIndex];
	}
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return canEdit[columnIndex];
	}
}