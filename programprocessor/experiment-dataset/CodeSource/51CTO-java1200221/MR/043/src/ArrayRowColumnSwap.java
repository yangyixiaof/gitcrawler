public class ArrayRowColumnSwap { // ������
public static void main(String[] args) {
    // ����2ά����
    int arr[][] = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
    System.out.println("���л���ǰ��");
    // ���2ά����
    printArray(arr);
    int arr2[][] = new int[arr.length][arr.length];
    for (int i = 0; i < arr.length; i++) {// ����������������
        for (int j = 0; j < arr[i].length; j++) {
            arr2[i][j] = arr[j][i];
        }
    }
    System.out.println("���л�����");
    // ���2ά����
    printArray(arr);
}
    
private static void printArray(int[][] arr) {
    for (int i = 0; i < arr.length; i++) {// ��������
        for (int j = 0; j < arr.length; j++) {
            System.out.print(arr[i][j] + " ");// �������Ԫ��
        }
        System.out.println();
    }
}
}