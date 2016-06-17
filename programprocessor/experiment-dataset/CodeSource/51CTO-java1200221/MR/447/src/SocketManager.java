import java.io.*;
import java.net.*;
import java.util.*;

public class SocketManager extends ArrayList {
    synchronized void add(Socket socket) { // ��������׽��ַ���
        super.add(socket);
    }
    
    synchronized void delete(Socket socket) { // ɾ���׽��ַ���
        super.remove(socket);
    }
    
    synchronized void sendClientConut() { // �����ǰ��������
        String info = "��������Ϊ��" + size();
        try {
            File file = new File("c://count.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file, true))); // ����BufferedWriter����
            String dates = String.format("%tF %<tT", new Date()); // �����ڽ��и�ʽ��
            out.write(dates + ":��������Ϊ" + size()); // ���ļ���д����
            out.newLine(); // �½�һ��
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(info);
        writeAll(info);
    }
    
    synchronized void writeAll(String str) { // ʹ���׽���������������Ϣ
        PrintWriter writer = null;
        Socket socket;
        for (int i = 0; i < size(); i++) { // ѭ�������׽��ּ���
            socket = (Socket) get(i); // ��ȡָ���׽���
            try {
                writer = new PrintWriter(socket.getOutputStream(), true); // ���������
                if (writer != null)
                    writer.println(str); // ͨ�������д����Ϣ
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
