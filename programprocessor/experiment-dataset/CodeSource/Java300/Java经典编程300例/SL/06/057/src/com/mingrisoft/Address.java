package com.mingrisoft;

import java.io.Serializable;

public class Address implements Serializable {
    private static final long serialVersionUID = 4983187287403615604L;
    private String state; // ��ʾԱ�����ڵĹ���
    private String province; // ��ʾԱ�����ڵ�ʡ
    private String city; // ��ʾԱ�����ڵ���

    public Address(String state, String province, String city) {// ���ù��췽����ʼ��������
        this.state = state;
        this.province = province;
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public String toString() {// ʹ�õ�ַ���Ա�ʾ��ַ����
        StringBuilder sb = new StringBuilder();
        sb.append("���ң�" + state + ", ");
        sb.append("ʡ��" + province + ", ");
        sb.append("�У�" + city);
        return sb.toString();
    }

}
