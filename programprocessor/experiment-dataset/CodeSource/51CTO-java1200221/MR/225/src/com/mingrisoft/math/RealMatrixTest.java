package com.mingrisoft.math;

import java.util.Arrays;

import org.apache.commons.math.linear.Array2DRowRealMatrix;
import org.apache.commons.math.linear.RealMatrix;

public class RealMatrixTest {
    public static void main(String[] args) {
        double[][] matrixData1 = { { 1, 2 }, { 3, 4 } };
        RealMatrix m = new Array2DRowRealMatrix(matrixData1);// ���ö�ά�����ʼ������
        System.out.println("����m�е�Ԫ�أ�");
        System.out.println(Arrays.deepToString(m.getData()));// ���ù��������������Ԫ��
        double[][] matrixData2 = { { 1, 2 }, { 3, 4 } };
        RealMatrix n = new Array2DRowRealMatrix(matrixData2);// ���ö�ά�����ʼ������
        System.out.println("����n�е�Ԫ�أ�");
        System.out.println(Arrays.deepToString(n.getData()));// ���ù��������������Ԫ��
        RealMatrix addition = m.add(n);// ���о���ӷ�����
        System.out.println("����addition�е�Ԫ�أ�");
        System.out.println(Arrays.deepToString(addition.getData()));
        RealMatrix subtraction = m.subtract(n);// ���о����������
        System.out.println("����subtraction�е�Ԫ�أ�");
        System.out.println(Arrays.deepToString(subtraction.getData()));
        RealMatrix multiplication = m.multiply(n);// ���о���˷�����
        System.out.println("����multiplication�е�Ԫ�أ�");
        System.out.println(Arrays.deepToString(multiplication.getData()));
        RealMatrix transposition = m.multiply(n);// ���о���ת������
        System.out.println("����mת�ú��¾����е�Ԫ�أ�");
        System.out.println(Arrays.deepToString(transposition.getData()));
    }
}
