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
    private int mousePressX = 0;// 鼠标按下点的x坐标
    private int mousePressY = 0;// 鼠标按下点的y坐标
    final Border border = getBorder();// 获得文本框的边框
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
        mousePressX = e.getX();// 鼠标按下点的x坐标
        mousePressY = e.getY();// 鼠标按下点的y坐标
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Cursor cursorD = new Cursor(Cursor.DEFAULT_CURSOR);// 默认的光标类型
        setCursor(cursorD);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        JTextField textField = (JTextField) e.getSource();
        int textW = textField.getWidth();
        int textH = textField.getHeight();
        Rectangle rect = textField.getBounds();
        if (getCursor().getType()==Cursor.W_RESIZE_CURSOR){// 调整窗口左边框的大小和位置
            textField.setBounds((int)rect.getX() + e.getX(),(int)rect.getY(),textW - e.getX(),textH);
            textField.requestFocus();
        } else if (getCursor().getType()==Cursor.E_RESIZE_CURSOR){// 调整窗口右边框的大小和位置
            textField.setBounds((int)rect.getX(),(int)rect.getY(),e.getX(),textH);
            textField.requestFocus();
        } else if (getCursor().getType()==Cursor.N_RESIZE_CURSOR){// 调整窗口上边框的大小和位置
            textField.setBounds((int)rect.getX(),(int)rect.getY()+e.getY(),textW,textH - e.getY());
            textField.requestFocus();
        } else if (getCursor().getType()==Cursor.S_RESIZE_CURSOR){// 调整窗口下边框的大小和位置
            textField.setBounds((int)rect.getX(),(int)rect.getY(),textW,e.getY());
            textField.requestFocus();
        } else {// 移动文本框的位置
            textField.setBounds((int)rect.getX() + e.getX()-mousePressX,(int)rect.getY()+e.getY()-mousePressY,textW,textH);
            textField.requestFocus();
        }
        

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
        Cursor cursorW = new Cursor(Cursor.W_RESIZE_CURSOR);// 调整窗口左边框大小的光标类型
        Cursor cursorN = new Cursor(Cursor.N_RESIZE_CURSOR);// 调整窗口上边框大小的光标类型
        Cursor cursorE = new Cursor(Cursor.E_RESIZE_CURSOR);// 调整窗口右边框大小的光标类型
        Cursor cursorS = new Cursor(Cursor.S_RESIZE_CURSOR);// 调整窗口下边框大小的光标类型
        Cursor cursorD = new Cursor(Cursor.DEFAULT_CURSOR);// 默认光标类型
        JTextField textField = (JTextField) e.getSource();
        int textW = textField.getWidth();
        int textH = textField.getHeight();
        if (e.getX() == 0){
            setCursor(cursorW);// 设置左边框光标
        }else if (e.getY() == 0){
            setCursor(cursorN);// 设置上边框光标
        }else if (e.getX() == textW-3){
            setCursor(cursorE);// 设置右边框光标
        }else if (e.getY() == textH-3){
            setCursor(cursorS);// 设置下边框光标
        }else{
            setCursor(cursorD);// 设置默认光标
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setBorder(null);
    }

}
