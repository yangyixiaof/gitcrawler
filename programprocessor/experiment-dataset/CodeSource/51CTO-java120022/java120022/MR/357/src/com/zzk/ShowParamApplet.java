package com.zzk;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

public class ShowParamApplet extends Applet {
    String id;// ������Ա����
    String name;// ������Ա����
    String sex;// ������Ա����
    public void init() {
        id = getParameter("id");// ��ò���id��ֵ
        name = getParameter("name");// ��ò���name��ֵ
        sex = getParameter("sex");// ��ò���sex��ֵ
    }
    public void paint(Graphics g) {
        g.setColor(Color.blue);// ������ɫ
        g.drawString("ѧ�ţ�", 30, 10);// �����ı�
        g.drawString(id, 70, 10);// ���ƻ�õ�idֵ
        g.drawString("������", 30, 30);// �����ı�
        g.drawString(name, 70, 30);// ���ƻ�õ�nameֵ
        g.drawString("�Ա�", 30, 50);// �����ı�
        g.drawString(sex, 70, 50);// ���ƻ�õ�sexֵ
    }
}
