package com.tl.juc;

/**
 * @author tanglei
 *
 *
 * 双亲委派模式  +  沙箱安全机制
 */
public class Hello {

    public static void main(String[] args){
        Hello hello = new Hello();

        //应用程序类加载器  AppClassLoader
        System.out.println(hello.getClass().getClassLoader());

        //扩展类加载器      ExtensionClassLoader
        System.out.println(hello.getClass().getClassLoader().getParent());

        //启动类家在趣      BootStrap  c++写  Java无法去调用       所以获取到的是空
        System.out.println(hello.getClass().getClassLoader().getParent().getParent());
    }

}
