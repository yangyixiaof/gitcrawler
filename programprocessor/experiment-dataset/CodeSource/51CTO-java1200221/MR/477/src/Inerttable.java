import com.jacob.com.*;
import com.jacob.activeX.ActiveXComponent;

public class Inerttable {
    // word�ĵ�
    private Dispatch doc;
    
    // word���г������
    private ActiveXComponent word;
    
    // ����word�ĵ�����
    private Dispatch documents;
    
    // ѡ���ķ�Χ������
    private Dispatch selection;
    
    private boolean saveOnExit = true;
    
    public Inerttable() {
        ComThread.InitSTA();// ��ʼ��com���̣߳��ǳ���Ҫ����ʹ�ý�����Ҫ���� realease����
        if (word == null) {
            word = new ActiveXComponent("Word.Application");
            word.setProperty("Visible", new Variant(false));
        }
        if (documents == null)
            documents = word.getProperty("Documents").toDispatch();
    }
    
    /**
     * ����һ���µ�word�ĵ�
     */
    public void createNewDocument() {
        doc = Dispatch.call(documents, "Add").toDispatch();
        selection = Dispatch.get(word, "Selection").toDispatch();
    }
    
    /**
     * ��ָ���ĵ�Ԫ������д����
     * 
     * @param tableIndex
     * @param cellRowIdx
     * @param cellColIdx
     * @param txt
     */
    
    public void putTxtToCell(int tableIndex, int cellRowIdx, int cellColIdx,
            String txt) {
        Dispatch tables = Dispatch.get(doc, "Tables").toDispatch(); // ��ȡ�������
        Dispatch table = Dispatch.call(tables, "Item", new Variant(tableIndex))
                .toDispatch(); // Ҫ���ı��
        Dispatch cell = Dispatch.call(table, "Cell", new Variant(cellRowIdx),
                new Variant(cellColIdx)).toDispatch();
        Dispatch.call(cell, "Select");
        Dispatch.put(selection, "Text", txt); // put()�������ñ������
    }
    
    /**
     * �������
     * 
     * @param pos
     *            λ��
     * @param cols
     *            ����
     * @param rows
     *            ����
     */
    
    public void createTable(int numCols, int numRows) {
        Dispatch tables = Dispatch.get(doc, "Tables").toDispatch(); // ��ȡ�������
        Dispatch range = Dispatch.get(selection, "Range").toDispatch(); // ��ȡ�����������
        Dispatch newTable = Dispatch.call(tables, "Add", range,
                new Variant(numRows), new Variant(numCols)).toDispatch(); // �������������
        Dispatch.call(selection, "MoveRight");
    }
    
    /**
     * �ļ���������Ϊ
     * 
     * @param savePath
     *            ��������Ϊ·��
     */
    public void save(String savePath) {
        Dispatch.call(
                (Dispatch) Dispatch.call(word, "WordBasic").getDispatch(),
                "FileSaveAs", savePath);
    }
    
    /**
     * �ر�ȫ��Ӧ��
     */
    public void close() {
        
        if (word != null) {
            Dispatch.call(word, "Quit");
            word = null;
        }
        selection = null;
        documents = null;
    }
    
    public static void main(String args[]) {
        
        Inerttable msWordManager = new Inerttable(); // �����������
        try {
            msWordManager.createNewDocument(); // �½��ĵ�
            msWordManager.createTable(5, 5); // ����5��5�еı��
            msWordManager.putTxtToCell(1, 1, 1, "���"); // ���1�е�1�����������
            msWordManager.putTxtToCell(1, 2, 1, "1"); // ���2�е�1�����������
            msWordManager.putTxtToCell(1, 1, 2, "����"); 
            msWordManager.putTxtToCell(1, 2, 2, "����");
            msWordManager.putTxtToCell(1, 1, 3, "����");
            msWordManager.putTxtToCell(1, 2, 3, "30");
            msWordManager.putTxtToCell(1, 1, 4, "�Ա�");
            msWordManager.putTxtToCell(1, 2, 4, "��");
            msWordManager.putTxtToCell(1, 1, 5, "ѧ��");
            msWordManager.putTxtToCell(1, 2, 5, "����");
            msWordManager.save("c:\\��word�л��Ʊ��.doc"); // ���ñ����ĵ�����
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            msWordManager.close();
        }
        
    }
    
}
