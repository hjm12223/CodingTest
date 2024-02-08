package beakjoon.mst;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Boj16398 {
    static int[] parent;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        long result = 0;
        parent = new int[n+1];
        sc.nextLine();
        Queue<Node> pq = new PriorityQueue<>();
        for (int i = 1 ; i<= n ; i++){
            parent[i] = i;
            String[] line = sc.nextLine().split(" ");
            for (int j = 1 ; j <= n ; j++){
                int w = Integer.parseInt(line[j-1]);
                if (i == j) continue;
                else pq.offer(new Node(i,j,w));
            }
        }
        while (!pq.isEmpty()){
            Node curr = pq.poll();
            if (find(curr.from) != find(curr.to)){
                union(curr.from,curr.to);
                result += curr.cost;
            }
        }
        System.out.println(result);
    }

    private static void union(int a, int b) {
        int a_parent = parent[a];
        int b_parent = parent[b];
        if (parent[a_parent] < parent[b_parent]){
            parent[a_parent] = parent[b_parent];
        }else {
            parent[b_parent] = parent[a_parent];
        }
    }

    private static int find(int i) {
        if (parent[i] != i){
            parent[i] = find(parent[i]);
        }
        return parent[i];
    }

    private static class Node implements Comparable<Node>{
        int from;
        int to;
        int cost;

        public Node(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}
