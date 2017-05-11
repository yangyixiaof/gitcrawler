package com.mingrisoft.cat;

import java.awt.Color;

import org.apache.commons.lang.builder.EqualsBuilder;

public class Cat {
    private String name;
    private int age;
    private double weight;
    private Color color;
    
    public Cat(String name, int age, double weight, Color color) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.color = color;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Cat cat = (Cat) obj;
        return new EqualsBuilder().append(name, cat.name).append(age, cat.age).append(weight, cat.weight).append(color, cat.color).isEquals();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("���֣�" + name + "\n");
        sb.append("���䣺" + age + "\n");
        sb.append("������" + weight + "\n");
        sb.append("��ɫ��" + color + "\n");
        return sb.toString();
    }
}
