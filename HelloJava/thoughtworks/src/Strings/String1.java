package Strings;

/*
    1.字符串内容永远不可变
    2.因为字符串不可变，所以字符串是可以共享使用的
    3.字符串效果上相当于char[]字符数组，但其底层原理是byte[]字节数组

三种构造方法：
    public String();  //创建一个空字符串，没有任何内容
    public String(char[] array);   //根据字符数字内容创建字符串
    public String(bytw[] array);  //根据字节数组内容创建字符串
直接创建：
    String str="Hello World"
 */
public class String1 {
    public static void main(String[] args) {
        String str1 = new String();
        System.out.println("第一个字符串是：" + str1);

        char[] chararray = {'A', 'B', 'C'};
        String str2 = new String(chararray);
        System.out.println(str2);

        byte[] bytearray = {97, 98, 99};
        String str3 = new String(bytearray);
        System.out.println(str3);
    }
}
