package beakjoon.dijkstra;

import java.util.*;

public class Boj1753 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt(); // 노드의 수
        int e = sc.nextInt(); // 간선의 수
        int k = sc.nextInt(); // 시작 정점의 번호

        int[][] graph = new int[e][3];

        for (int i = 0 ; i< e ; i++){
            graph[i][0] = sc.nextInt(); // 시작
            graph[i][1] = sc.nextInt(); // 끝
            graph[i][2] = sc.nextInt(); // 가중치
        }
        List<List<Node>> list = new ArrayList<>();

        for (int i = 0 ; i <= v ; i++)
            list.add(new ArrayList<>());

        for (int i = 0; i < e ; i++){
            int from = graph[i][0];
            int to = graph[i][1];
            int cost = graph[i][2];
            list.get(from).add(new Node(to,cost));
        }

        Queue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.c - o2.c;
            }
        });

        pq.offer(new Node(k,0));
        int[] dist = new int[v+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[0] = 0;
        dist[k] = 0;

        while (!pq.isEmpty()){
            Node curr = pq.poll();
            if (dist[curr.n] < curr.c) continue;
            for (int i = 0 ; i < list.get(curr.n).size(); i++){
                int nextN = list.get(curr.n).get(i).n;
                int nextC = curr.c+list.get(curr.n).get(i).c;
                if (dist[nextN] > nextC){
                    dist[nextN] = nextC;
                    pq.offer(new Node(nextN,nextC));
                }
            }
        }

        for (int i = 1 ; i <= v ; i++){
            if (dist[i] != Integer.MAX_VALUE) {
                System.out.println(dist[i]);
            }else {
                System.out.println("INF");
            }
        }
    }

    public static class Node {
        int n;
        int c;

        public Node(int n, int c) {
            this.n = n;
            this.c = c;
        }

    }
}
