package com.zzk;

/**
 * @author ������
 * ����65~90֮�������������
 */
public class RandomBuildLetter {
    public RandomBuildLetter() {
        super();
    }
    
    public int[] getLetter(int letterCounts) {
        int[] letter = new int[letterCounts]; // ���ݲ���������������
        for (int i = 0; i < letterCounts; i++) {
            int a = (int) getRandomLetter(); // ���÷�������65~90֮����������������д��ĸA-Z��ASCIIֵ
            letter[i] = a; // ������������ֵ������
        }
        return letter; // �����������
    }
    
    public int getRandomLetter() {
        int letter = (int) (Math.random() * 26) + 65; // ����65~90֮����������������д��ĸ��ASCIIֵ
        return letter;
    }
}
