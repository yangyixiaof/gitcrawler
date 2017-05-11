package com.lzw;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class InfoServer {
    
    /**
     * ����Socket���ӵ��߳�
     * 
     * @author ����ξ
     */
    private static final class SocketThread extends Thread {
        private static final String TEXT_FILE_PATH = "/com/textFile/";
        private final Socket socket;
        
        private SocketThread(Socket socket) {
            this.socket = socket;
        }
        
        public void run() {
            try {
                // ����Socket������ɨ����
                final Scanner scanner = new Scanner(socket.getInputStream());
                // ��������ı��ļ����ļ��ж���
                File dir = new File(getClass().getResource(TEXT_FILE_PATH)
                        .toURI());
                String[] files = dir.list();// ��ȡ�ļ��б�����
                ObjectOutputStream dout = new ObjectOutputStream(socket
                        .getOutputStream());// �������������
                dout.writeObject(files);// ���ļ��б����������socket
                while (scanner.hasNext()) {// ����socket��������ɨ��������
                    String line = scanner.nextLine();// ��ȡһ���ı�
                    InputStream is = getClass().getResourceAsStream(
                            TEXT_FILE_PATH + line);// �����ı��ļ�������
                    ZipOutputStream zout = new ZipOutputStream(socket
                            .getOutputStream());// ����socket��ZIP�����
                    byte[] data = new byte[1024];// �������ݻ���
                    int readNum;
                    // ΪZIP��������һ��ѹ����Ŀ
                    zout.putNextEntry(new ZipEntry("one"));
                    while (is != null && (readNum = is.read(data)) > 0) {
                        zout.write(data, 0, readNum);// ��ZIP��д����
                    }
                    zout.closeEntry();// �ر�ѹ����Ŀ
                    is.close();// �ر��ļ�������
                }
                scanner.close();// �ر�������ɨ����
                socket.close();// �ر�socket
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(1598);// ����socket����������
        System.out.println("�������Ѿ�������������");// �����ʾ��Ϣ
        while (!ss.isClosed()) {
            final Socket socket = ss.accept();
            new SocketThread(socket).start();
        }
    }
}
