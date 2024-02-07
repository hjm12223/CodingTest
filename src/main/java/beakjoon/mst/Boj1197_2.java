package beakjoon.mst;

import java.util.*;
import java.io.*;

public class Boj1197_2 {
    static boolean[] isVisited;
    static List<Node>[] list;

    public static void main(String[] args) throws  IOException {
        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        list = new List[v+1];
        isVisited = new boolean[v + 1];
        int[][] arr = new int[e][3];

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            arr[i][2] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i <= v; i++) {
            list[i] = new ArrayList<>();
        }
            for (int i = 0; i < e; i++) {
                int from = arr[i][0];
                int to = arr[i][1];
                int value = arr[i][2];
                list[from].add(new Node(to, value));
                list[to].add(new Node(from, value));
            }
            prim();
           br.close();
    }

    private static void prim() {
        Queue<Node> pq = new PriorityQueue<>();
        int answer = 0;
        pq.offer(new Node(1,0));
        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            if (!isVisited[curr.n]){
                isVisited[curr.n] = true;
                answer += curr.c;
                }
            for (Node node :list[curr.n]){
                if (!isVisited[node.n]) {
                    pq.offer(node);
                }
            }
        }
        System.out.println(answer);
    }


    private static class Node implements Comparable<Node> {
        public Node(int n, int c) {
            this.n = n;
            this.c = c;
        }

        int n;
        int c;

        @Override
        public int compareTo(Node o) {
            return this.c - o.c;
        }
    }
}