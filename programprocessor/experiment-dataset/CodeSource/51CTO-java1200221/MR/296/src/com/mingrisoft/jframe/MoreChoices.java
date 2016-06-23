package com.mingrisoft.jframe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;

public class MoreChoices extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = -3130941572848968305L;
    private JPanel contentPane;
    private JPanel hiddenPanel = new JPanel();
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MoreChoices frame = new MoreChoices();
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
    public MoreChoices() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 138, 86);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);
        
        button = new JButton("\u663E\u793A\u6210\u529F\u79D8\u7C4D");
        button.addActionListener(more);
        panel.add(button);
        Dimension panelDimension = new Dimension(button.getPreferredSize().width, button.getPreferredSize().height + 10);
        panel.setPreferredSize(panelDimension);
        
        JLabel label = new JLabel("Java��̴ʵ�");
        hiddenPanel.add(label);
        hiddenPanel.setBorder(BorderFactory.createEtchedBorder());
    }
    
    ActionListener more = new ActionListener() {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            getContentPane().add(hiddenPanel);
            pack();
            button.setText("���سɹ��ؼ�");
            button.removeActionListener(more);
            button.addActionListener(less);
            
        }
    };
    ActionListener less = new ActionListener() {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            remove(hiddenPanel);
            pack();
            button.setText("��ʾ�ɹ��ؼ�");
            button.removeActionListener(less);
            button.addActionListener(more);
            
        }
    };
    private JButton button;
}
