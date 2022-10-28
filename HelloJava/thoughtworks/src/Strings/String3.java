package Strings;
/*
public boolean equals(Object obj);
    参数可以是任何对象，但只有当参数是一个字符串且内容相同时才会返回true，否则false
注意：
    1.任何对象都能用Object进行接收
    2.equals方法具有对称性，即可表示为a.equals(b),也可表示为b.equals(a)
    3.如果比较双方一个常量一个变量，推荐把常量字符串写在前面，因为当字符串为null作为参数时时会出现空指针异常

public boolean equalsIgnoreCase(String str);//忽略大小写的比较
 */
public class String3 {
    public static void main(String[] args) {
        String str1="abcd";
        String str2="abcd";
        char[] array={'a','b','c','d'};
        String str3=new String(array);
        String str4="ABCD";

        System.out.println(str1.equals(str2));//true
        System.out.println(str1.equals(str3));//true
        System.out.println(str1.equals(str4));//false

        System.out.println("abcd".equals(str1));//true
        System.out.println(str1.equals("abcd"));//true

        System.out.println(str1.equalsIgnoreCase(str4));//true
    }
}
