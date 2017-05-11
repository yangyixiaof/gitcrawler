package com.mingrisoft;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBHelper implements DBConfig {

    public static boolean savePicture(Picture picture) {
        try {
            Class.forName(DRIVER);// �������ݿ�����
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        FileInputStream in = null;
        String sql = "insert into tb_picture (picturename, picturefile) values (?, ?);";// ����SQL���
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            in = new FileInputStream(picture.getPictureFile());// ����ļ�������
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);// ������ݿ�����
            ps = conn.prepareStatement(sql);// ���Ԥ�������
            ps.setString(1, picture.getPictureName());// �����ļ���
            ps.setBlob(2, in);// �����ļ�������
            ps.execute();// ��������
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
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
        return false;
    }
}
