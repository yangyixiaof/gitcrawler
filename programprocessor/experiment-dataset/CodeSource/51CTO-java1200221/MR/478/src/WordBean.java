
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

/**
 * @author zhuzhen_hua@yahoo.com.cn
 */

public class WordBean {
// word�ĵ�
private Dispatch doc;    
// word���г������
private ActiveXComponent word;    
// ����word�ĵ�����
private Dispatch documents;    
// ѡ���ķ�Χ������
private Dispatch selection;
    
public WordBean() {
    if (word == null) {
        word = new ActiveXComponent("Word.Application");
        word.setProperty("Visible", new Variant(false));
    }
    if (documents == null)
        documents = word.getProperty("Documents").toDispatch();
}    
  
    
    /**
     * �ڵ�ǰ��������ͼƬ
     * 
     * @param imagePath
     *            ͼƬ·��
     */
    
public void insertImage(String imagePath, String docPath, int pos) {
    doc = Dispatch.call(documents, "Open", docPath).toDispatch();   // ����Ӧ��word�ĵ�
    selection = Dispatch.get(word, "Selection").toDispatch();       
    for (int i = 0; i < pos; i++)
        Dispatch.call(selection, "MoveRight");      //������������ƶ���Ӧ��λ��
    Dispatch.call(Dispatch.get(selection, "InLineShapes").toDispatch(),
            "AddPicture", imagePath);               //���ĵ��в���ͼƬ
}
    

    public static void main(String args[]) {
        try {
            WordBean wordBean = new WordBean();           
            wordBean.insertImage("c:\\6.jpg", "E://a.doc", 14);           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}