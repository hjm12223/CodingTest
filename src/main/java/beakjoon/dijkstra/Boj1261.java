package beakjoon.dijkstra;

import java.util.*;

public class Boj1261 {
    static int[][] dist;
    static int[] dx = new int[]{1,0,-1,0};
    static int[] dy = new int[]{0,1,0,-1};
    static int[][] arr;

    static boolean[][] isVisited;

    static int x;
    static int y;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        x = sc.nextInt();
        y = sc.nextInt();
        arr = new int[y][x];
        dist = new int[y][x];
        isVisited = new boolean[y][x];
        for (int i = 0; i< y; i++){
            String next = sc.next();
            for (int j = 0; j < x; j++){
                arr[i][j] = Integer.parseInt(String.valueOf(next.charAt(j)));
            }
        }

        int bfs = bfs();
        System.out.println("bfs = " + bfs);

    }

    private static int bfs() {
        Queue<Point> q = new PriorityQueue<>();
        q.offer(new Point(0,0,0));
        while (!q.isEmpty()){
            Point curr = q.poll();
            if (curr.x == x-1 && curr.y == y-1) return curr.cnt;
            for (int i = 0 ; i< 4 ; i++) {
                int n_x = dx[i] + curr.x;
                int n_y = dy[i] + curr.y;
                if (n_y < 0 || n_x <0 || n_x >= x || n_y >= y) continue;
                if (arr[n_y][n_x] != 1 && !isVisited[n_y][n_x]){
                    isVisited[curr.y][curr.x] = true;
                    dist[n_y][n_x] = dist[curr.y][curr.x] + 1;
                    q.offer(new Point(n_x,n_y, curr.cnt));
                }else if (arr[n_y][n_x] == 1 && !isVisited[n_y][n_x]){
                    isVisited[n_y][n_x] = true;
                    q.offer(new Point(n_x,n_y, curr.cnt+1));
                }
            }
        }
        return 0;
    }
    static class Point implements Comparable<Point>{
        int x;
        int y;
        int cnt;

        @Override
        public int compareTo(Point o) {
            return this.cnt - o.cnt;
        }

        public Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}
