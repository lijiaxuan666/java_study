//https://leetcode-cn.com/problems/container-with-most-water/
//双指针
public class day08 {
    public static void main(String[] args) {
        int[] height={1,8,6,2,5,4,8,3,7};
        day08 dy8=new day08();
        int ans=dy8.maxArea(height);
        System.out.println(ans);
    }
    public int maxArea(int [] height){
        int x=0,y=height.length-1;
        int max=0;
        while(x<y){
            if(height[x]<=height[y]) {
                max=max>height[x]*(y-x)?max:height[x]*(y-x);
                x++;
            }
            else{
                max=max>height[y]*(y-x)?max:height[y]*(y-x);
                y--;
            }
        }
        return max;
    }
}
