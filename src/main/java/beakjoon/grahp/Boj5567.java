package beakjoon.grahp;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Boj5567 {
    static int n;
    static int m;
    static int[][] graph;
    static boolean[] visited;
    static int cnt = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt(); // 노드의 수
        m = sc.nextInt(); // 노드의 수

        visited = new boolean[n+1];
        graph = new int[n+1][n+1];

        for (int i = 0 ; i< m; i++){
            int s = sc.nextInt();
            int e = sc.nextInt();
            graph[s][e] = 1;
            graph[e][s] = 1;
        }
        bfs(1);
        System.out.println("cnt = " + cnt);
    }

    private static void bfs(int x) {
        visited[x] =true;
        Queue<Integer> q = new LinkedList<>();
        q.offer(x);
        int level = 1;
        while (!q.isEmpty() && level < 3) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                int curr = q.poll();

                for (int next = 1; next <= n; next++) {
                    if (!visited[next] && graph[curr][next] == 1) {
                        visited[next] = true;
                        q.offer(next);
                        cnt++;
                    }
                }
            }
            level++;
        }
    }
}