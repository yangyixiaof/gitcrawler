package com.mingrisoft.math;

import java.util.Arrays;
import java.util.Random;

import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;

public class DescriptiveStatisticsTest {
    public static void main(String[] args) {
        DescriptiveStatistics ds = new DescriptiveStatistics(10);
        for (int i = 0; i < 10; i++) {
            ds.addValue(new Random().nextInt(10));// �����ݼ������10��С��10���������
        }
        System.out.println("���ݼ��е�ȫ��Ԫ�أ�");
        System.out.println(Arrays.toString(ds.getValues()));
        System.out.println("���ݼ�������ƽ�����ǣ�" + ds.getMean());
        System.out.println("���ݼ��ļ���ƽ�����ǣ�" + ds.getGeometricMean());
        System.out.println("���ݼ��ķ����ǣ�" + ds.getVariance());
        System.out.println("���ݼ��ı�׼�����ǣ�" + ds.getStandardDeviation());
        System.out.println("���ݼ��ĺ��ǣ�" + ds.getSum());
        System.out.println("���ݼ���ƽ�����ǣ�" + ds.getSumsq());
        System.out.println("���ݼ������ֵ�ǣ�" + ds.getMax());
        System.out.println("���ݼ�����Сֵ�ǣ�" + ds.getMin());
        System.out.println("���ݼ�����λ���ǣ�" + ds.getPercentile(50));
        System.out.println("���ݼ���ƫ���ǣ�" + ds.getSkewness());
        System.out.println("���ݼ��ķ���ǣ�" + ds.getKurtosis());
    }
}
