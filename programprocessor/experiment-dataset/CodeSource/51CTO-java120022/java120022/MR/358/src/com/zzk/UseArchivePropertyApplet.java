package com.zzk;

import javax.swing.JApplet;

public class UseArchivePropertyApplet extends JApplet {
    public void init() {
        // ʹ����html�ļ�������NewPanelApp.jar�ļ��е�NewPanel�ഴ������
        npanel.NewPanel npanel = new npanel.NewPanel();
        setLayout(null);// ��������Ϊ���Բ���
        npanel.setBounds(10, 10, 254, 167);// ����NewPanel�������ʾλ�úʹ�С
        add(npanel);// ��NewPanel������ӵ�������
    }
}
