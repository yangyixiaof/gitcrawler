package com.mingrisoft;
public class DivideZeroException extends ArithmeticException {			// �Զ����쳣��
    public DivideZeroException() {
    }															// ʵ��Ĭ�Ϲ��췽��
    public DivideZeroException(String msg) {
        super(msg);
    }																// ʵ���������Ϣ�Ĺ��췽��
}
