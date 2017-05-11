package com.zzk;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import com.swtdesigner.SwingResourceManager;

/**
 * @author 张振坤
 */
public class TypeLetterFrame extends JFrame {
    private JLabel lb_ok; // 显示正确率的标签
    private JLabel lb_speed; // 显示打字速度的标签
    // 创建背景面板对象
    private final BackgroundPanel backgroundPanel = new BackgroundPanel();
    
    private RandomBuildLetter buildLetter = new RandomBuildLetter();// 创建随机产生字母的类的对象
    private JLabel[] labels = null; // 创建标签
    private Vector<String> vector = new Vector<String>(); // 创建存储准备击打字母的向量
    final JLabel label = new JLabel();
    private Date startTime = new Date();
    private int totalSeconds = 0; // 打字的总秒数
    private int totalOkLetters = 0; // 打正确的字母总数
    private int totalLetters = 0; // 出现字母总数
    private int speed = 0; // 打字速度
    
    public TypeLetterFrame() {
        super();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("打字母游戏");
        setBounds(100, 80, 520, 400);
        
        addKeyListener(new GameListener()); // 为窗体添加按键监听器
        
        backgroundPanel.setImage(SwingResourceManager.getImage(
                TypeLetterFrame.class, "/image/background.jpg"));
        getContentPane().add(backgroundPanel, BorderLayout.CENTER);
        
        label.setBounds(10, 10, 157, 18);
        backgroundPanel.add(label);
        
        lb_speed = new JLabel();
        lb_speed.setBounds(173, 10, 157, 18);
        backgroundPanel.add(lb_speed);
        
        lb_ok = new JLabel();
        lb_ok.setBounds(345, 10, 157, 18);
        backgroundPanel.add(lb_ok);
        addLetter();
        Thread th = new Thread(new MoveLetterThread());
        th.start();
    }
    
    private void addLetter() {
        int seed = 10; // 设置标签之间偏移量的变量
        // 调用RandomBuildLetter类的方法随机产生8个整数并赋值给数组，即8个A-Z之间字母的ASCII值
        int[] letters = buildLetter.getLetter(8);
        labels = new JLabel[letters.length]; // 创建显示带字母苹果的标签数组
        // 实例化标签数组的每个对象
        for (int i = 0; i < letters.length; i++) {
            int value = letters[i]; // 获得数组letters中的值
            char c = (char) value; // 将数组letters中的值转换为字符
            String s = String.valueOf(c); // 将字符转换为字符串
            labels[i] = new JLabel(); // 实例化标签对象
            labels[i].setToolTipText(s); // 设置标签的提示文本
            // 设置标签显示的图片，即带字母的苹果
            labels[i].setIcon(SwingResourceManager.getIcon(
                    TypeLetterFrame.class, "/icon/" + s + ".png"));
            int x = (int) (Math.random() * 60) + seed; // 随机产生标签显示位置的横坐标
            int y = (int) (Math.random() * 80); // 随机产生标签显示位置的纵坐标
            labels[i].setBounds(x, y, 100, 30); // 设置标签的显示位置和大小
            backgroundPanel.add(labels[i]); // 将标签添加到背景面板中
            seed += 60; // 调整标签之间的偏移量
            vector.add(s); // 将字符串字母添到到向量对象中
            totalLetters++; // 计算出现字母的总个数
        }
    }
    
    /**
     * @author 张振坤
     *         使字母移动的线程
     */
    private class MoveLetterThread implements Runnable {
        int seed = 10; // 定义标签之间偏移量的变量
        
        public void run() {
            while (true) {
                // 对显示苹果字母的标签数组进行操作
                for (int i = 0; i < labels.length; i++) {
                    // 设置标签的显示位置
                    labels[i].setLocation(labels[i].getX(), labels[i].getY()
                            + (int) (Math.random() * labels[i].getHeight()));
                    if (labels[i].getY() > 400) { // 标签纵坐标大于400
                        String oldValue = labels[i].getToolTipText(); // 获得标签上原来的提示文本
                        int value = buildLetter.getRandomLetter(); // 随机产生A-Z的ASCII值
                        char c = (char) value; // 转换成字母字符
                        String s = String.valueOf(c); // 将字母字符转换为字符串
                        labels[i].setToolTipText(s); // 设置新的提示文本
                        // 设置图标，即带字母的苹果
                        labels[i].setIcon(SwingResourceManager.getIcon(
                                TypeLetterFrame.class, "/icon/" + s + ".png"));
                        totalLetters++; // 计算出现字母的总个数
                        labels[i].setVisible(true); // 显示标签
                        int x = (int) (Math.random() * 60) + seed; // 随机产生标签显示位置的横坐标
                        int y = (int) (Math.random() * 80); // 随机产生标签显示位置的纵坐标
                        labels[i].setLocation(x, y); // 设置标签的显示位置
                        seed += 60; // 调整标签之间的偏移量
                        if (seed >= 490) { // 如果seed大于490
                            int m = 0; // 声明要从向量中移除项的索引
                            for (int j = 0; j < vector.size(); j++) { // 遍历向量对象
                                String str = vector.get(j).toString(); // 获得向量中的元素
                                if (str.equals(oldValue)) { // 如果与标签上原来的提示文本相同
                                    m = j; // 为索引m赋值，即要从向是中删除项的索引
                                }
                            }
                            vector.remove(m); // 从向量中移除元素
                            seed = 10; // 重新设置标签之间的偏移量
                        }
                        vector.add(s); // 在向量中添加新的元素
                    }
                    // 总的秒数
                    int second0 = (int) ((new Date().getTime() - startTime
                            .getTime()) / 1000);
                    int second = second0 % 60; // 时间中的秒
                    int minute0 = new Double(second0 / 60).intValue(); // 总的分钟
                    totalSeconds = second0; // 存放总的秒数
                    int minute = minute0 % 60; // 时间中的分钟
                    int hour = new Double(minute0 / 60).intValue(); // 总的小时数
                    // 在标签上显示时间
                    label.setText("用时：" + String.valueOf(hour) + ":"
                            + String.valueOf(minute) + ":"
                            + String.valueOf(second));
                    speed = (int) (totalOkLetters / (totalSeconds / 60.0f)); // 计算打字速度
                    // 在标签上显示打字事件
                    lb_speed.setText("速度（个/分钟）" + String.valueOf(speed));
                    int ok = 0; // 存放正确率的变量
                    if (totalLetters != 0) {
                        ok = (totalOkLetters * 100 / totalLetters); // 计算正确率
                    }
                    lb_ok.setText("正确率（百分比）" + String.valueOf(ok)); // 在标签上显示正确率
                }
                try {
                    Thread.sleep(200); // 休眠200毫秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * @author 张振坤
     *         键盘按键监听器类
     */
    private class GameListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode(); // 获得按键的值
            if (code >= 65 && code <= 90) { // 是大写字母
                char c = (char) code; // 转换为字母字符
                String s = String.valueOf(c); // 转换为字符串
                for (int i = 0; i < vector.size(); i++) { // 遍历向量对象
                    String str = vector.get(i).toString(); // 获得向量中的元素
                    if (s.equals(str)) { // 按键字符与向量中的相同
                        for (int j = 0; j < labels.length; j++) { // 遍历标签数组
                            if (str.equals(labels[j].getToolTipText())) { // 按键字符与标签提示文本相同
                                labels[j].setVisible(false); // 隐藏该标签
                                int value = buildLetter.getRandomLetter(); // 获得新字符的ASCII值
                                char ch = (char) value; // 转换为字符
                                String ss = String.valueOf(ch); // 转换为字符串
                                labels[j].setToolTipText(ss); // 重新设置标签的提示文本
                                // 设置标签上显示的字母苹果图片
                                labels[j].setIcon(SwingResourceManager.getIcon(
                                        TypeLetterFrame.class, "/icon/" + ss
                                                + ".png"));
                                totalLetters++; // 计算出现字母的总数
                                labels[j].setVisible(true); // 显示标签组件
                                int x = (int) (Math.random() * 20 + labels[j]
                                        .getX()); // 随机生成标签组件的横坐标
                                int y = (int) (Math.random() * -80); // 随机生成标签组件的纵坐标
                                labels[j].setLocation(x, y); // 设置标签的显示位置
                                vector.add(ss); // 将新字符串添加到向量中
                                break; // 结束循环
                            }
                        }
                        int x = 0; // 声明要从向量中移除项的索引
                        for (int k = 0; k < vector.size(); k++) { // 遍历向量对象
                            String oldValue = vector.get(k).toString(); // 获得向量中的元素
                            if (str.equals(oldValue)) { // 如果与标签上原来的提示文本相同
                                x = k; // 为索引x赋值，即要从向是中删除项的索引
                            }
                        }
                        vector.remove(x); // 从向量中移除元素
                        totalOkLetters++; // 打正确的字母总数
                        break;
                    }
                }
            }
        }
    }
    
    public static void main(String[] args) {
        TypeLetterFrame frame = new TypeLetterFrame();
        frame.setVisible(true);
    }
    
}
