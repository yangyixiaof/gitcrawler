package com.mingrisoft.generic;

public class StackTest {
    public static void main(String[] args) {
        Stack stack = new Stack();
        System.out.println("��ջ�������ַ�����");
        System.out.println("��ƵѧJava");
        System.out.println("ϸ˵Java");
        System.out.println("Java�����ŵ���ͨ(��2��)");
        stack.push("��ƵѧJava");
        stack.push("ϸ˵Java");
        stack.push("Java�����ŵ���ͨ(��2��)");
        System.out.println("��ջ��ȡ���ַ�����");
        while (!stack.empty()) {
            System.out.println((String) stack.pop());
        }
    }
}
