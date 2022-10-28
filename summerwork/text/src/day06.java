public class day06 {
    public static void main(String[] args) {
        day06 dy6=new day06();
        int a=dy6.longestPalindromeSubseq("bbbab");
        System.out.println(a);
    }
    public int longestPalindromeSubseq(String s) {
        int n=s.length();
        char []chars=s.toCharArray();
        int [][]dp=new int[n][n];
        for(int i=n-1;i>=0;i--){//串应该由短到长，所以注意开始顺序
            dp[i][i]=1;//字母本身也是一个回文串
            for(int j=i+1;j<n;j++){
                if(chars[i]==chars[j])//如果两个相同，就加在这两个位置之间的最长字串两边，所以长度+2
                    dp[i][j]=dp[i+1][j-1]+2;
                else//两个不相等，肯定不能同时加，所以看这两个哪个子串更长点
                    dp[i][j]=Math.max(dp[i+1][j],dp[i][j-1]);
            }
        }
        return dp[0][n-1];
    }


}
