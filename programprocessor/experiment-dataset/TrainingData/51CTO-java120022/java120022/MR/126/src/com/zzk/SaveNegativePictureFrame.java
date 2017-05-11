package com.zzk;

import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.LookupOp;
import java.awt.image.ShortLookupTable;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SaveNegativePictureFrame extends JFrame {
    private BufferedImage image;// ��������ͼ�����
    private NegativePicturePanel negativePicturePanel = null; // ����ͼ��������
    private boolean negativeFlag = false;// ������
    public static void main(String args[]) {
        SaveNegativePictureFrame frame = new SaveNegativePictureFrame();
        frame.setVisible(true);
    }
    
    public SaveNegativePictureFrame() {
        super();
        setTitle("���򲢱���ͼƬ"); // ���ô������
        setBounds(200, 160, 516, 458); // ���ô����С��λ��
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���ô���ر�ģʽ
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        negativePicturePanel = new NegativePicturePanel(); // ����ͼ��������
        add(negativePicturePanel); // �ڴ��������ͼ��������
        final JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                short[] negative = new short[256];// ������ʾ��ɫ����ķ�������
                for (int i = 0; i<256;i++){
                    negative[i] = (short)(255-i);// Ϊ���鸳ֵ
                }
                ShortLookupTable table = new ShortLookupTable(0,negative);// �������ұ����
                LookupOp op = new LookupOp(table,null);// ����ʵ�ִ�Դ��Ŀ����Ҳ�����LookupOp����
                image = op.filter(image, null);// ����LookupOp�����filter()������ʵ��ͼ������
                repaint();  // ����paint()����
                negativeFlag = !negativeFlag;// ����Ƿ��ѷ���
                if (negativeFlag){
                    button.setText("��ԭͼƬ");
                }else{
                    button.setText("����ͼƬ");
                }
            }
        });
        button.setText("����ͼƬ");
        panel.add(button);

        final JButton button_2 = new JButton();
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (!negativeFlag) {
                    JOptionPane.showMessageDialog(null,"��ûִ�з��������");// ��ʾ��ʾ��Ϣ
                    return;
                }
                FileDialog dialog = new FileDialog(SaveNegativePictureFrame.this,"����");// �����Ի���
                dialog.setMode(FileDialog.SAVE);// ���öԻ���Ϊ����Ի���
                dialog.setVisible(true);// ��ʾ����Ի���
                String path = dialog.getDirectory();// ����ļ��ı���·��
                String fileName = dialog.getFile();// ��ñ�����ļ���
                if (path == null || fileName == null){
                    return;
                }
                String fileExtName = fileName.substring(fileName.indexOf(".")+1);// �ļ���չ��,������
                String pathAndName = path + "\\" + fileName;// �ļ�������·��
                try {
                    ImageIO.write(image, fileExtName, new File(pathAndName));// ������ͼ�񱣴浽����
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null,"����ʧ��\n"+e1.getMessage());// ��ʾ��ʾ��Ϣ
                }
            }
        });
        button_2.setText("����ͼƬ");
        panel.add(button_2);
        
        final JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                System.exit(0);
            }
        });
        button_1.setText("��    ��");
        panel.add(button_1);
    }
    
    
    // ���������
    class NegativePicturePanel extends JPanel {
        public NegativePicturePanel(){
            Image img = null;// ��������ͼ�����
            try {
                img = ImageIO.read(new File("src/img/imag.jpg"));  // ����ͼ�����
            } catch (IOException e) {
                e.printStackTrace();
            }
            image = new BufferedImage(img.getWidth(null),img.getHeight(null),BufferedImage.TYPE_INT_RGB);// ��������ͼ�����
            image.getGraphics().drawImage(img, 0, 0,  null);// �ڻ���ͼ������ϻ���ͼ��
        }
        public void paint(Graphics g) {
            if (image != null) {
                g.drawImage(image, 0, 0, null);// ���ƻ���ͼ�����
            }
        }
    }
}
