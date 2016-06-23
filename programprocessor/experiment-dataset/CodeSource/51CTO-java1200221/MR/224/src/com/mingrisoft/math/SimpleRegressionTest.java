package com.mingrisoft.math;

import org.apache.commons.math.stat.regression.SimpleRegression;

public class SimpleRegressionTest {
    public static void main(String[] args) {
        double[][] data = { { 54, 61 }, { 66, 80 }, { 68, 62 }, { 76, 86 }, { 78, 84 }, { 82, 76 }, { 85, 85 }, { 87, 82 }, { 90, 88 }, { 94, 82 }, { 90, 88 }, { 94, 96 } };
        SimpleRegression regression = new SimpleRegression();
        regression.addData(data);// ����Ҫ����������
        System.out.println("б���ǣ�" + regression.getSlope());
        System.out.println("б�ʱ�׼���ǣ�" + regression.getSlopeStdErr());
        System.out.println("�ؾ��ǣ�" + regression.getIntercept());
        System.out.println("�ؾ��׼���ǣ�" + regression.getInterceptStdErr());
        System.out.println("���ƽ�����ǣ�" + regression.getSumSquaredErrors());
    }
}
