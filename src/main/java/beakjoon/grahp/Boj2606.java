package beakjoon.grahp;

import java.util.Scanner;

public class Boj2606 {
    static boolean[][] graph;
    static boolean[] visited;
    static int n;
    static int m;
    static int cnt = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n  = sc.nextInt(); // 노드의 수
        m  = sc.nextInt(); // 간선
        graph = new boolean[n+1][n+1];
        visited = new boolean[n+1];
        
        for (int i = 0 ; i< m ;i++){
            int s = sc.nextInt();
            int e = sc.nextInt();
            graph[s][e] = true;
            graph[e][s] = true;
        }
        int dfs = dfs(1);
        System.out.println(dfs);
    }
    public static int dfs(int v){
        visited[v] = true;
        if (v == graph.length) {
            return cnt;
        }
        for (int i = 1 ; i< graph.length ;i++){
            if (!visited[i]&& graph[v][i]){
                cnt++;
                dfs(i);
            }
        }
        return cnt;
    }
}
