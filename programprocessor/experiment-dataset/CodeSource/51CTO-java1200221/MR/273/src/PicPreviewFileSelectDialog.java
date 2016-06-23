import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.net.MalformedURLException;

import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PicPreviewFileSelectDialog extends JFrame {
    
    private JPanel contentPane;
    private PaintPanel paint;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            UIManager
                    .setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PicPreviewFileSelectDialog frame = new PicPreviewFileSelectDialog();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * Create the frame.
     */
    public PicPreviewFileSelectDialog() {
        setTitle("\u652F\u6301\u56FE\u7247\u9884\u89C8\u7684\u6587\u4EF6\u9009\u62E9\u5BF9\u8BDD\u6846");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 557, 411);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        JFileChooser fileChooser = new JFileChooser();// �����ļ�ѡ����
        contentPane.add(fileChooser, BorderLayout.CENTER);// ��ӵ�����
        paint = new PaintPanel();// ����ͼƬԤ�����
        paint.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null,
                null));// �������ı߿�
        paint.setPreferredSize(new Dimension(150, 300));// ����Ԥ�����Ĵ�С
        fileChooser.setAccessory(paint);// ���������Ϊ�ļ�ѡ�����ؼ�
        // ���ѡ�����������¼�������
        fileChooser.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent arg0) {
                do_this_propertyChange(arg0);
            }
        });
        // �����ļ�ѡ�����Ĺ�����
        fileChooser.setFileFilter(new FileNameExtensionFilter("ͼƬ�ļ�", "jpg",
                "png", "gif"));
    }
    
    protected void do_this_propertyChange(PropertyChangeEvent e) {
        // ����ı�ѡ���ļ��������¼�����
        if (JFileChooser.SELECTED_FILE_CHANGED_PROPERTY == e.getPropertyName()) {
            File picfile = (File) e.getNewValue();// ��ȡѡ�����ļ�
            if (picfile != null && picfile.isFile()) {
                try {
                    // ���ļ�����ͼƬ
                    Image image = getToolkit()
                            .getImage(picfile.toURI().toURL());
                    paint.setImage(image);// ����Ԥ������ͼƬ
                    paint.repaint();// ˢ��Ԥ�����Ľ���
                } catch (MalformedURLException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
