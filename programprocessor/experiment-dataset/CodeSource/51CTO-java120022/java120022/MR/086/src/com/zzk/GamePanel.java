package com.zzk;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.swtdesigner.SwingResourceManager;

/**
 * 游戏面板
 * 
 * @author 张振坤
 */
public class GamePanel extends JPanel implements MouseListener {
    private static final long serialVersionUID = -653831947783440122L;
    private Cell[] cells = new Cell[9];// 创建单元图片数组
    private Cell cellBlank = null;// 空白
    
    // 构造方法
    public GamePanel() {
        super();
        setLayout(null);// 设置空布局
        init();// 初始化
    }
    
    // 初始化游戏
    public void init() {
        int num = 0;// 图片序号
        Icon icon = null;// 图标对象
        Cell cell = null;// 单元图片对象
        for (int i = 0; i < 3; i++) {// 循环行
            for (int j = 0; j < 3; j++) {// 循环列
                num = i * 3 + j;// 计算图片序号
                icon = SwingResourceManager.getIcon(GamePanel.class, "/pic/"
                        + (num + 1) + ".jpg");// 获取图片
                cell = new Cell(icon, num);// 实例化单元图片对象
                cell.setLocation(j * Cell.IMAGEWIDTH, i * Cell.IMAGEWIDTH);// 设置单元图片的坐标
                cells[num] = cell;// 将单元图片存储到单元图片数组中
            }
        }
        for (int i = 0; i < cells.length; i++) {
            this.add(cells[i]);// 向面板中添加所有单元图片
        }
    }
    
    /**
     * 对图片进行随机排序
     */
    public void random() {
        Random rand = new Random();// 实例化Random
        int m, n, x, y;
        if (cellBlank == null) {// 判断空白的图片位置是否为空
            cellBlank = cells[cells.length - 1];// 取出空白的图片
            for (int i = 0; i < cells.length; i++) {// 遍历所有单元图片
                if (i != cells.length - 1) {
                    cells[i].addMouseListener(this);// 对非空白图片注册鼠标监听
                }
            }
        }
        for (int i = 0; i < cells.length; i++) {// 遍历所有单元图片
            m = rand.nextInt(cells.length);// 产生随机数
            n = rand.nextInt(cells.length);// 产生随机数
            x = cells[m].getX();// 获取x坐标
            y = cells[m].getY();// 获取y坐标
            // 对单元图片调换
            cells[m].setLocation(cells[n].getX(), cells[n].getY());
            cells[n].setLocation(x, y);
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        Cell cell = (Cell) e.getSource();// 获取触发时间的对象
        int x = cellBlank.getX();// 获取x坐标
        int y = cellBlank.getY();// 获取y坐标
        if ((x - cell.getX()) == Cell.IMAGEWIDTH && cell.getY() == y) {
            cell.move(Direction.RIGHT);// 向右移动
            cellBlank.move(Direction.LEFT);
        } else if ((x - cell.getX()) == -Cell.IMAGEWIDTH && cell.getY() == y) {
            cell.move(Direction.LEFT);// 向左移动
            cellBlank.move(Direction.RIGHT);
        } else if (cell.getX() == x && (cell.getY() - y) == Cell.IMAGEWIDTH) {
            cell.move(Direction.UP);// 向上移动
            cellBlank.move(Direction.DOWN);
        } else if (cell.getX() == x && (cell.getY() - y) == -Cell.IMAGEWIDTH) {
            cell.move(Direction.DOWN);// 向下移动
            cellBlank.move(Direction.UP);
        }
        if (isSuccess()) {// 判断是否拼图成功
            int i = JOptionPane.showConfirmDialog(this, "成功，再来一局？", "拼图成功",
                    JOptionPane.YES_NO_OPTION);// 提示成功
            if (i == JOptionPane.YES_OPTION) {
                random();// 开始新一局
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
     * 判断是否拼图成功
     * 
     * @return 布尔值
     */
    public boolean isSuccess() {
        for (int i = 0; i < cells.length; i++) {// 遍历所有单元图片
            int x = cells[i].getX();// 获取x坐标
            int y = cells[i].getY();// 获取y坐标
            if (i != 0) {
                if (y / Cell.IMAGEWIDTH * 3 + x / Cell.IMAGEWIDTH != cells[i]
                        .getPlace()) {// 判断单元图片位置是否正确
                    return false;// 只要有一个单元图片的位置不正确，就返回false
                }
            }
        }
        return true;// 所有单元图片的位置都正确返回true
    }
}
