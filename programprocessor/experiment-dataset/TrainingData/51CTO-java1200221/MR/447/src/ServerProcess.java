import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class ServerProcess {
    private SocketManager socketMan = new SocketManager(); // ����SocketManager����    
    void getServer() { // �����׽��ַ���
        try {
            ServerSocket serverSocket = new ServerSocket(7777); // ʵ����ServerSocket����
            System.out.println("�������׽����Ѵ���");
            while (true) {
                Socket socket = serverSocket.accept(); // �ȴ�����
                new write_Thread(socket).start(); // �����߳�
                socketMan.add(socket); // ��������׽��ַ���
                socketMan.sendClientConut(); // ����ǰ���������              
             }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
    class write_Thread extends Thread { // �ڲ��࣬�����߳��н��û������������
        Socket socket = null; // ����Socket����
        private BufferedReader reader; // ����BufferedReader����
        private PrintWriter writer; // ����PrintWriter����        
        public write_Thread(Socket socket) { // ���췽��
            this.socket = socket;
        }        
        public void run() {
            try {
                reader = new BufferedReader(new InputStreamReader(socket
                        .getInputStream()));
                writer = new PrintWriter(socket.getOutputStream(), true);
                String msg;
                while ((msg = reader.readLine()) != null) {
                    socketMan.writeAll(msg); // ���ͻ��������Ϣд������
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }    
    public static void main(String[] args) { // ������
        ServerProcess server = new ServerProcess(); // �����������
        server.getServer();
    }
}
