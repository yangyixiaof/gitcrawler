package com.cdd.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GetFrame {
    public Connection Con() {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            Connection Con = DriverManager
                    .getConnection(
                            "jdbc:jtds:sqlserver://localhost:1433;DatabaseName=db_database22",
                            "sa", "");
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
    
    public ResultSet GetRs(final String SQL) {
        try {
            Connection Con = Con(); // ��ȡ���ݿ�����
            Statement Smt = Con
                    .createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                            ResultSet.CONCUR_UPDATABLE); // ��ȡStatement����
            ResultSet Rs = Smt.executeQuery(SQL); // ִ�в�ѯ��䣬��ȡ��ѯ�����
            return Rs;
        } catch (SQLException e) {
            System.out.println("��¼������ȡʧ�ܣ�");
            return null;
        }
    }
    
public List getMessage(String tableName) {
    List list = new ArrayList(); // ���屣�淵��ֵ��List����
    String SQL = " Select case when c.colid=1 then  o.name end ����,"
            + " c.ColId �ֶα��,c.name �ֶ���,c.length �ֶγ���,t.name �ֶ����,"
            + " p.value ����,case when c.isnullable=0 then '1' end �Ƿ�Ϊ��,"
            + " c.scale С��λ��,REPLACE (REPLACE (REPLACE (m.text,'(',''),')',''),'''','') Ĭ��ֵ,"
            + " case when ("
            + " Select Count(*) From SysObjects where name in ("
            + " Select name From Sysindexes Where id=c.id and indid in ("
            + " Select indid From Sysindexkeys  where id=c.id and colid in ("
            + " Select colid From Syscolumns where id=c.id and colid=c.colid))) and xtype='pk')>0"
            + " then '1' end �Ƿ�Ϊ����"
            + " From Sysobjects o"
            + " left join Syscolumns c on o.id=c.id"
            + " left join Sysproperties p on o.id=p.id and c.colid=p.smallid"
            + " left join Systypes t on t.xtype=c.xtype"
            + " left join Syscomments m on m.id=c.cdefault"
            + " where (o.xtype='u' or o.xtype='v') and o.status>0 and o.name='"
            + tableName + "'" + " order by o.name,c.colid"; // �����ѯSQL���
    ResultSet res = GetRs(SQL);                 //����ִ��SQL��䷽��
    ResultSetMetaData Rsmd;                     //��ȡResultSetMetaData����
    try {
        Rsmd = res.getMetaData();               //ʵ���� ResultSetMetaData����
        while (res.next()) {                    //ѭ��������ѯ�����
            Student student = new Student();    //�����������ݿ�����JavaBean����
            student.setId(res.getString("�ֶα��"));       //���ö�������
            student.setName(res.getString("�ֶ���"));
            student.setType(res.getString("�ֶ����"));
            student.setAcquiescence(res.getString("Ĭ��ֵ"));
            student.setDepict(res.getString("����"));
            student.setDigit(res.getString("С��λ��"));
            student.setLength(res.getString("�ֶγ���"));
            student.setIfNull(res.getString("�Ƿ�Ϊ��"));
            list.add(student);                  //��������ӵ�List������
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;            //����List����
}
    
}
