package beakjoon.DfsBfs;

import java.util.*;

public class DFSAndBFS {

    static int[][] array;

    static boolean[] visited;


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        int m = scan.nextInt();
        int v = scan.nextInt();



        array = new int[n+1][n+1];

        for (int i = 0 ; i < m ; i++){
            int a = scan.nextInt();
            int b = scan.nextInt();
            array[a][b] =1;
            array[b][a] = 1;
        }

        /**
         * 인접 노드들을 설정
         */
        visited = new boolean[n+1];
        dfs(v);
        visited = new boolean[n+1];
        bfs(v);
    }

    private static void dfs(int v){
        visited[v] = true;
        System.out.println(v + " ");
        if (v == array.length) return;

        for (int j = v;  j < array.length;  j++){
            if (array[v][j] == 1 && !visited[j]){
                dfs(j);
            }
        }
    }
    private static void bfs(int v){
        Queue<Integer> q = new LinkedList<>();
        q.offer(v);
        visited[v] = true;
        System.out.println(v +" ");
        while (!q.isEmpty()){
            int n = q.poll();
            for (int i = 1 ; i < array.length ; i++){
                if (array[n][i] == 1 && visited[i] == false){
                    visited[i] = true;
                    System.out.println(i + " ");
                    q.offer(i);
                }
            }
        }
    }
}
