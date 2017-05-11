package com.zzk;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.swtdesigner.SwingResourceManager;

/**
 * ��Ϸ���
 * 
 * @author ������
 */
public class GamePanel extends JPanel implements MouseListener {
    private static final long serialVersionUID = -653831947783440122L;
    private Cell[] cells = new Cell[9];// ������ԪͼƬ����
    private Cell cellBlank = null;// �հ�
    
    // ���췽��
    public GamePanel() {
        super();
        setLayout(null);// ���ÿղ���
        init();// ��ʼ��
    }
    
    // ��ʼ����Ϸ
    public void init() {
        int num = 0;// ͼƬ���
        Icon icon = null;// ͼ�����
        Cell cell = null;// ��ԪͼƬ����
        for (int i = 0; i < 3; i++) {// ѭ����
            for (int j = 0; j < 3; j++) {// ѭ����
                num = i * 3 + j;// ����ͼƬ���
                icon = SwingResourceManager.getIcon(GamePanel.class, "/pic/"
                        + (num + 1) + ".jpg");// ��ȡͼƬ
                cell = new Cell(icon, num);// ʵ������ԪͼƬ����
                cell.setLocation(j * Cell.IMAGEWIDTH, i * Cell.IMAGEWIDTH);// ���õ�ԪͼƬ������
                cells[num] = cell;// ����ԪͼƬ�洢����ԪͼƬ������
            }
        }
        for (int i = 0; i < cells.length; i++) {
            this.add(cells[i]);// �������������е�ԪͼƬ
        }
    }
    
    /**
     * ��ͼƬ�����������
     */
    public void random() {
        Random rand = new Random();// ʵ����Random
        int m, n, x, y;
        if (cellBlank == null) {// �жϿհ׵�ͼƬλ���Ƿ�Ϊ��
            cellBlank = cells[cells.length - 1];// ȡ���հ׵�ͼƬ
            for (int i = 0; i < cells.length; i++) {// �������е�ԪͼƬ
                if (i != cells.length - 1) {
                    cells[i].addMouseListener(this);// �Էǿհ�ͼƬע��������
                }
            }
        }
        for (int i = 0; i < cells.length; i++) {// �������е�ԪͼƬ
            m = rand.nextInt(cells.length);// ���������
            n = rand.nextInt(cells.length);// ���������
            x = cells[m].getX();// ��ȡx����
            y = cells[m].getY();// ��ȡy����
            // �Ե�ԪͼƬ����
            cells[m].setLocation(cells[n].getX(), cells[n].getY());
            cells[n].setLocation(x, y);
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Cell cell = (Cell) e.getSource();// ��ȡ����ʱ��Ķ���
        int x = cellBlank.getX();// ��ȡx����
        int y = cellBlank.getY();// ��ȡy����
        if ((x - cell.getX()) == Cell.IMAGEWIDTH && cell.getY() == y) {
            cell.move(Direction.RIGHT);// �����ƶ�
            cellBlank.move(Direction.LEFT);
        } else if ((x - cell.getX()) == -Cell.IMAGEWIDTH && cell.getY() == y) {
            cell.move(Direction.LEFT);// �����ƶ�
            cellBlank.move(Direction.RIGHT);
        } else if (cell.getX() == x && (cell.getY() - y) == Cell.IMAGEWIDTH) {
            cell.move(Direction.UP);// �����ƶ�
            cellBlank.move(Direction.DOWN);
        } else if (cell.getX() == x && (cell.getY() - y) == -Cell.IMAGEWIDTH) {
            cell.move(Direction.DOWN);// �����ƶ�
            cellBlank.move(Direction.UP);
        }
        if (isSuccess()) {// �ж��Ƿ�ƴͼ�ɹ�
            int i = JOptionPane.showConfirmDialog(this, "�ɹ�������һ�֣�", "ƴͼ�ɹ�",
                    JOptionPane.YES_NO_OPTION);// ��ʾ�ɹ�
            if (i == JOptionPane.YES_OPTION) {
                random();// ��ʼ��һ��
            }
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    /**
     * �ж��Ƿ�ƴͼ�ɹ�
     * 
     * @return ����ֵ
     */
    public boolean isSuccess() {
        for (int i = 0; i < cells.length; i++) {// �������е�ԪͼƬ
            int x = cells[i].getX();// ��ȡx����
            int y = cells[i].getY();// ��ȡy����
            if (i != 0) {
                if (y / Cell.IMAGEWIDTH * 3 + x / Cell.IMAGEWIDTH != cells[i]
                        .getPlace()) {// �жϵ�ԪͼƬλ���Ƿ���ȷ
                    return false;// ֻҪ��һ����ԪͼƬ��λ�ò���ȷ���ͷ���false
                }
            }
        }
        return true;// ���е�ԪͼƬ��λ�ö���ȷ����true
    }
}
