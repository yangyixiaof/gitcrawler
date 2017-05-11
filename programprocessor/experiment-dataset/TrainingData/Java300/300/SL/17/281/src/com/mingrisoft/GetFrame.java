package com.mingrisoft;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GetFrame {
	public Connection Con() {
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			Connection Con = DriverManager
					.getConnection(
							"jdbc:jtds:sqlserver://localhost:1433;DatabaseName=db_database17",
							"sa", "");
			System.out.println("���ӳɹ�");
			return Con;
		} catch (Exception e) {
			return null;
		}
	}

	// ����鿴���ݿ����������ݱ���
	public List GetRs() {
		try {
			List list = new ArrayList();
			String[] tableType = { "TABLE" }; // ָ��Ҫ���в�ѯ�ı�����
			Connection conn = Con(); // ���������ݿ⽨�����ӷ���
			DatabaseMetaData databaseMetaData = conn.getMetaData(); // ��ȡDatabaseMetaDataʵ��
			ResultSet resultSet = databaseMetaData.getTables(null, null, "%",
					tableType);// ��ȡ���ݿ����������ݱ���
			while (resultSet.next()) { // ��������
				String tableName = resultSet.getString("TABLE_NAME");
				list.add(tableName);
			}
			return list;
		} catch (SQLException e) {
			System.out.println("��¼������ȡʧ�ܣ�");
			return null;
		}
	}

	public ResultSet GetRs(String SQL) {
		try {
			Connection Con = Con(); // ��ȡ���ݿ�����
			Statement Smt = Con
					.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE); // ��ȡStatement����
			ResultSet Rs = null;

			Rs = Smt.executeQuery(SQL); // ִ�в�ѯ��䣬��ȡ��ѯ�����

			return Rs;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("��¼������ȡʧ�ܣ�");
			return null;
		}
	}

	public List getMessage(String tableName) {
		List list = new ArrayList(); // ���屣�淵��ֵ��List����

		String SQL = "SELECT syscolumns.ColId AS [�ֶα��],syscolumns.name AS [�ֶ���], syscolumns.length AS [�ֶγ���], "
				+ "systypes.name AS [�ֶ����], "
				+ "sys.extended_properties.[value] AS [����], "
				+ "CASE syscolumns.isnullable WHEN '1' THEN 'Y' ELSE 'N' END AS [�Ƿ�Ϊ��], "
				+ "ISNULL(COLUMNPROPERTY(syscolumns.id, syscolumns.name, 'Scale'), 0) AS [С��λ��], "
				+ "syscomments.text AS [Ĭ��ֵ], "
				+ "CASE WHEN EXISTS (SELECT 1 FROM sysobjects WHERE xtype = 'PK' AND name IN "
				+ "(SELECT name FROM sysindexes WHERE indid IN "
				+ "(SELECT indid "
				+ "FROM sysindexkeys "
				+ "WHERE id = syscolumns.id AND colid = syscolumns.colid))) "
				+ "THEN '��' ELSE '' END AS [����] "
				+ "FROM syscolumns "
				+ "INNER JOIN systypes "
				+ "ON syscolumns.xtype = systypes.xtype "
				+ "LEFT JOIN sysobjects ON syscolumns.id = sysobjects.id "
				+ "LEFT OUTER JOIN sys.extended_properties ON "
				+ "( sys.extended_properties.minor_id = syscolumns.colid "
				+ "AND sys.extended_properties.major_id = syscolumns.id) "
				+ "LEFT OUTER JOIN syscomments ON syscolumns.cdefault = syscomments.id "
				+ "WHERE (systypes.name <> 'sysname') "
				+ "AND syscolumns.id IN (SELECT id FROM SYSOBJECTS WHERE xtype = 'U' AND NAME = '"
				+ tableName + "')";
		ResultSet res = GetRs(SQL); // ����ִ��SQL��䷽��
		ResultSetMetaData Rsmd; // ��ȡResultSetMetaData����
		try {
			Rsmd = res.getMetaData(); // ʵ���� ResultSetMetaData����
			while (res.next()) { // ѭ��������ѯ�����
				Student student = new Student(); // �����������ݿ�����JavaBean����
				student.setId(res.getString("�ֶα��")); // ���ö�������
				student.setName(res.getString("�ֶ���"));
				student.setType(res.getString("�ֶ����"));
				student.setAcquiescence(res.getString("Ĭ��ֵ"));
				student.setDepict(res.getString("����"));
				student.setDigit(res.getString("С��λ��"));
				student.setLength(res.getString("�ֶγ���"));
				student.setIfNull(res.getString("�Ƿ�Ϊ��"));
				list.add(student); // ��������ӵ�List������
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list; // ����List����
	}

}
