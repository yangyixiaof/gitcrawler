package com.mingrisoft;
public class Test {
    public static void main(String[] args) {
        Employee employee = new Employee();	//����Employee����
        System.out.println(employee.getInfo());	//���Employee�����getInfo()��������ֵ
        Manager manager = new Manager();		//����Manager����
        System.out.println(manager.getInfo());	//���Manager�����getInfo()��������ֵ
    }
}
