package com.tl.juc;

@FunctionalInterface
interface Foo{
    int add(int x,int y);

    //默认方法   jdk1.8出现的
    default int div(int x,int y){
        return x/y;
    }
    //静态方法
    static int mul(int x,int y){
        return x*y;
    }

}

/**
 *
 *
 * 拷贝小括号, 写死右箭头,落地大括号
 */
public class LambdaExpressDemo {
    public static void main(String[] args) {
        /*new Foo() {
            @Override
            public int add(int x, int y) {
                System.out.println("匿名内部类的写法....");
                return x+y;
            }
        };*/

        Foo foo = (x,y) -> {
            System.out.println("*****this is lambdaExpressDemo");
            return x+y;
        };

        System.out.println(foo.add(1, 2));
        System.out.println(foo.div(10, 2));
        System.out.println(Foo.mul(2, 6));


    }


}
