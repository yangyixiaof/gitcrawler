

import java.awt.*;
import java.io.Serializable;

import javax.swing.*;

/**
 * @author ����ξ
 */
public class BGPanel extends JPanel implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private Image image; // @jve:decl-index=0:
    public static final int HORIZONGTAL_FILL = 1;
    public static final int VERTICAL_FILL = 2;
    public static final int BOTH_FILL = 3;
    public static final int NO_FILL = 0;
    private int iconFill = NO_FILL;
    
    /**
     * This is the default constructor
     */
    public BGPanel() {
        super();
        initialize();
    }
    
    /**
     * This method initializes this
     * 
     * @return void
     */
    private void initialize() {
        this.setSize(new Dimension(300, 200));
        this.setLayout(new GridBagLayout());
    }
    
    public Image getImage() {
        return image;
    }
    
    public void setImage(Image icon) {
        this.image = icon;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);// ���ԭ���ؼ���۵Ļ���
        if (image != null) {// ��ʼ�Զ��屳���Ļ���
            switch (iconFill) {// �жϱ�����䷽ʽ
                case NO_FILL:// �����
                    g.drawImage(image, 0, 0, this);// ����ԭʼͼƬ��С
                    break;
                case HORIZONGTAL_FILL:// ˮƽ���
                    // ������ؼ��ȿ��ͼƬ
                    g.drawImage(image, 0, 0, getWidth(), image.getHeight(this),
                            this);
                    break;
                case VERTICAL_FILL:// ��ֱ���
                    // ������ؼ��ȸߵ�ͼƬ
                    g.drawImage(image, 0, 0, image.getWidth(this), getHeight(),
                            this);
                    break;
                case BOTH_FILL:// ˫�����
                    // ������ؼ�ͬ�ȴ�С��ͼƬ
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                    break;
                default:
                    break;
            }
        }
    }
    
    public int getIconFill() {
        return iconFill;
    }
    
    /**
     * ���ñ����ظ���ʽ
     * 
     * @param repeat
     *            �ظ���ʽ
     */
    public void setIconFill(int iconFill) {
        this.iconFill = iconFill;
    }
    
}
