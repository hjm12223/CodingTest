package beakjoon.DfsBfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Chess {
    static int[] dx = new int[]{1,2,2,1,-1,-2,-2,-1};
    static int[] dy = new int[]{2,-1,1,-2,-2,1,-1,2};
    static int[][] dist;
    static int[][] chess;
    static boolean[][] isVisited;
    static Queue<int[]> q = new LinkedList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();
        for (int i = 0 ; i< testCase ; i++){
            int n = sc.nextInt();
            chess = new int[n][n];
            isVisited= new boolean[n][n];
            dist = new int[n][n];
            int currX = sc.nextInt();
            int currY = sc.nextInt();
            dist[currX][currY] = 0;
            isVisited[currX][currY] = true;
            q.offer(new int[]{currX,currY});
            int resultX = sc.nextInt();
            int resultY = sc.nextInt();
            bfs(resultX,resultY,n);
            System.out.println(dist[resultX][resultY]);
        }
    }

    private static void bfs(int x, int y, int n) {
        while (!q.isEmpty()){
            int[] curr = q.poll();
            for (int i = 0 ; i < 8 ; i++){
                int n_x = curr[0] + dx[i];
                int n_y = curr[1] + dy[i];
                if (n_x < 0 || n_x >=n  || n_y < 0 || n_y >=n) continue;
                if (!isVisited[n_x][n_y]){
                    isVisited[n_x][n_y] = true;
                    dist[n_x][n_y] += dist[curr[0]][curr[1]] +1;
                    q.offer(new int[]{n_x,n_y});
                }
                if (n_x == x && n_y == y){
                    break;
                }
            }
        }
    }
}
