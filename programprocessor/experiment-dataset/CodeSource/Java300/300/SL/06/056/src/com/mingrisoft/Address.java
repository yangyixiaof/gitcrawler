package com.mingrisoft;

public class Address implements Cloneable {
    private String state; // ��ʾԱ�����ڵĹ���
    private String province;// ��ʾԱ�����ڵ�ʡ
    private String city; // ��ʾԱ�����ڵ���

    public Address(String state, String province, String city) {// ���ù��췽�����г�ʼ��
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

    @Override
    public String toString() {// ��дtoString()����
        StringBuilder sb = new StringBuilder();
        sb.append("���ң�" + state + ", ");
        sb.append("ʡ��" + province + ", ");
        sb.append("�У�" + city);
        return sb.toString();
    }

    @Override
    protected Address clone() {// ʵ��ǳ��¡
        Address address = null;
        try {
            address = (Address) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return address;
    }
}
