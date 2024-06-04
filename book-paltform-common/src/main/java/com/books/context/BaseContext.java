package com.books.context;

public class BaseContext {

    // 每次发送的http请求都会产生一个threadlocal的内存对象（和线程绑定），然后使用set的方式让这个对象存放当前请求者的id
    public static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }

    public static Long getCurrentId() {
        return threadLocal.get();
    }

    public static void removeCurrentId() {
        threadLocal.remove();
    }

}
