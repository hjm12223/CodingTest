package beakjoon.DfsBfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Tomato {
    static int[][] box;
    static int[][] dist;
    static int[] nx = new int[]{1,0,-1,0};
    static int[] ny = new int[]{0,1,0,-1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Queue<Tot> q = new LinkedList<>();

        int row = sc.nextInt();
        int column = sc.nextInt();

        box = new int[column][row];
        dist = new int[column][row];

        for (int i = 0 ;  i < column; i++){
            for (int j = 0 ; j < row ; j++){
                box[i][j] = sc.nextInt();
                if (box[i][j] == 1) q.offer(new Tot(i,j));
                if (box[i][j] == 0) dist[i][j] = -1;
            }
        }
        System.out.println("dist = " + Arrays.deepToString(dist));
        int ans = bfs(row, column, q);
        System.out.println("ans = " + ans);
    }

    private static int bfs(int row, int column, Queue<Tot> q) {

        int ans =0;

        while(!q.isEmpty()){
            Tot tot = q.poll();
            for (int k = 0 ; k < 4 ; k++){
                int n_x = tot.x + nx[k];
                int n_y = tot.y + ny[k];
                if (n_x < 0 || n_x >= row || n_y <0 || n_y >= column) continue;
                if (dist[n_x][n_y] >= 0 ) continue;
                q.offer(new Tot(n_x,n_y));
                dist[n_x][n_y] = dist[tot.x][tot.y] +1;
            }
        }
        System.out.println("dist = " + Arrays.deepToString(dist));
        for (int i = 0 ;  i < column; i++){
            for (int j = 0 ; j < row ; j++){
                if (dist[i][j] == -1) return 0;
                ans = Math.max(dist[i][j],ans);
            }
        }
        return ans;
    }


    public static class Tot{
        int x;
        int y;

        public Tot(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
