package insideclass;
//使用成员内部类有两种方法
//1.间接方式：在外部类的方法当中，使用内部类，然后main只是调用外部类方法即可
//2.直接方式：类名称 对象名 = new 类名称();
//          外部类名称.内部类名称 对象名 = new 外部类名称().new 内部类名称();

public class main {
    public static void main(String[] args) {
        outside A=new outside();
        A.method2();

        outside.inside B=new outside().new inside();
        B.method1();
    }
}