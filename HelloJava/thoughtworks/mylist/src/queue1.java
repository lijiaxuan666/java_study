import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class queue1 {
    public static void main(String[] args) {
        int []dx={1,0,-1,0};
        int []dy={0,1,0,-1};
        char [][] mg=new char[100][100];
        boolean [][]vis=new boolean[100][100];
        int [][]map=new int[100][100];
        String [] str=new String[100];
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        for (int i = 0; i < n; i++) {
            Arrays.fill(vis[i],false);
        }
        for (int i = 0; i < n; i++) {
            str[i]=sc.next();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mg[i][j]=str[i].charAt(j);
            }
        }
        Queue<int[]> qu=new LinkedList<>();
        qu.offer(new int[]{0,0});
        vis[0][0]=true;
        map[0][0]=1;
        while(!qu.isEmpty()){
            int []cur=qu.peek();
            int xx=cur[0];
            int yy=cur[1];
            for (int i = 0; i < 4; i++) {
                int x=xx+dx[i];
                int y=yy+dy[i];
                if(x>=0&&y>=0&&x<n&&y<n&&mg[x][y]=='-'&&!vis[x][y]){
                    vis[x][y]=true;
                    map[x][y]=map[xx][yy]+1;
                    if(x==n-1&&y==n-1){
                        System.out.println(map[n-1][n-1]);
                        return;
                    }
                    qu.offer(new int[]{x,y});
                }
            }
            qu.poll();
        }
    }
}
