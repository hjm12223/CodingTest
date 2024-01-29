package beakjoon.grahp;

import java.util.*;

public class Boj11724 {
    static int n;
    static int m;
    static boolean[] isVisited;
    static boolean[][] graph;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        int cnt =0;
        isVisited = new boolean[n+1];
        graph = new boolean[n+1][n+1];
        for (int i = 0 ; i < m ;i++){
            int s = sc.nextInt();
            int e = sc.nextInt();
            graph[s][e] = true;
            graph[e][s] = true;
        }
        for (int i = 1 ; i <= n ; i++){
            if (!isVisited[i]){
                bfs(i);
                cnt++;
            }
        }
        System.out.println(cnt);
    }

    private static void bfs(int x) {
        Stack<Integer> stack = new Stack<>();
        stack.push(x);
        while (!stack.isEmpty()){
            int curr = stack.pop();
            for (int i =1 ; i<=n ;i++){
                if (!isVisited[i] && graph[curr][i]){
                    isVisited[i] = true;
                    stack.push(i);
                }
            }
        }
    }
}
