package com.mingrisoft;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;

public class DBHelper implements DBConfig {

    public static ImageIcon retrievePicture(Picture picture) {
        try {
            Class.forName(DRIVER);// �������ݿ�����
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // ����SQL���
        String sql = "select picturefile from tb_picture where id = " + picture.getId() + " and picturename = '" + picture.getPictureName() + "'";
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);// ������ݿ�����
            stat = conn.createStatement();// ���������
            rs = stat.executeQuery(sql);// ��ò�ѯ���
            if (rs.next()) {
                Blob pictureFile = rs.getBlob("picturefile");// ���Blob����
                return new ImageIcon(pictureFile.getBytes(1, (int) pictureFile.length()));// ����ͼ��
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
