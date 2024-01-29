package beakjoon.grahp;

import java.util.*;

public class Boj1260 {

    static boolean[] isVisited;
    static int[][] graph;

    static int n;
    static int m;
    static int v;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
         n = sc.nextInt(); // 노드
         m = sc.nextInt(); // 간선
         v = sc.nextInt(); // 시작번호
        graph = new int[n+1][n+1];
        isVisited = new boolean[n+1];
        for (int i = 0 ; i< m ; i++){
            int s = sc.nextInt();
            int e = sc.nextInt();
            graph[s][e] = 1;
            graph[e][s] = 1;
        }
        dfs(v);
        System.out.println();
        isVisited = new boolean[n+1];
        bfs(v);
        }

    private static void bfs(int v) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(v);
        System.out.print(v  + " " );
        isVisited[v] = true;
        while (!q.isEmpty()){
            int curr = q.poll();
            for (int i = 1 ; i< graph.length;  i++) {
                if (graph[curr][i] == 1 && !isVisited[i]) {
                    isVisited[i] = true;
                    q.offer(i);
                    System.out.print(i  + " " );
                }
            }
            }
        }
    private static void dfs(int v) {
        isVisited[v] = true;
        System.out.print(v  + " " );
        if (v == graph.length) return;;
        for (int i = 1 ; i< graph.length; i++){
            if (graph[v][i] ==1  && !isVisited[i])
                dfs(i);
            }
        }
}
