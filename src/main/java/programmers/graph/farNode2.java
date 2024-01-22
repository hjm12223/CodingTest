package programmers.graph;

import java.util.*;

public class farNode2 {
    public static void main(String[] args) {
        System.out.println(solution(11, new int[][]{{1, 2}, {1, 3}, {2, 4}, {2, 5}, {3, 5}, {3, 6}, {4, 8}, {4, 9}, {5, 9}, {5, 10}, {6, 10}, {6, 11}}) + " (Expected: 4)");
    }
    static List<List<Integer>> graph = new ArrayList<>();
    static boolean[] visited;
    static int[] dist;
    public static int solution(int n, int[][] edge){
        visited = new boolean[n];
        dist = new int[n];
        Arrays.fill(dist,Integer.MAX_VALUE);
        for (int i = 0 ; i < n ; i++)
            graph.add(new ArrayList<>());
        for (int[] e : edge) {
            graph.get(e[0]-1).add(e[1] -1);
            graph.get(e[1]-1).add(e[0] -1);
        }
        bfs(0);
        Arrays.sort(dist);
        System.out.println("dist = " + Arrays.toString(dist));
        int count = 1;
        for (int i = dist.length-1; i > 0 ; i--){
            if (dist[i] != dist[i-1]) {
                break;
            }
            else {
                count++;
            }
        }
        return count;
    }

    private static void bfs(int i) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(i);
        visited[i] = true;
        dist[i] = 0;

        while (!q.isEmpty()){
            int curr = q.poll();
            for (int e : graph.get(curr)) {
                if (!visited[e]){
                    q.offer(e);
                    visited[e] =true;
                    dist[e] = Math.min(dist[e] , dist[curr] +1 );
                }
            }
        }
    }
}
