import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;

public class RandomSortArray {
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet<Integer>();// ����TreeSet���϶���
        Random ran = new Random();// �������������
        int count = 0;// ���������������
        while (count < 10) {// ѭ�����������
            boolean succeed = set.add(ran.nextInt(100));// Ϊ�����������
            if (succeed)// �ۼӳɹ���ӵ����������ֵ�����
                count++;
        }
        int size = set.size();// ��ȡ���ϴ�С
        Integer[] array = new Integer[size];// ����ͬ�ȴ�С������
        Integer[] ddd = set.toArray(array);// ��ȡ�����е�����
        System.out.println("���ɵ��ظ���������������£�");
        for (int value : array) {// ���������������
            System.out.print(value + "   ");
        }
    }
}
