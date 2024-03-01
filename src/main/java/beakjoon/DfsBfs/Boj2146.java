package beakjoon.DfsBfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2146 {
    static int result = 0;
    static int[][] arr;
    static boolean[][] visited;
    static int[] dx = new int[]{1,0,-1,0};
    static int[] dy = new int[]{0,1,0,-1};

    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        result = Integer.MAX_VALUE;
         n = Integer.parseInt(st.nextToken());
        arr = new int[n][n];
        for (int i = 0 ; i < n ; i++){
            String[] line = br.readLine().split(" ");
            for (int j = 0 ; j < n ; j++){
                arr[i][j] = Integer.parseInt(line[j]);
            }
        }
        int isIndex = 2;
        visited = new boolean[n][n];
        for (int i = 0 ; i < n ; i++){
            for (int j = 0 ; j < n ; j++){
                if (arr[i][j] == 1 && !visited[i][j]){
                    bfs(i,j,isIndex++);
                }
            }
        }
        for (int i = 2; i<= isIndex ; i++) {
            result = Math.min(connect(i),result);
        }
        if (result == Integer.MAX_VALUE){
            System.out.println(0);
        }else {
            System.out.println(result);
        }
    }

    private static void bfs(int x, int y, int isIndex) {
        visited[x][y] = true;
        Queue<int[]>q = new LinkedList<>();
        q.offer(new int[]{x,y});
        arr[x][y] = isIndex;

        while (!q.isEmpty()){
            int[] curr = q.poll();
            int cx = curr[0];
            int cy = curr[1];
            for (int i = 0; i < 4 ; i++){
                int nx = dx[i] + cx;
                int ny = dy[i] + cy;
                if (nx >= n || ny >= n || nx < 0 || ny < 0 || visited[nx][ny] || arr[nx][ny] != 1) continue;
                visited[nx][ny] = true;
                q.offer(new int[]{nx,ny});
                arr[nx][ny] = isIndex;
            }
        }
    }

    private static int connect(int index) {
        Queue<int[]>q = new LinkedList<>();
        for (int i = 0 ; i < n ; i++){
            for (int j = 0 ; j < n ; j++){
                if (arr[i][j] == index){
                    q.offer(new int[]{i,j});
                    visited[i][j] = true;
                }
            }
        }

        int bridgeLength = 0;




        while (!q.isEmpty()) {
            int queueSize = q.size();

            for (int i = 0; i < queueSize; i++) {

                int[] curr = q.poll();
                int cx = curr[0];
                int cy = curr[1];

                for (int d = 0; d < 4; d++) {
                    int nx = dx[d] + cx;
                    int ny = dy[d] + cy;

                    if (nx >= n || ny >= n || nx < 0 || ny < 0) continue;

                    if (arr[nx][ny] != 0 && arr[nx][ny] != index){
                        return bridgeLength;
                    }
                    if (arr[nx][ny] ==  0&& !visited[nx][ny]){
                        q.offer(new int[]{nx,ny});
                        visited[nx][ny] = true;
                    }
                }
            }
            bridgeLength++;
        }
        return Integer.MAX_VALUE;
    }
}
/*
[[true, true, true, true, true, false, false, false, false, false],
[true, true, true, false, false, false, false, false, false, false],
[true, true, false, false, false, false, false, false, false, false],
[true, true, false, false, false, false, false, false, false, false],
[true, false, false, false, false, false, false, false, false, false],
[false, false, false, false, false, false, false, false, false, false],
[false, false, false, false, false, false, false, false, false, false],
[false, false, false, false, false, false, false, false, false, false],
[false, false, false, false, false, false, false, false, false, false],
[false, false, false, false, false, false, false, false, false, false]]

 */
/*
다리를 만들어야한다
그러면 해당 가장자리에서 시작하는것이 가장 알맞음

가장자리에 도착했을 떄 bfs 를 시작 다리를 만들어줌
 */