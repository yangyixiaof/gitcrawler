package com.mingrisoft.util;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBHelper implements DBConfig {
    
    public static void saveImage(String name, String type, File image) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            Class.forName(DRIVER);// ��������
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);// ��������
            ps = conn.prepareStatement("insert into picture values (?, ?, ?);");
            ps.setString(1, name);// ����ͼƬ����
            ps.setString(2, type);// ����ͼƬ����
            ps.setString(3, image.getAbsolutePath());// ����ͼƬ�ľ���·��
            ps.executeUpdate();// ִ�б���
        } catch (Exception e) {
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
    }
}
