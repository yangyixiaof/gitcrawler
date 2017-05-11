package com.zzk;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.DAY_OF_WEEK;
import static java.util.Calendar.HOUR;
import static java.util.Calendar.MILLISECOND;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.SECOND;
import static java.util.Calendar.YEAR;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * ʱ�����
 * 
 * @author ������
 */
@SuppressWarnings("serial")
public class ClockPanel extends JPanel {
    // 3��ָ��Ĵ�ϸ
    private static final BasicStroke HOURS_POINT_WIDTH = new BasicStroke(4);
    private static final BasicStroke MINUETES_POINT_WIDTH = new BasicStroke(3);
    private static final BasicStroke SEC_POINT_WIDTH = new BasicStroke(2);
    ImageIcon background;// ����ͼƬ����
    private int centerX;// ��������
    private int centerY;// ��������
    private final static int secLen = 60; // ָ�볤��
    private final static int minuesLen = 55; // ָ�볤��
    private final static int hoursLen = 36; // ָ�볤��
    
    /**
     * ���췽��
     */
    public ClockPanel() {
        setToolTipText("С����+��-����͸���ȣ�Crtl+Shift+X�˳�");// ��ʾ��Ϣ
        setOpaque(false);
        
        background = new ImageIcon(getClass().getResource("clock.jpg"));// ����ͼƬ
        int iconWidth = background.getIconWidth();// ��ȡͼƬ��С
        centerX = iconWidth / 2;// ��ȡͼƬ�м�����
        int iconHeight = background.getIconHeight();
        centerY = iconHeight / 2;// ��ȡͼƬ�м�����
        setPreferredSize(new Dimension(iconWidth, iconHeight));
    }
    
    @Override
    public void paint(Graphics g) {// ��д���෽��
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(background.getImage(), 0, 0, this);// ���Ʊ���ͼƬ
        Calendar calendar = Calendar.getInstance();// ��ȡ��������
        int millisecond = calendar.get(MILLISECOND);// ��ȡ����ֵ
        int sec = calendar.get(SECOND);// ��ȡ��ֵ
        int minutes = calendar.get(MINUTE);// ��ȡ��ֵ
        int hours = calendar.get(HOUR);// ��ȡʱֵ
        String dateStr = calendar.get(YEAR) + "��" + (calendar.get(MONTH) + 1)
                + "��" + calendar.get(DAY_OF_MONTH) + "��";// ��ǰ�����ַ���
        int weekDay = calendar.get(DAY_OF_WEEK) - 1;// ��ǰ����ֵ
        String weekStr = "";
        if (weekDay == 0) {
            weekStr = "������";
        } else {
            weekStr = "����" + weekDay;
        }
        
        {// ��������������
            g2.setColor(Color.BLACK);// ���ñ���ɫ
            g2.setFont(new Font("����", Font.BOLD, 12));// ��������
            // ��ȡ������Ⱦ������
            FontRenderContext fontRenderContext = g2.getFontRenderContext();
            // ���������ַ����ı߽��С
            Rectangle2D dateStrBounds = getFont().getStringBounds(dateStr,
                    fontRenderContext);
            // ��ʱ���°벿��������
            g2.drawString(dateStr,
                    (int) (centerX - dateStrBounds.getWidth() / 2),
                    centerY + 20);
            // �ı������С
            g2.setFont(new Font("����", Font.BOLD, 14));
            // ���������ַ����߽��С
            Rectangle2D weekStrBounds = getFont().getStringBounds(weekStr,
                    fontRenderContext);
            // ���������ַ���
            g2.drawString(weekStr,
                    (int) (centerX - weekStrBounds.getWidth() / 2),
                    centerY + 35);
        }
        double secAngle = (60 - sec) * 6 - (millisecond / 150); // ����Ƕ�
        int minutesAngle = (60 - minutes) * 6;// ����Ƕ�
        int hoursAngle = (12 - hours) * 360 / 12 - (minutes / 2);// ʱ��Ƕ�
        // �������롢���롢ʱ��ָ�������
        int secX = (int) (secLen * Math.sin(Math.toRadians(secAngle)));// ����ָ����X����
        int secY = (int) (secLen * Math.cos(Math.toRadians(secAngle))); // ����ָ����Y����
        int minutesX = (int) (minuesLen * Math
                .sin(Math.toRadians(minutesAngle))); // ����ָ����X����
        int minutesY = (int) (minuesLen * Math
                .cos(Math.toRadians(minutesAngle))); // ����ָ����Y����
        int hoursX = (int) (hoursLen * Math.sin(Math.toRadians(hoursAngle))); // ʱ��ָ����X����
        int hoursY = (int) (hoursLen * Math.cos(Math.toRadians(hoursAngle))); // ʱ��ָ����Y����
        // �ֱ����ʱ�롢���롢����
        g2.setStroke(HOURS_POINT_WIDTH);// ����ʱ��Ŀ��
        g2.setColor(Color.BLACK);// ����ʱ�����ɫ
        g2.drawLine(centerX, centerY, centerX - hoursX, centerY - hoursY);// ����ʱ��
        g2.setStroke(MINUETES_POINT_WIDTH);// ���÷���Ŀ��
        if (minutesAngle != hoursAngle) // ���롢ʱ���ص���ɫ
            g2.setColor(new Color(0x2F2F2F));// ����δ�ص�ʱ����ɫ
        else {
            g2.setColor(Color.GREEN);// �����ص�ʱ����ɫ
        }
        g2.drawLine(centerX, centerY, centerX - minutesX, centerY - minutesY);// ���Ʒ���
        g2.setStroke(SEC_POINT_WIDTH);// ��������Ŀ��
        if (secAngle != hoursAngle && secAngle != minutesAngle)// ���롢ʱ�롢�����ص���ɫ
            g2.setColor(Color.ORANGE);// ����δ�ص�ʱ����ɫ
        else {
            g2.setColor(Color.GREEN);// �����ص�ʱ����ɫ
        }
        // ����3��ָ�������Բ������
        g2.fillOval(centerX - 5, centerY - 5, 10, 10);// ��������Բ
        g2.drawLine(centerX, centerY, centerX - secX, centerY - secY);// ��������
        g2.drawLine(centerX + 1, centerY + 1, centerX - secX + 1, centerY
                - secY + 1);// ��������
    }
}
