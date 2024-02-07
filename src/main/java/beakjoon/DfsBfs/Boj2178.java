package beakjoon.DfsBfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Boj2178 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] dx = new int[]{1,0,-1,0};
        int[] dy = new int[]{0,1,0,-1};

        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] arr = new int[n][m];
        int[][] dist = new int[n][m];
        boolean[][] isVisited = new boolean[n][m];
        sc.nextLine();
        for (int i = 0 ; i< n ; i++){
            String s = sc.nextLine();
            for (int j = 0 ; j < m ; j++){
                arr[i][j] = Integer.parseInt(String.valueOf(s.charAt(j)));
            }
        }
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0,0});
        dist[0][0] = 1;
        isVisited[0][0] = true;
        while (!q.isEmpty()){
            int[] curr = q.poll();
            for (int k = 0 ; k < 4 ; k++){
                int nx = curr[0] + dx[k];
                int ny = curr[1] + dy[k];
                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (arr[nx][ny] == 1 && !isVisited[nx][ny]){
                    isVisited[nx][ny] =true;
                    dist[nx][ny] += dist[curr[0]][curr[1]] +1;
                    q.add(new int[]{nx,ny});
                }
            }
        }
        System.out.println(dist[n-1][m-1]);
    }
}
