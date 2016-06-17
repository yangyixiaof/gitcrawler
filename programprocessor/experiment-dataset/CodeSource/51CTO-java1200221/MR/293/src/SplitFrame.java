import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.UIManager;

public class SplitFrame extends JFrame {
    
    private JPanel contentPane;
    
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
                    SplitFrame frame = new SplitFrame();
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
    public SplitFrame() {
        setTitle("�ָ�Ĵ������");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        JSplitPane splitPane = new JSplitPane();// �����ָ����
        splitPane.setResizeWeight(0.5);// �������ؼ�����İٷֱ�
        splitPane.setContinuousLayout(true);// �������ַָ����
        contentPane.add(splitPane, BorderLayout.CENTER);
        // �����������
        BGPanel panel = new BGPanel();
        panel.setIconFill(BGPanel.BOTH_FILL);// ����˫�����
        // ���ñ���ͼƬ
        panel.setImage(getToolkit().getImage(
                getClass().getResource("photo1.jpg")));// ���ñ���ͼƬ
        splitPane.setLeftComponent(panel);
        // �����������
        BGPanel panel_1 = new BGPanel();
        panel_1.setIconFill(BGPanel.BOTH_FILL);// ����˫�����
        panel_1.setImage(getToolkit().getImage(
                getClass().getResource("photo2.jpg")));// ���ñ���ͼƬ
        splitPane.setRightComponent(panel_1);
    }
    
}
