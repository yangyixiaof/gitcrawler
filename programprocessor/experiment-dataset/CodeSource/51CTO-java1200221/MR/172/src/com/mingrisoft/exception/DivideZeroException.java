package com.mingrisoft.exception;

public class DivideZeroException extends ArithmeticException {// �Զ����쳣��
    private static final long serialVersionUID = 1563874058117161205L;
    
    public DivideZeroException() {
    }// ʵ��Ĭ�Ϲ��췽��
    
    public DivideZeroException(String msg) {
        super(msg);
    }// ʵ���������Ϣ�Ĺ��췽��
}
