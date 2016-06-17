import java.io.*;
import com.jacob.*;
import com.jacob.activeX.*;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class WordToHtml {
    /**
     * WORD转HTML
     * 
     * @param docfilePath
     *            WORD文件全路径
     * @param htmlfilePath
     *            转换后HTML存放路径
     */
    
    public void wordToHtml(String docfilePath, String htmlfilePath) {
        ActiveXComponent app = new ActiveXComponent("Word.Application"); // 启动word
        try {
            app.setProperty("Visible", new Variant(false)); // 设置word为不可视
            Dispatch dispatch = app.getProperty("Documents").toDispatch(); // 读取文档属性值
            Dispatch doc = Dispatch.invoke(
                    dispatch,
                    "Open",
                    Dispatch.Method,
                    new Object[] { docfilePath, new Variant(false),
                            new Variant(true) }, new int[1]).toDispatch(); // 功能调用
            Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[] {
                    htmlfilePath, new Variant(8) }, new int[1]); // 以html格式保存到临时文件
            Variant f = new Variant(false);
            Dispatch.call(doc, "Close", f); // 将文档关闭，并将其设置为不可视
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  
    public static void main(String[] args) {
        WordToHtml wth = new WordToHtml(); // 创建本类对象
        wth.wordToHtml("c:\\向word中绘制表格.doc", "c:\\向word中绘制表格.html"); // 调用格式转换方法
      
    }
    
}
