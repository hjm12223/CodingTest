package beakjoon.DfsBfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Art {
    static int[] nx = new int[]{1,0,-1,0};
    static int[] ny = new int[]{0,-1,0,1};

    static int[][] array;

    static int count;

    static int area;
    static int max;
    static boolean[][] visited ;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        int m = scan.nextInt();
        visited= new boolean[n][m];
        array = new int[n][m];
        for (int i = 0 ; i < n ; i++){
            for (int j = 0 ; j < m ; j++){
                array[i][j] = scan.nextInt();
            }
        }
        dfs(n,m);
        System.out.println("max = " + max);
        System.out.println("count = " + count);
    }

    private static void dfs(int n, int m) {
        Queue<int[]> q = new LinkedList<>();
        count = 0;
        max = 0;
        for (int i = 0  ; i < n ; i++){
            for (int j = 0 ; j < m ; j++){
                if (array[i][j] == 0 || visited[i][j]) continue;
                visited[i][j] = true;
                q.offer(new int[]{i,j});
                count++;
                area =0;

                while (!q.isEmpty()){
                    area++;
                    int[] block = q.poll();
                    for (int k =  0; k < 4; k++){
                        int n_x = block[0] + nx[k];
                        int n_y = block[1] + ny[k];
                        if (n_x < 0 || n_x >= n || n_y < 0 || n_y >= m) continue;

                        if (array[n_x][n_y] == 1 && !visited[n_x][n_y]){
                            q.offer(new int[]{n_x,n_y});
                            visited[n_x][n_y]= true;
                        }
                    }
                }
                if (area > max) max = area;
            }

        }
    }
}
