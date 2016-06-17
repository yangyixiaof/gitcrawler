package com.mingrisoft.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Agency implements InvocationHandler {
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理人员在卖房子");// 用来处理代理类
        return null;
    }
}
