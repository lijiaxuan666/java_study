import java.util.Arrays;
import java.util.Comparator;

public class day04 {
    public static void main(String[] args) {
        int [][]intervals={{1,2},{2,3},{3,4},{1,3}};
        day04 dy4=new day04();
        int ans=dy4.eraseOverlapIntervals(intervals);
        System.out.println(ans);
    }
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1]-o2[1];
            }
            //根据右区间排序，无论何时开始，他都是最早结束，是最优解
        });
        int n=intervals.length;
        int end=intervals[0][1];
        int ans=1;
        for(int i=1;i<n;i++){
            //如果左区间大于等于上个区间的右区间，则选择
            if(intervals[i][0]>=end){
                end=intervals[i][1];
                ans++;
            }
        }
        return n-ans;
    }
}
