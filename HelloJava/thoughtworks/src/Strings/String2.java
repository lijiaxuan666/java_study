package Strings;

/*
    字符串常量池：程序中直接写上双引号的字符串，就在字符串常量池中

    对于基本类型来说，==是进行数值的比较
    对于引用类型来说，==是进行地址值的比较
 */
public class String2 {
    public static void main(String[] args) {
        String str1 = "abc";
        String str2 = "abc";

        char[] chararray = {'a', 'b', 'c'};
        String str3 = new String(chararray);

        System.out.println(str1 == str2);
        System.out.println(str1 == str3);
        System.out.println(str2 == str3);
    }
}
