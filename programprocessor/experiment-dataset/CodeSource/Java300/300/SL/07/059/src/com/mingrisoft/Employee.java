package com.mingrisoft;

import java.util.Date;		

public class Employee {
    
    private String name;		//Ա��������
    private double salary;		//Ա���Ĺ���
    private Date birthday;		//Ա��������
    
    public String getName() {		//��ȡԱ��������
        return name;
    }
    
    public void setName(String name) {	//����Ա��������
        this.name = name;
    }
    
    public double getSalary() {		//��ȡԱ���Ĺ���
        return salary;
    }
    
    public void setSalary(double salary) {	//����Ա���Ĺ���
        this.salary = salary;
    }
    
    public Date getBirthday() {		//��ȡԱ��������
        return birthday;
    }
    
    public void setBirthday(Date birthday) {		//����Ա��������
        this.birthday = birthday;
    }
    
}
