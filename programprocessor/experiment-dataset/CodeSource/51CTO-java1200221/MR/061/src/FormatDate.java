import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class FormatDate {
    public static void main(String[] args) {
        Date date = new Date();
        DateFormat formater = DateFormat.getDateInstance(DateFormat.FULL,
                Locale.CHINA);
        // �й�����
        String string = formater.format(date);
        System.out.println("�й����ڣ�\t"+string);
        // ���ô�����
        formater = DateFormat.getDateInstance(DateFormat.FULL,
                Locale.CANADA);
        System.out.println("���ô����ڣ�\t"+formater.format(date));
        // �ձ�����
        formater = DateFormat.getDateInstance(DateFormat.FULL,
                Locale.JAPAN);
        System.out.println("�ձ����ڣ�\t"+formater.format(date));
        // ��������
        formater = DateFormat.getDateInstance(DateFormat.FULL,
                Locale.FRANCE);
        System.out.println("�������ڣ�\t"+formater.format(date));
        // �¹�����
        formater = DateFormat.getDateInstance(DateFormat.FULL,
                Locale.GERMAN);
        System.out.println("�¹����ڣ�\t"+formater.format(date));
        // ���������
        formater = DateFormat.getDateInstance(DateFormat.FULL,
                Locale.ITALIAN);
        System.out.println("��������ڣ�\t"+formater.format(date));
    }
}
