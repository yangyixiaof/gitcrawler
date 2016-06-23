package com.mingrisoft.enums;

public enum Size {
    SMALL("����С��ƥ��"), MEDIUM("�����к�ƥ��"), LARGE("���Ǵ��ƥ��");
    private String description;
    
    private Size(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
    
    public static void main(String[] args) {
        for (Size size : Size.values()) {
            System.out.println(size + ":" + size.getDescription());
        }
    }
}
