//https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/
//快速幂
public class day10 {
    public static void main(String[] args) {
        day10 dy10=new day10();
        System.out.println(dy10.myPow(2.0000,-2));
    }
    public double myPow(double x, int n) {
        if(x == 0) return 0;
        long b = n;//Java代码中int32变量n∈[−2147483648,2147483647]
        //因此当n=−2147483648时执行n=−n会因越界而赋值出错。
        //解决方法是先将n存入long变量b，后面用b操作即可。
        double res = 1.0;
        if(b < 0) {
            x = 1 / x;
            b = -b;
        }
        while(b > 0) {
            if((b & 1) == 1) res *= x;
            x *= x;
            b >>= 1;
        }
        return res;
    }
}
