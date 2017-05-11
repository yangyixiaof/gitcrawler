package com.mingrisoft.math;

import org.apache.commons.math.MathException;
import org.apache.commons.math.distribution.TDistributionImpl;

public class TDistributionImplTest {
    public static void main(String[] args) throws MathException {
        TDistributionImpl t = new TDistributionImpl(5);// �½�һ�����ɶ�Ϊ5��T�ֲ�
        System.out.println("��ǰT�ֲ������ɶȣ�" + t.getDegreesOfFreedom());
        double upperTail = t.cumulativeProbability(0.7267);
        System.out.println("���������0.7267�����Ŷȣ�" + upperTail);
        System.out.println("����0��ĸ����ܶȣ�" + t.density(0));
        double domain = t.inverseCumulativeProbability(0.75);
        System.out.println("�������Ŷȴ���0.75����ֵ��" + domain);
    }
}
