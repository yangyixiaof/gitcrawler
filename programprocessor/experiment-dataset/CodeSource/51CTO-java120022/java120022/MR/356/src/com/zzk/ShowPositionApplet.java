package com.zzk;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
public class ShowPositionApplet extends Applet {
    String author;// ������Ա����
    public void init() {
        author = "ZhenKun Zhang";// ��ʼ����Ա����
    }
    public void paint(Graphics g){
        g.setColor(Color.blue);// ������ɫ
        g.drawString(author, 50, 30);// �����ı�
    }
}
