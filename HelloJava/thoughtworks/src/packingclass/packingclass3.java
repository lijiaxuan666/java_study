package packingclass;

public class packingclass3 {
    public static void main(String[] args) {
        int i1 = 100;
        String s1 = i1 + "";//最简单方法，工作中常用
        System.out.println(s1 + 200);//100200

        String s2 = Integer.toString(100);
        //包装类静态方法toString(参数),不是Object类的toString重载
        //static String toString(int i)返回一个表示指定整数的String对象
        System.out.println(s2 + 200);//100200

        String s3 = String.valueOf(100);
        //static String valueOf(int i)返回 int参数的字符串表示形式
        System.out.println(s3 + 200);//100200

        //字符串-->基本类型
        //使用包装类的静态方法parsexxx("字符串");
        int i = Integer.parseInt(s1);
        System.out.println(i + 100);//300
    }
}
