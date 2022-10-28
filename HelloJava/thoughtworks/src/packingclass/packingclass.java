package packingclass;

/*
装箱：
    构造方法：
        Integer(int value)构造一个新分配的Integer对象，它表示指定的int值
        Integer(String s)构造一个新分配的Integer对象，它表示String参数所指示的int值
    静态方法：
        static Integer valueOf(int i)返回一个指定的int值的Integer实例
        static Integer valueOf(String s)返回保存指定的String的值的Integer对象
拆箱：
    成员方法：
        int intValue()以int类型返回该Integer的值
 */
public class packingclass {
    public static void main(String[] args) {
        //构造方法
        Integer in1 = new Integer(111);//划横线说明已经过时了
        System.out.println(in1);//重写了toString方法
        Integer in2 = new Integer("111");
        System.out.println(in2+100);
        //静态方法
        Integer in3 = Integer.valueOf(111);
        System.out.println(in3);
        Integer in4 = Integer.valueOf("111");
        System.out.println(in4+100);

        String str=String.valueOf(100);
        System.out.println(str+100);//100100

        //拆箱
        int i = in1.intValue();
        System.out.println(i);
    }
}
