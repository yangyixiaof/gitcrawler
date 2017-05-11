package com.mingrisoft.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBHelper implements DBConfig {
    
    public static void saveImage(String name, String type, File image) {
        FileInputStream in = null;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            in = new FileInputStream(image);// ����image��������FileInputStream����
            Class.forName(DRIVER);// ������������
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);// ��������
            ps = conn.prepareStatement("insert into images values (?, ?, ?);");
            ps.setString(1, name);// ����ͼƬ����
            ps.setString(2, type);// ����ͼƬ����
            ps.setBinaryStream(3, in, (int) image.length());// ����ͼƬ
            ps.executeUpdate();// ִ�б���
        } catch (Exception e) {// �����쳣
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
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
