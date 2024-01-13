package beakjoon.DfsBfs;

import java.util.*;

public class redGreen {


    static char[][] map;
    static boolean[][] isVisited;

    static int[] nx = new int[]{1,0,-1,0};
    static int[] ny = new int[]{0,1,0,-1};
    static Queue<int[]> q = new LinkedList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int n = sc.nextInt();
        map = new char[n][n];
        isVisited = new boolean[n][n];
        for (int i = 0 ; i < n ; i ++){
            String line = sc.next();
            for(int j = 0 ;j < n ; j++){
                    map[i][j] = line.charAt(j);
                }
            }
        int cnt = 0;
              for (int i = 0 ; i < n ; i ++){
                for(int j = 0 ;j < n ; j++){
                    if (!isVisited[i][j]){
                        cnt++;
                        bfs(i,j,n);
                    }
                }
            }
        sb.append(cnt);
        isVisited = new boolean[n][n];
        cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!isVisited[i][j]) {
                    cnt++;
                    bfs(i, j,n);
                }
            }
        }
        sb.append(" " +cnt);
        System.out.println(sb.toString());
    }

    private static void bfs(int x, int y, int n) {
        q.offer(new int[]{x,y});
        isVisited[x][y] = true;
        while (!q.isEmpty()){
            int[] curr = q.poll();
            for (int k = 0 ; k < 4 ; k++){
                int n_x = curr[0] + nx[k];
                int n_y = curr[1] + ny[k];
                if (n_x < 0 || n_x >= n || n_y <0 || n_y >= n) continue;
                if (isVisited[n_x][n_y] || map[curr[0]][curr[1]] != map[n_x][n_y]) continue;
                        q.offer(new int[]{n_x, n_y});
                        isVisited[n_x][n_y] = true;
                    }
            if (map[curr[0]][curr[1]] == 'G') map[curr[0]][curr[1]] = 'R';
            }
        }
    }
