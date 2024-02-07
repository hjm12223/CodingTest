package beakjoon.mst;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1368 {
    static int[] parent;
    public static void main(String[] args) throws IOException {
        int result = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        parent = new int[n+1];
        int[] ws  = new int[n+1];
        Queue<Node> pq = new PriorityQueue<>();
        for (int i = 1 ; i <= n ; i ++){
            st = new StringTokenizer(br.readLine());
            ws[i] = Integer.parseInt(st.nextToken()); // i번쩨 마을에 우물을 팔때의 비용
            parent[i] = i;
        }
        for (int i = 1 ; i<= n ; i ++){
            System.out.println(pq);
            String[] line = br.readLine().split(" ");
            for (int j = 1 ; j <= n ;j++){
                int w = Integer.parseInt(line[j-1]);
                if (i==j) pq.add(new Node(0,i,ws[i]));
                else if (i<j) pq.add(new Node(i,j,w));
            }
        }

        while (!pq.isEmpty()){
            Node curr = pq.poll();
            if (find(curr.from) != find(curr.to)){
                union(curr.from,curr.to);
                result+= curr.cost;
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

    public static class Node implements Comparable<Node>{
        @Override
        public String toString() {
            return "Node{" +
                    "from=" + from +
                    ", to=" + to +
                    ", cost=" + cost +
                    '}';
        }

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
