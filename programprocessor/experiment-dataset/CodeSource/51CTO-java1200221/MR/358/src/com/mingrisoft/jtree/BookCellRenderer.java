package com.mingrisoft.jtree;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

public class BookCellRenderer implements TreeCellRenderer {
    
    private JLabel titleLabel = new JLabel();
    private JLabel pressLabel = new JLabel();
    private JLabel publicationDateLabel = new JLabel();
    private JLabel booksCategoryLabel = new JLabel();
    private JLabel priceLabel = new JLabel();
    private JPanel panel = new JPanel(new GridLayout(5, 1, 5, 5));
    
    public BookCellRenderer() {
        titleLabel.setForeground(Color.RED);
        titleLabel.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        panel.add(titleLabel);
        pressLabel.setForeground(Color.GREEN);
        pressLabel.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        panel.add(pressLabel);
        publicationDateLabel.setForeground(Color.BLUE);
        publicationDateLabel.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        panel.add(publicationDateLabel);
        booksCategoryLabel.setForeground(Color.ORANGE);
        booksCategoryLabel.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        panel.add(booksCategoryLabel);
        priceLabel.setForeground(Color.PINK);
        priceLabel.setFont(new Font("΢���ź�", Font.PLAIN, 16));
        panel.add(priceLabel);
        panel.setPreferredSize(new Dimension(350, 110));
    }
    
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        
        Object userObject = ((DefaultMutableTreeNode) value).getUserObject();
        if (userObject instanceof Book) {
            Book book = (Book) userObject;
            titleLabel.setText("������" + book.getTitle());
            pressLabel.setText("�����磺" + book.getPress());
            publicationDateLabel.setText("����ʱ�䣺" + book.getPublicationDate());
            booksCategoryLabel.setText("�������" + book.getBooksCategory());
            priceLabel.setText("���ۣ�" + book.getPrice() + "Ԫ");
            return panel;
        } else {
            return new DefaultTreeCellRenderer().getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        }
    }
    
}
