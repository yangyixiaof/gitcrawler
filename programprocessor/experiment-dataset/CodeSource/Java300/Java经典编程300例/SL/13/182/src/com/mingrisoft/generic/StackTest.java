package com.mingrisoft.generic;

public class StackTest {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<String>();
        System.out.println("��ջ�������ַ�����");
        System.out.println("��ƵѧJava");
        System.out.println("ϸ˵Java");
        System.out.println("Java�����ŵ���ͨ(��2��)");
        stack.push("��ƵѧJava");  //��ջ�������ַ���
        stack.push("ϸ˵Java");   //��ջ�������ַ���
        stack.push("Java�����ŵ���ͨ(��2��)"); //��ջ�������ַ���
        System.out.println("��ջ��ȡ���ַ�����");
        while (!stack.empty()) {
            System.out.println((String) stack.pop());//ɾ��ջ��ȫ��Ԫ�ز��������
        }
    }
}

