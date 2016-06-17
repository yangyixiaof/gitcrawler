package com.zzk;

import java.awt.Cursor;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JTextField;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class TargetTextField extends JTextField implements MouseListener,
MouseMotionListener, ActionListener {
    private int mousePressX = 0;// ��갴�µ��x����
    private int mousePressY = 0;// ��갴�µ��y����
    final Border border = getBorder();// ����ı���ı߿�
    public TargetTextField() {
        super();
        setBorder(border);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        setBorder(border);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mousePressX = e.getX();// ��갴�µ��x����
        mousePressY = e.getY();// ��갴�µ��y����
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Cursor cursorD = new Cursor(Cursor.DEFAULT_CURSOR);// Ĭ�ϵĹ������
        setCursor(cursorD);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        JTextField textField = (JTextField) e.getSource();
        int textW = textField.getWidth();
        int textH = textField.getHeight();
        Rectangle rect = textField.getBounds();
        if (getCursor().getType()==Cursor.W_RESIZE_CURSOR){// ����������߿�Ĵ�С��λ��
            textField.setBounds((int)rect.getX() + e.getX(),(int)rect.getY(),textW - e.getX(),textH);
            textField.requestFocus();
        } else if (getCursor().getType()==Cursor.E_RESIZE_CURSOR){// ���������ұ߿�Ĵ�С��λ��
            textField.setBounds((int)rect.getX(),(int)rect.getY(),e.getX(),textH);
            textField.requestFocus();
        } else if (getCursor().getType()==Cursor.N_RESIZE_CURSOR){// ���������ϱ߿�Ĵ�С��λ��
            textField.setBounds((int)rect.getX(),(int)rect.getY()+e.getY(),textW,textH - e.getY());
            textField.requestFocus();
        } else if (getCursor().getType()==Cursor.S_RESIZE_CURSOR){// ���������±߿�Ĵ�С��λ��
            textField.setBounds((int)rect.getX(),(int)rect.getY(),textW,e.getY());
            textField.requestFocus();
        } else {// �ƶ��ı����λ��
            textField.setBounds((int)rect.getX() + e.getX()-mousePressX,(int)rect.getY()+e.getY()-mousePressY,textW,textH);
            textField.requestFocus();
        }
        

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
        Cursor cursorW = new Cursor(Cursor.W_RESIZE_CURSOR);// ����������߿��С�Ĺ������
        Cursor cursorN = new Cursor(Cursor.N_RESIZE_CURSOR);// ���������ϱ߿��С�Ĺ������
        Cursor cursorE = new Cursor(Cursor.E_RESIZE_CURSOR);// ���������ұ߿��С�Ĺ������
        Cursor cursorS = new Cursor(Cursor.S_RESIZE_CURSOR);// ���������±߿��С�Ĺ������
        Cursor cursorD = new Cursor(Cursor.DEFAULT_CURSOR);// Ĭ�Ϲ������
        JTextField textField = (JTextField) e.getSource();
        int textW = textField.getWidth();
        int textH = textField.getHeight();
        if (e.getX() == 0){
            setCursor(cursorW);// ������߿���
        }else if (e.getY() == 0){
            setCursor(cursorN);// �����ϱ߿���
        }else if (e.getX() == textW-3){
            setCursor(cursorE);// �����ұ߿���
        }else if (e.getY() == textH-3){
            setCursor(cursorS);// �����±߿���
        }else{
            setCursor(cursorD);// ����Ĭ�Ϲ��
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setBorder(null);
    }

}
