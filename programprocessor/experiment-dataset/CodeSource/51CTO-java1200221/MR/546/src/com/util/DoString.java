package com.util;

public class DoString {
    private String getstr;
    private String checkstr;
    
    public DoString() {
    }
    
    public void setGetstr(String getstr) {
        this.getstr = getstr;
        dostring();
    }
    
    public String getGetstr() {
        return this.getstr;
    }
    
    public String getCheckstr() {
        return this.checkstr;
    }
    
    public void dostring() {
        this.checkstr = this.getstr;
        this.checkstr = this.checkstr.replaceAll("&", "&amp;"); // �滻�ַ�����
        this.checkstr = this.checkstr.replaceAll(";", "");
        this.checkstr = this.checkstr.replaceAll("'", "");
        this.checkstr = this.checkstr.replaceAll("<", "&lt;");
        this.checkstr = this.checkstr.replaceAll(">", "&gt;");
        this.checkstr = this.checkstr.replaceAll("--", "");
        this.checkstr = this.checkstr.replaceAll("\"\"", "&quot;");
        this.checkstr = this.checkstr.replaceAll("/", "");
        this.checkstr = this.checkstr.replaceAll("%", "");
        
    }
}
