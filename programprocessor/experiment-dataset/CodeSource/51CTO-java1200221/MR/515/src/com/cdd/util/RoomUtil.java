package com.cdd.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoomUtil {
    Connection conn = null;
    
    // ��ȡ���ݿ�����
    public Connection getConn() {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver"); // �������ݿ�����
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:jtds:sqlserver://localhost:1433;DatabaseName=db_database21"; // �������ݿ�URL
        String userName = "sa"; // �������ݿ���û���
        String passWord = ""; // �������ݿ�����
        try {
            conn = DriverManager.getConnection(url, userName, passWord); // ��ȡ���ݿ�����
            if (conn != null) {
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn; // ����Connection����
    }
    
public List getRoom() {
    List list = new ArrayList(); // �������ڱ��淵��ֵ��List����
    conn = getConn(); // ��ȡ���ݿ�����
    try {
        Statement staement = conn.createStatement();
        String sql = "select * from tb_room  where (not roomState = '��ס') and ( not roomType = '�ռ�')"; // �����ѯ���ݵ�SQL���
        ResultSet set = staement.executeQuery(sql); // ִ�в�ѯ��䷵�ز�ѯ�����
        while (set.next()) { // ѭ��������ѯ�����
            Room room = new Room();
            room.setRoomDate(set.getString("roomDate"));
            room.setRoomFacility(set.getString("roomFacility"));
            room.setRoomid(set.getInt(1));
            room.setRoomPrice(set.getInt("roomPrice"));
            room.setRoomState(set.getString("roomState"));
            room.setRoomType(set.getString("roomType"));
            list.add(room);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list; // ����List����
}
    
    public static void main(String[] args) {
        RoomUtil util = new RoomUtil();
        List list = util.getRoom();
        System.out.println("��ѯû����ס���ռ�ͷ���");
        for(int i = 0;i<list.size();i++){
            Room room = (Room)list.get(i);
            System.out.println("����� ��"+room.getRoomid()+" , ���ͣ�"+room.getRoomType()+" , �۸�"+room.getRoomPrice()+
                    " , �豸��"+room.getRoomFacility()+" , ״̬��"+room.getRoomState());
        }
    }
}
