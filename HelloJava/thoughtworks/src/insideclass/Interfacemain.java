package insideclass;
/*匿名内部类定义格式：
    接口名称 对象名 = new 接口名称(){
        //覆盖重写所有抽象方法
    };
 */
public class Interfacemain {

    public static void main(String[] args) {

        Interface obj=new Interface() {
            @Override//用来检测是否完成所有覆盖重写
            public void method() {
                System.out.println("匿名内部类实现了方法");
            }
        };//后面的分号不能省
        obj.method();
    }
}
