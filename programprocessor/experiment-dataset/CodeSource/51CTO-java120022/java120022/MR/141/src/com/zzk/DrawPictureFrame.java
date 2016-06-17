package com.zzk;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;

public class DrawPictureFrame extends JFrame {
    // ����ͼ�����
    BufferedImage image = new BufferedImage(570, 390,
            BufferedImage.TYPE_INT_BGR);
    Graphics gs = image.getGraphics(); // ���ͼ��Ļ�ͼ�����Ķ���
    Graphics2D g = (Graphics2D) gs; // ����ͼ�����Ķ���ת��ΪGraphics2D����
    DrawPictureCanvas canvas = new DrawPictureCanvas(); // ������������
    Color foreColor = Color.BLACK; // ����ǰ��ɫ
    Color backgroundColor = Color.WHITE; // ���屳��ɫ
    boolean rubber = false; // ��Ƥ��ʶ����
    int x = -1; // ��һ�������Ƶ�ĺ�����
    int y = -1; // ��һ�������Ƶ��������
    private JMenuItem newItemMenuItem_6 = null;
    
    public DrawPictureFrame() {
        super();
        setResizable(false);
        setTitle("��ͼ����");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 80, 574, 397);
        g.setColor(backgroundColor); // �ñ���ɫ���û�ͼ�����Ķ������ɫ
        g.fillRect(0, 0, 570, 390); // �ñ���ɫ�����������
        g.setColor(foreColor); // ��ǰ��ɫ���û�ͼ�����Ķ������ɫ
        canvas.setImage(image); // ���û�����ͼ��
        getContentPane().add(canvas); // ��������ӵ���������Ĭ�ϲ��ֵ��в�λ��
        canvas.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(final MouseEvent e) {
                if (rubber) { // ��Ƥ��ʶΪtrue����ʾʹ����Ƥ
                    if (x > 0 && y > 0) {
                        g.setColor(backgroundColor); // �ñ���ɫ���û�ͼ�����Ķ������ɫ
                        g.fillRect(x, y, 10, 10); // ������꾭��λ�õ�ͼ��
                    }
                    x = e.getX(); // �������ڻ����ϵĺ�����
                    y = e.getY(); // �������ڻ����ϵ�������
                } else { // ��Ƥ��ʶΪfalse����ʾ��ͼ
                    if (x > 0 && y > 0) {
                        // ����꾭������ֱ��
                        g.drawLine(x, y, e.getX(), e.getY());
                    }
                    x = e.getX(); // ��һ�������Ƶ�ĺ�����
                    y = e.getY(); // ��һ�������Ƶ��������
                }
                canvas.repaint(); // ���»���
            }
            
            public void mouseMoved(final MouseEvent arg0) {
                if (rubber) {
                    // �������ָ�����״
                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                } else {
                    // �������ָ�����״
                    setCursor(Cursor
                            .getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                }
            }
        });
        canvas.addMouseListener(new MouseAdapter() {
            public void mouseReleased(final MouseEvent arg0) {
                x = -1; // ��һ�������Ƶ�ĺ�����
                y = -1; // ��һ�������Ƶ��������
            }
        });
        final JToolBar toolBar = new JToolBar();
        toolBar.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(final MouseEvent arg0) {
                // �������ָ�����״
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });
        getContentPane().add(toolBar, BorderLayout.NORTH);
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                // �������ʵ����ԣ���ϸΪ1����
                BasicStroke bs = new BasicStroke(1, BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER);
                g.setStroke(bs); // ���û�ͼ�����Ķ���Ļ���
            }
        });
        button.setText("  ϸ  ��  ");
        toolBar.add(button);
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                // �������ʵ����ԣ���ϸΪ2����
                BasicStroke bs = new BasicStroke(2, BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER);
                g.setStroke(bs); // ���û�ͼ�����Ķ���Ļ���
            }
        });
        button_1.setText("  ��  ��  ");
        toolBar.add(button_1);
        
        final JButton button_2 = new JButton();
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                // �������ʵ����ԣ���ϸΪ4����
                BasicStroke bs = new BasicStroke(4, BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER);
                g.setStroke(bs); // ���û�ͼ�����Ķ���Ļ���
            }
        });
        button_2.setText("  ��  ��  ");
        toolBar.add(button_2);
        final JButton button_3 = new JButton();
        button_3.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                // ��ѡ����ɫ�Ի���
                Color bgColor = JColorChooser.showDialog(null, "ѡ����ɫ�Ի���",
                        Color.CYAN);
                if (bgColor != null) {
                    backgroundColor = bgColor;
                }
                g.setColor(backgroundColor); // ���û�ͼ�����Ķ���ı���ɫ
                g.fillRect(0, 0, 570, 390); // �ñ���ɫ�����������
                g.setColor(foreColor); // ���û�ͼ�����Ķ����ǰ��ɫ
                canvas.repaint(); // ���»���
            }
        });
        button_3.setText("������ɫ");
        toolBar.add(button_3);
        final JButton button_4 = new JButton();
        button_4.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                // ��ѡ����ɫ�Ի���
                Color fColor = JColorChooser.showDialog(null, "ѡ����ɫ�Ի���",
                        Color.CYAN);
                if (fColor != null) {
                    foreColor = fColor;
                }
                g.setColor(foreColor); // ���û�ͼ�����Ķ����ǰ��ɫ
            }
        });
        button_4.setText("ǰ����ɫ");
        toolBar.add(button_4);
        final JButton button_5 = new JButton();
        button_5.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                g.setColor(backgroundColor); // ���û�ͼ�����Ķ���ı���ɫ
                g.fillRect(0, 0, 570, 390); // �ñ���ɫ�����������
                g.setColor(foreColor); // ���û�ͼ�����Ķ����ǰ��ɫ
                canvas.repaint(); // ���»���
            }
        });
        button_5.setText("  ��  ��  ");
        toolBar.add(button_5);
        
        final JButton button_6 = new JButton();
        button_6.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                if (button_6.getText().equals("  ��  Ƥ  ")) { // �����������ϵ���Ƥ��ť��ʹ����Ƥ
                    rubber = true; // ������Ƥ��ʶΪtrue
                    newItemMenuItem_6.setText("��  ͼ"); // �ı�˵�����ʾ���ı�Ϊ��ͼ
                    button_6.setText("  ��  ͼ  "); // �ı䰴ť����ʾ���ı�Ϊ��ͼ
                } else { // �����������ϵĻ�ͼ��ť��ʹ�û���
                    rubber = false; // ������Ƥ��ʶΪfalse
                    newItemMenuItem_6.setText("��  Ƥ"); // �ı�˵�����ʾ���ı�Ϊ��Ƥ
                    button_6.setText("  ��  Ƥ  "); // �ı䰴ť����ʾ���ı�Ϊ��Ƥ
                    g.setColor(foreColor); // ���û�ͼ�����Ķ����ǰ��ɫ
                }
            }
        });
        button_6.setText("  ��  Ƥ  ");
        toolBar.add(button_6);
        
        final JButton button_7 = new JButton();
        button_7.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                System.exit(0); // �˳�Ӧ�ó���
            }
        });
        button_7.setText("  ��  ��  ");
        toolBar.add(button_7);
        
        final JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        final JMenu menu = new JMenu();
        menu.setText("��  ��");
        menuBar.add(menu);
        
        final JMenuItem newItemMenuItem = new JMenuItem();
        newItemMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                // �������ʵ����ԣ���ϸΪ1����
                BasicStroke bs = new BasicStroke(1, BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER);
                g.setStroke(bs); // ���û�ͼ�����Ķ���Ļ���
            }
        });
        newItemMenuItem.setText("ϸ  ��");
        menu.add(newItemMenuItem);
        
        final JMenuItem newItemMenuItem_1 = new JMenuItem();
        newItemMenuItem_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                // �������ʵ����ԣ���ϸΪ2����
                BasicStroke bs = new BasicStroke(2, BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER);
                g.setStroke(bs); // ���û�ͼ�����Ķ���Ļ���
            }
        });
        newItemMenuItem_1.setText("��  ��");
        menu.add(newItemMenuItem_1);
        
        final JMenuItem newItemMenuItem_2 = new JMenuItem();
        newItemMenuItem_2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                // �������ʵ����ԣ���ϸΪ4����
                BasicStroke bs = new BasicStroke(4, BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER);
                g.setStroke(bs); // ���û�ͼ�����Ķ���Ļ���
            }
        });
        newItemMenuItem_2.setText("��  ��");
        menu.add(newItemMenuItem_2);
        
        final JMenu menu_1 = new JMenu();
        menu_1.setText("��  ɫ");
        menuBar.add(menu_1);
        
        final JMenuItem newItemMenuItem_3 = new JMenuItem();
        newItemMenuItem_3.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                // ��ѡ����ɫ�Ի���
                Color fColor = JColorChooser.showDialog(null, "ѡ����ɫ�Ի���",
                        Color.CYAN);
                if (fColor != null) {
                    foreColor = fColor;
                }
                g.setColor(foreColor); // ���û�ͼ�����Ķ����ǰ��ɫ
            }
        });
        newItemMenuItem_3.setText("ǰ����ɫ");
        menu_1.add(newItemMenuItem_3);
        
        final JMenuItem newItemMenuItem_4 = new JMenuItem();
        newItemMenuItem_4.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                // ��ѡ����ɫ�Ի���
                Color bgColor = JColorChooser.showDialog(null, "ѡ����ɫ�Ի���",
                        Color.CYAN);
                if (bgColor != null) {
                    backgroundColor = bgColor;
                }
                g.setColor(backgroundColor); // ���û�ͼ�����Ķ���ı���ɫ
                g.fillRect(0, 0, 570, 390); // �ñ���ɫ�����������
                g.setColor(foreColor); // ���û�ͼ�����Ķ����ǰ��ɫ
                canvas.repaint(); // ���»���
            }
        });
        newItemMenuItem_4.setText("������ɫ");
        menu_1.add(newItemMenuItem_4);
        
        final JMenu menu_2 = new JMenu();
        menu_2.setText("��  ��");
        menuBar.add(menu_2);
        
        final JMenuItem newItemMenuItem_5 = new JMenuItem();
        newItemMenuItem_5.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                g.setColor(backgroundColor); // ���û�ͼ�����Ķ���ı���ɫ
                g.fillRect(0, 0, 570, 390); // �ñ���ɫ�����������
                g.setColor(foreColor); // ���û�ͼ�����Ķ����ǰ��ɫ
                canvas.repaint(); // ���»���
            }
        });
        newItemMenuItem_5.setText("��  ��");
        menu_2.add(newItemMenuItem_5);
        
        newItemMenuItem_6 = new JMenuItem();
        newItemMenuItem_6.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (newItemMenuItem_6.getText().equals("��  Ƥ")) { // ������Ƥ�˵���ʹ����Ƥ
                    rubber = true; // ������Ƥ��ʶΪtrue
                    newItemMenuItem_6.setText("��  ͼ"); // �ı�˵�����ʾ���ı�Ϊ��ͼ
                    button_6.setText("  ��  ͼ  "); // �ı䰴ť����ʾ���ı�Ϊ��ͼ
                } else { // �����������ϵĻ�ͼ��ť��ʹ�û���
                    rubber = false; // ������Ƥ��ʶΪfalse
                    newItemMenuItem_6.setText("��  Ƥ"); // �ı�˵�����ʾ���ı�Ϊ��Ƥ
                    button_6.setText("  ��  Ƥ  "); // �ı䰴ť����ʾ���ı�Ϊ��Ƥ
                    g.setColor(foreColor); // ���û�ͼ�����Ķ����ǰ��ɫ
                }
            }
        });
        newItemMenuItem_6.setText("��  Ƥ");
        menu_2.add(newItemMenuItem_6);
        
        final JMenu menu_3 = new JMenu();
        menu_3.setText("ϵͳ");
        menuBar.add(menu_3);
        
        final JMenuItem newItemMenuItem_8 = new JMenuItem();
        newItemMenuItem_8.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        newItemMenuItem_8.setText("��  ��");
        menu_3.add(newItemMenuItem_8);
    }
    
    public static void main(String[] args) {
        DrawPictureFrame frame = new DrawPictureFrame();
        frame.setVisible(true);
    }
}
