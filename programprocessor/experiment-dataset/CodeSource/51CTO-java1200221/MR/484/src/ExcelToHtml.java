import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class ExcelToHtml {
    
    /**
     * EXCELתHTML
     * 
     * @param xlsfilePath
     *            EXCEL�ļ�ȫ·��
     * @param htmlfilePath
     *            ת����HTML���·��
     */
    
public void excelToHtml(String xlsfilePath, String htmlfilePath) {
    ActiveXComponent app = new ActiveXComponent("Excel.Application"); // ����excel
try {
    app.setProperty("Visible", new Variant(false));                //����Excel����Ϊ���ɼ�
    Dispatch excels = app.getProperty("Workbooks").toDispatch();
    Dispatch excel = Dispatch.invoke(
            excels,
            "Open",
            Dispatch.Method,
            new Object[] { xlsfilePath, new Variant(false),
                    new Variant(true) }, new int[1]).toDispatch(); // ���ܵ���
    Dispatch.invoke(excel, "SaveAs", Dispatch.Method, new Object[] {
            htmlfilePath, new Variant(44) }, new int[1]); // ��html��ʽ���浽��ʱ�ļ�
    Variant f = new Variant(false);
    Dispatch.call(excel, "Close", f); // �ر�excel�ļ�
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public static void main(String[] args) {
    ExcelToHtml eth = new ExcelToHtml();        //�����������
    eth.excelToHtml("c:\\JAVA �ܼƻ�������������.xls", "c:\\JAVA �ܼƻ�������������.html"); //���ý�Excelת��ΪHtml��ʽ����
}

}
