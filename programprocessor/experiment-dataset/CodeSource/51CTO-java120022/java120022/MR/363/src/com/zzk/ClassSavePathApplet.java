package com.zzk;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.net.URL;

public class ClassSavePathApplet extends Applet {
    URL url;// ����URL����
    public void start() {
        url = this.getCodeBase();// ���class���·����URL����
    }
    public void paint(Graphics g) {
        g.setColor(Color.blue);// ������ɫ
        g.drawString(url.getFile(), 30, 20);// ����class�Ĵ��·��
    }
}
