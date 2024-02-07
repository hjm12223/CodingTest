package beakjoon.DfsBfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Boj1926 {
    static int[] dx = new int[]{1,0,-1,0};
    static int[] dy=  new int[]{0,1,0,-1};
    static int[][] arr;
    static int count = 0;
    static int max = 0;
    static int row;
    static int column;
    static int area;
    static boolean[][] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        row = sc.nextInt();
        column = sc.nextInt();
        arr = new int[row][column];
        visited = new boolean[row][column];
        for (int i = 0 ; i < row; i++){
            for (int j = 0; j < column; j++){
                arr[i][j] = sc.nextInt();
            }
        }
        bfs();
        System.out.println("arr = " + Arrays.deepToString(arr));
        System.out.println(count);
        System.out.println(max);

    }

    private static void bfs() {
        Queue<Point> q = new LinkedList<>();

        for (int i = 0 ; i< row; i++ ){
            for (int j = 0 ; j < column ; j++){
                if (arr[i][j] == 0 || visited[i][j]) continue;
                visited[i][j] =true;
                q.offer(new Point(i,j));
                count++;
                area= 0;
                while (!q.isEmpty()){
                    area++;
                    Point curr = q.poll();
                    for (int k = 0;  k < 4  ; k++){
                        int nx = dx[k] + curr.x;
                        int ny = dy[k] + curr.y;
                        if (nx < 0 || ny < 0 || nx >= row || ny >= column ) continue;
                        if (arr[nx][ny] == 1 && !visited[nx][ny]){
                            q.offer(new Point(nx,ny));
                            visited[nx][ny] = true;
                        }
                    }
                }
                if (area > max) max = area;
            }
        }

    }

    private static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
