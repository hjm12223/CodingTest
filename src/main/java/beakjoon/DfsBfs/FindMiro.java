package beakjoon.DfsBfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class FindMiro {

    static int[][] dist;
    static int[][] array;

    static int[] dx = new int[]{0,1,0,-1};
    static int[] dy = new int[]{1,0,-1,0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(st.nextToken());

        for(int i=0;i<n;i++) {

            String input = sc.next();

            for(int j=0;j<m;j++) {
                array[i][j] = input.charAt(j) - '0';
                dist[i][j] = -1;
            }
        }
        bfs(n, m);
    }

    private static void bfs(int n, int m) {
        Queue<Pair> q = new LinkedList<>();
        dist[0][0] = 0;

        Pair pairs = new Pair(0,0);
        q.offer(pairs);
        while (!q.isEmpty()){
            Pair pair = q.poll();
            System.out.println("pair = " + pair);
            for (int k = 0 ; k < 4 ; k++){
                int n_x = pair.x + dx[k];
                int n_y = pair.y + dy[k];

                if (n_x < 0 || n_x >= n || n_y < 0 || n_y >= m) continue;
                if (array[n_x][n_y] != 1 && dist[n_x][n_y] != -1) continue;

                q.offer(new Pair(n_x,n_y));
                dist[n_x][n_y] = dist[pair.x][pair.y] +1;
            }
        }
    }
        public static class Pair {
            int x;
            int y;

            public Pair(int x, int y) {
                this.x = x;
                this.y = y;
            }
    }
}
