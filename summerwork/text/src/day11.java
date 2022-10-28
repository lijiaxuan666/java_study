import java.util.Arrays;
//https://leetcode-cn.com/problems/network-delay-time/
//单源最短路径算法 Dijkstra
public class day11 {
    public static void main(String[] args) {
        int [][]times={{2,1,1},{2,3,1},{3,4,1}};
        day11 dy11=new day11();
        int ans=dy11.networkDelayTime(times,4,2);
        System.out.println(ans);
    }
    public int networkDelayTime(int[][] times, int n, int k) {
        int MAX_VALUE=(int)1e9;//将数组的值初始化为该值，输入的所有的数据都比他小，不会对结果产生影响
        int [][]map=new int[n][n];
        for(int i=0;i<n;i++){
            Arrays.fill(map[i],MAX_VALUE);
        }
        for(int i=0;i<times.length;i++){
            map[times[i][0]-1][times[i][1]-1]=times[i][2];
        }
        int ans[]=new int[n];//（他的值是起点到这个节点的最短距离）
        Arrays.fill(ans,MAX_VALUE);
        ans[k-1]=0;
        int x;
        boolean []vis=new boolean[n];
        for(int i=0;i<n;i++){//有n个节点，所以循环n次
            x=-1;
            for(int j=0;j<n;j++){
                if(!vis[j]&&(x==-1||ans[j]<ans[x])){
                    //如果他没走过，如果x还没有初始值或者起点到这个节点的值小于起点到上个记录的值
                    x=j;
                }
            }
            vis[x]=true;//让他变为走过
            for(int j=0;j<n;j++){
                ans[j]=Math.min(ans[j],ans[x]+map[x][j]);
                //判断起点到这个节点的值与上个节点+上个节点到这个节点的值哪个小
            }
        }
        int max=ans[0];//查找数字中最大值
        for(int i=1;i<n;i++){
            if(ans[i]>max)
                max=ans[i];
        }
        return max==MAX_VALUE?-1:max;//如果最大值是MAX_VALUE，说明有节点没有走到，返回-1
    }
}
