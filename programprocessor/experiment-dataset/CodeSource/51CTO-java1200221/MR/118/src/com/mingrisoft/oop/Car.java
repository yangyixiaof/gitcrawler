package com.mingrisoft.oop;

public class Car {
    
    private String name;
    private double speed;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public double getSpeed() {
        return speed;
    }
    
    public void setSpeed(double speed) {
        this.speed = speed;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("����:" + name + ", ");
        sb.append("�ٶȣ�" + speed + "ǧ��/Сʱ");
        return sb.toString();
    }
}
