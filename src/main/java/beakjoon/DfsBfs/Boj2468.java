package beakjoon.DfsBfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Boj2468 {
    static int[] dx= new int[]{1,0,-1,0};
    static int[] dy = new int[]{0,1,0,-1};

    static int[][] arr;
    static boolean[][] visited;

    static int N;
    static int cnt;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        arr = new int[N][N];
        int max = Integer.MIN_VALUE;
        for (int i = 0 ; i < N; i++){
            for(int j =0 ; j < N ; j++){
                arr[i][j] = sc.nextInt();
                if (max<arr[i][j]) max= arr[i][j];
            }
        }
        int result = 0;
        for (int k = 0; k < max; k++) {
            cnt = 0;
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (arr[i][j] > k && !visited[i][j]) {
                        bfs(i, j, k);
                        cnt++;
                        System.out.println("cnt = " + cnt);
                    }
                }

            }
            result = Math.max(cnt,result);

        }
        System.out.println(result);
    }
    private static void bfs(int i, int j, int k) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{i, j});
        visited[i][j] = true;
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            for (int l = 0; l < 4; l++) {
                int n_x = curr[0] + dx[l];
                int n_y = curr[1] + dy[l];
                if (n_x < 0 || n_x >= N || n_y < 0 || n_y >= N) continue;
                if (arr[n_x][n_y] > k && !visited[n_x][n_y]) {
                    visited[n_x][n_y] = true;
                    q.offer(new int[]{n_x, n_y});
                }
            }
        }
    }
}
