package com.mingrisoft;
public class Student {
    private int id;			//��ʾѧ�������
    private String name;		//��ʾѧ��������
    private boolean male;	//��ʾѧ�����Ա�
    private double account;	//��ʾѧ�����˻����
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isMale() {
		return male;
	}
	public void setMale(boolean male) {
		this.male = male;
	}
	public double getAccount() {
		return account;
	}
	public void setAccount(double account) {
		this.account = account;
	}

}
