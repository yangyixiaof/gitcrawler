package com.mingrisoft.reflection;

import java.awt.Container;
import java.util.TreeSet;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class Test {
    public static void main(String[] args) {
        TreeSet<Class<?>> treeSet = new TreeSet<Class<?>>(new ClassComparator());
        System.out.println("�����������JPanel.class");
        treeSet.add(JPanel.class);// �����������JPanel.class
        System.out.println("�����������JComponent.class");
        treeSet.add(JComponent.class);// �����������JComponent.class
        System.out.println("�����������Container.class");
        treeSet.add(Container.class);// �����������Container.class
        System.out.print("������������һ��Ԫ�أ�");
        System.out.println(treeSet.last());// ������������һ��Ԫ��
    }
}
