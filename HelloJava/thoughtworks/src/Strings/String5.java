package Strings;

import java.nio.charset.StandardCharsets;

/*
字符串截取：
    public String subString(int index);//截取从参数位置一直到字符串末尾
    public String substring(int begin, int end);//从begin开始，从end结束，包含begin，不包含end位置
字符串转换：
    public char[] toCharArray();//将当前字符串拆分为字符数组作为返回值
    public byte[] getBytes();//获取当前字符串底层的字符数组
    public String replace(CharSequence oldString, CharSequence newString);
    //将出现的所有老字符串替换为新字符串
字符串分割：
    public String[] split(String regex);//按照参数规则，把字符串分为若干分
    注意：split方法参数其实是一个正则表达式
         如果用"."(小数点)来切分,应表示为"\\."(因为”.“在正则表达式中有特殊含义)
 */
public class String5 {
    public static void main(String[] args) {
        String str1="abcdefghij";
        //截取
        String str2=str1.substring(3);
        System.out.println(str2);

        String str3=str1.substring(3,6);
        System.out.println(str3);
        System.out.println("=================");

        //转换
        String str4="ABCD";
        char[] array=str4.toCharArray();//转化为字符数组
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }

        System.out.println("=================");
        byte[] array1=str4.getBytes();
        for (int i = 0; i < array1.length; i++) {
            System.out.println(array1[i]);
        }

        System.out.println("=================");
        String str5=str4.replace("BC","EF");
        System.out.println(str5);

        System.out.println("=================");
        //分割
        String str11="aaa,bbb,ccc";
        String[] array2=str11.split(",");
        for (int i = 0; i < array2.length; i++) {
            System.out.println(array2[i]);
        }
    }
}
