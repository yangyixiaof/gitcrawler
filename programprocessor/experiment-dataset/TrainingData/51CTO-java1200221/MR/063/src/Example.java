import java.util.Date;
import java.util.Locale;

public class Example {
    public static void main(String[] args) {
        Date today = new Date();
        // ��ʽ������ַ���Ϊ�·ݵ�Ӣ����д
        String a = String.format(Locale.US, "%tb", today);
        System.out.println("��ʽ������ַ���Ϊ�·ݵ�Ӣ����д: " + a);
        // ��ʽ������ַ���Ϊ�·ݵ�Ӣ��ȫд
        String b = String.format(Locale.US, "%tB", today);
        System.out.println("��ʽ������ַ���Ϊ�·ݵ�Ӣ����д: " + b);
        // ��ʽ������ַ���Ϊ���ڣ��磺����һ��
        String c = String.format("%ta", today);
        System.out.println("�¸�ʽ������ַ���Ϊ����: " + c);
        // ��ʽ������ַ���Ϊ���ڣ��磺����һ��
        String d = String.format("%tA", today);
        System.out.println("��ʽ������ַ���Ϊ����: " + d);
        // ��ʽ������ַ���Ϊ4λ�����ֵ
        String e = String.format("%tY", today);
        System.out.println("��ʽ������ַ���Ϊ4λ�����ֵ: " + e);
        // ��ʽ������ַ���Ϊ2λ�����ֵ
        String f = String.format("%ty", today);
        System.out.println("��ʽ������ַ���Ϊ2λ�����ֵ: " + f);
        // ��ʽ������ַ���Ϊ2λ���·�ֵ
        String g = String.format("%tm", today);
        System.out.println("��ʽ������ַ���Ϊ2λ���·�ֵ: " + g);
        // ��ʽ������ַ���Ϊ2λ������ֵ
        String h = String.format("%td", today);
        System.out.println("��ʽ������ַ���Ϊ2λ������ֵ: " + h);
        // ��ʽ������ַ���Ϊ1λ������ֵ
        String i = String.format("%te", today);
        System.out.println("��ʽ������ַ���Ϊ1λ������ֵ: " + i);
    }
}
