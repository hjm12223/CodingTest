package beakjoon.DfsBfs;

import java.util.*;

public class Boj2667 {
    static int[] dx = new int[]{1,0,-1,0};
    static int[] dy = new int[]{0,1,0,-1};

    static List<Integer> list =  new ArrayList<>();
    static int N;
    static int[][] arr;
    static boolean[][] visited;

    static int count;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arr = new int[N][N];
        count = 0;
        visited = new boolean[N][N];
        for (int i = 0;  i< N ; i++){
            String value = sc.next();
            for (int j = 0 ; j < N ; j++){
                arr[i][j] = Integer.parseInt(String.valueOf(value.charAt(j)));
                if (arr[i][j] == 0){
                    visited[i][j] = true;
                }
            }
        }

        for (int i = 0;  i< N ; i++){
            for (int j = 0 ; j < N ; j++){
                if (!visited[i][j]) {
                    bfs(i, j);
                }
            }
        }
        Collections.sort(list);
        System.out.println(list.size());
        for (int  result : list) {
            System.out.println(result);
        }
    }

    private static void bfs(int i, int j) {
        int cnt = 0;
        visited[i][j] = true;
        Stack<Point> q = new Stack<>();
        q.push(new Point(i,j));
        while (!q.isEmpty()){
            Point curr = q.pop();
            cnt++;

            for(int k = 0; k < 4 ; k++){
                int n_x = curr.x + dx[k];
                int n_y = curr.y + dy[k];
                if (n_x< 0 || n_x >=N || n_y < 0 || n_y >= N) continue;
                if (arr[n_x][n_y] != 0  && !visited[n_x][n_y]){
                    visited[n_x][n_y] =true;
                    q.push(new Point(n_x,n_y));
                }
            }
        }
            list.add(cnt);
    }

    public static class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
/**
 * [0, 1, 2, 0, 1, 0, 0]
 * [0, 2, 3, 0, 2, 0, 8]
 * [4, 3, 4, 0, 3, 0, 7]
 * [0, 0, 0, 0, 4, 5, 6]
 * [0, 1, 0, 0, 0, 0, 0]
 * [0, 2, 3, 4, 5, 6, 0]
 * [0, 3, 4, 5, 0, 0, 0]
 *
 * [0, 1, 2, 0, 1, 0, 0],
 * [0, 2, 3, 0, 2, 0, 8],
 * [6, 5, 4, 0, 3, 0, 7],
 * [0, 0, 0, 0, 4, 5, 6],
 * [0, 1, 0, 0, 0, 0, 0],
 * [0, 2, 3, 4, 5, 6, 0],
 * [0, 3, 4, 5, 0, 0, 0]]
 *
 */