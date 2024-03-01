package beakjoon.DfsBfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1600 {
    static int[][] arr;
    static int[] dx = new int[]{1,0,-1,0};
    static int[] dy = new int[]{0,1,0,-1};

    static int[] hx = new int[]{2,1,1,2,-2,-1,-1,-2};
    static int[] hy = new int[]{-1,-2,-2,-1,1,2,2,1};

    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Integer k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        arr = new int[r][c];
        visited = new boolean[r][c];
        for (int i = 0 ; i< r ; i++){
            String[] line = br.readLine().split(" ");
            for (int j = 0 ; j < c ; j++){
                arr[i][j] = Integer.parseInt(line[j]);
            }
        }

        arr[r-1][c-1] = Integer.MAX_VALUE;
        Queue<int[]> q  = new LinkedList<>();
        q.offer(new int[]{0,0});
        visited[0][0] = true;
        int cnt = 0;

        while (!q.isEmpty()){

            int[] curr = q.poll();
            System.out.println("c = " + Arrays.toString(curr));
            int cx = curr[0];
            int cy = curr[1];
            for (int i = 0 ; i< 4 ; i++){
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx >= r || ny >= c || nx < 0 || ny < 0 || arr[nx][ny] > 0) continue;
                if (arr[nx][ny] == Integer.MAX_VALUE){
                    System.out.println(cnt);
                }
                if (!visited[nx][ny]){
                 q.offer(new int[]{nx,ny});
                 visited[nx][ny] = true;
                }
            }
            System.out.println("arr = " + Arrays.deepToString(arr));
        }

    }
}
/*

 */