package insideclass;

public class outside {   //外部类
    String name="abc";

    public class inside{  //成员内部类-->非静态内部类
        //内部类的方法
        public void method1(){
            System.out.println(name);
            //内部类可以随意访问外部类，反之则需要内部类对象
        }
    }

    public void test(){
        //局部内部类
        class C{

        }
    }

    public void method2(){
        inside A = new inside();
        A.method1();//通过外部类的方法间接访问内部类
    }
}
