import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;
public class FormatNumber {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);// ������ע������ɨ����
        System.out.println("������һ�����֣�");
        double number = scan.nextDouble();// ��ȡ�û���������
        System.out.println("��������Locale������³�����Ϊ��ʽ������Ĺ������������ò�ͬ�Ļ��Ҹ�ʽ��");
        // ������ʽ������
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.CHINA);
        // �����ʽ�����Ҹ�ʽ
        System.out.println("Locale.CHINA��" + format.format(number));
        format = NumberFormat.getCurrencyInstance(Locale.US);
        System.out.println("Locale.US��" + format.format(number));
        format = NumberFormat.getCurrencyInstance(Locale.ENGLISH);
        System.out.println("Locale.ENGLISH��" + format.format(number));
        format = NumberFormat.getCurrencyInstance(Locale.TAIWAN);
        System.out.println("Locale.TAIWAN��" + format.format(number));
    }
}
