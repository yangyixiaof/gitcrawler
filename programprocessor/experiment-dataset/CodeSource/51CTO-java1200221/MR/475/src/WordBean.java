import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import java.io.*;

/**
 * @author zhuzhen_hua@yahoo.com.cn
 */

public class WordBean {
    
    private Dispatch doc; // word�ĵ�
    private ActiveXComponent word;// word���г������
    private Dispatch documents; // ����word�ĵ�����
    private Dispatch selection; // ѡ���ķ�Χ������
    
    public WordBean() {
        if (word == null) {
            word = new ActiveXComponent("Word.Application"); // ����word
            word.setProperty("Visible", new Variant(true)); // ����wordΪ����״̬
        }
        if (documents == null)
            documents = word.getProperty("Documents").toDispatch(); // ��ȡ����ֵ
    }
    
    /**
     * ����һ���µ�word�ĵ�
     */
    
    public void createNewDocument() {
        doc = Dispatch.call(documents, "Add").toDispatch(); // ����com/dll����
        selection = Dispatch.get(word, "Selection").toDispatch(); // ��ȡcom���������ֵ
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
     * �ڵ�ǰ���������ַ���
     * 
     * @param newText
     *            Ҫ��������ַ���
     */
    
    public void insertText(String newText) {
        Dispatch.put(selection, "Text", newText); // ��������ֵ
    }
    
    public static void main(String args[]) {
        
    }
    
}