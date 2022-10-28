package Strings;
/*
public int length();//获取字符个数，字符串长度
public String concat(String str);//将当前字符串和参数字符串拼接为新字符串
public char charAt(int index);//获取指定索引位置的字符
public int indexOf(String str);//查找参数字符串在字符串当中首次出现的索引位置
 */
public class String4 {
    public static void main(String[] args) {
        String str1="abcde";
        String str2="fghij";
        //长度
        System.out.println(str1.length());//5

        //拼接
        String str3=str1.concat(str2);
        System.out.println(str3);//abcdefghij

        //获取指定索引位置的字符
        char ch=str1.charAt(3);
        System.out.println(ch);//d

        //查找参数字符串在字符串当中首次出现的索引位置
        int i=str3.indexOf("cde");
        System.out.println(i);//2
    }
}
