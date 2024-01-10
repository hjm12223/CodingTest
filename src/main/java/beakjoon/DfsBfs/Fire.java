package beakjoon.DfsBfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Fire {
    static int[] dx = new int[]{1,0,-1,0};
    static int[] dy = new int[]{0,1,0,-1};
    static int[][] arr;

    static int[][] dist;

    static int[][] dist2;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int row = sc.nextInt();
        int column = sc.nextInt();
        arr = new int[column][row];
        dist = new int [column][row]; // 불
        dist2 = new int[column][row]; // 지훈
        Queue<Node> q = new LinkedList<>();
        Queue<Node> q2 = new LinkedList<>();
        for (int i = 0; i < column; i++) {
            String str = sc.next();
            for (int j = 0; j < row; j++) {
                arr[i][j] = str.charAt(j);
                if (str.charAt(j) == 'J') {
                    q2.offer(new Node(i, j));
                    dist2[i][j] = 1;
                } else if (str.charAt(j) == 'F') {
                    q.offer(new Node(i, j));
                    dist[i][j] = -1;
                } else if (str.charAt(j) == '#') {
                    dist[i][j] = -1;
                    dist2[i][j] = -1;
                } else {
                    dist[i][j] = 0;
                    dist2[i][j] = 0;
                }
            }
        }
        bfs(row, column, q,dist);
        bfs2(row,column,q2,dist2);
        System.out.println("dist2 = " + Arrays.deepToString(dist2));
        System.out.println("dist = " + Arrays.deepToString(dist));

    }
    public static int bfs(int row , int column, Queue<Node> q,int[][] dist){
        int ans = 0;
        while (!q.isEmpty()){

            Node node = q.poll();
            for (int k = 0 ; k < 4 ; k++){
                int n_x = node.x + dx[k];
                int n_y = node.y + dy[k];
                if (n_x < 0 || n_x >= row || n_y < 0 || n_y >= column) continue;
                if (dist[n_x][n_y] != 0 ) continue;
                dist[n_x][n_y] = dist[node.x][node.y] +1;
                q.offer(new Node(n_x,n_y));
            }
        }
        for (int i = 0 ; i < column; i++){
            for (int j = 0 ; j < row ; j++){
                ans = Math.max(ans,dist[i][j]);
            }
        }return ans;
    }

    public static void bfs2(int row , int column, Queue<Node> q,int[][] dist){
        int ans = 0;
        while (!q.isEmpty()){
            Node node = q.poll();
            for (int k = 0 ; k < 4 ; k++) {
                int n_x = node.x + dx[k];
                int n_y = node.y + dy[k];
                if (n_x < 0 || n_x >= row || n_y < 0 || n_y >= column){
                    System.out.println(dist[node.x][node.y]+1);
                     return;
            }
                if (dist2[n_x][n_y] >= 0 || arr[n_x][n_y] == '#') continue;
                if (dist[n_x][n_y] != -1 && dist[n_x][n_y] <= dist[node.x][node.y] +1) continue;
                dist[n_x][n_y] = dist[node.x][node.y] +1;
                q.offer(new Node(n_x,n_y));
            }
        }
        for (int i = 0 ; i < column; i++){
            for (int j = 0 ; j < row ; j++){
                ans = Math.max(ans,dist[i][j]);
            }
        }
    }
    public static class Node{
        int x;
        int y;

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
