package com.lzw.ip;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CText extends JTextField {
    public CText() {
        setBorder(null);// ȡ���߿�
        setHorizontalAlignment(SwingConstants.CENTER);// �ı�����
        setFont(getFont().deriveFont(16f));// ɫ����Ĭ��16������
        addKeyListener(new KeyAdapter() {// ��Ӱ����¼�������
            @Override
            public void keyTyped(KeyEvent e) {
                if (("0123456789" + (char) 8).indexOf(e.getKeyChar()) < 0) {
                    e.consume();// ���η���������˼�������
                    return;
                }
                if (e.getKeyChar() == (char) 8) {
                    return;// ���λ��˼�
                }
                String text = getText() + e.getKeyChar();// ��ȡ��������
                if (!text.isEmpty()) {// �������ǿ�
                    int value = Integer.parseInt(text);// ���������Ϊ����
                    if (value > 225) {// �����������225
                        e.consume();// ȡ����������
                        return;
                    }
                }
                // ��������ı��������������dot�ַ�
                if (getText().length() > 2 || e.getKeyChar() == '.') {
                    e.consume();// ȡ����������
                    transferFocus();// �����뽹�㴫�ݸ���һ���ؼ�
                    return;
                }
            }
            
            @Override
            public void keyPressed(KeyEvent e) {
                // ����ճ����ݼ�
                if (e.getKeyCode() == KeyEvent.VK_V && e.isControlDown()) {
                    e.consume();
                }
            }
        });
    }
    
    /**
     * ��ȡ��������ֵ�ķ���
     * 
     * @return
     */
    public int getInt() {
        String text = getText();// ��ȡ�����ı�
        if (text.isEmpty())// ���ο�����
            return 0;
        int value = Integer.parseInt(text);// ��������Ϊ����
        return value;
    }
    
    @Override
    public String toString() {
        return getInt() + "";
    }
    
}
