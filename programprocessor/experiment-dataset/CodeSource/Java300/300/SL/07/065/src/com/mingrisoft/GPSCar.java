package com.mingrisoft;

import java.awt.Point;

public class GPSCar extends Car implements GPS {
    @Override
    public Point getLocation() {						//�����������ٶ���ȷ��������λ��
        Point point = new Point();
        point.setLocation(super.getSpeed(), super.getSpeed());
        return point;
    }
    @Override
    public String toString() {						//��дtoString()����
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(", ���꣺(" + getLocation().x + ", " + getLocation().y + ")");
        return sb.toString();
    }
}
