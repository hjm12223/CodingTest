package beakjoon.grahp;

import java.util.Scanner;

public class Boj2660 {
    static boolean[][] graph;
    static boolean[] visited;
    static int n;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); // 노드의 수
        graph= new boolean[n+1][n+1];
        visited = new boolean[n+1];
        while (true){
            int s = sc.nextInt();
            int e = sc.nextInt();
            if (s == -1 && e == -1) break;
            graph[s][e] = true;
            graph[e][s] = true;
        }
    }
}
