package com.mingrisoft;

import java.io.Serializable;

public class Student implements Serializable {  // ���л�������
    private String id;                  // ��ĳ�Ա����
    private String name;// ��ĳ�Ա����
    public String getId() { // ��Ա������getter����
        return id;
    }
    public void setId(String id) {// ��Ա������setter����
        this.id = id;
    }
    public String getName() {// ��Ա������getter����
        return name;
    }
    public void setName(String name) {// ��Ա������setter����
        this.name = name;
    }
}
