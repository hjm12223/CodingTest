package beakjoon.dijkstra;

import java.util.*;

public class Boj1916 {
    static final int DEFAULT_VALUE = Integer.MAX_VALUE;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 노드의 수
        int e = sc.nextInt(); // 간선의 수
        int[][] arr = new int[e][3];
        for (int i = 0 ; i< e ; i++){
            arr[i][0] = sc.nextInt(); // 시작
            arr[i][1] = sc.nextInt(); // 끝
            arr[i][2] = sc.nextInt(); // 가중치
        }
        List<List<Node>> list  =new ArrayList<>();
        for (int i = 0 ; i<= n  ; i++){
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < e ; i++){
            int from = arr[i][0];
            int to = arr[i][1];
            int cost = arr[i][2];
            list.get(from).add(new Node(to,cost));
        }
        int start = sc.nextInt();
        int end = sc.nextInt();

        Queue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start,0));
        int[] dist = new int[n+1];
        Arrays.fill(dist,DEFAULT_VALUE);
        dist[0] = dist[start] = 0;

        while (!pq.isEmpty()){
            Node curr = pq.poll();
            if (dist[curr.n] < curr.c) continue;
            for (int i = 0 ; i < list.get(curr.n).size() ; i++){
                int nextN = list.get(curr.n).get(i).n;
                int nextC = list.get(curr.n).get(i).c + curr.c ;
                if (dist[nextN] > nextC){
                    dist[nextN] = nextC;
                    pq.offer(new Node(nextN,nextC));
                }
            }
        }
        System.out.println(dist[end]);
    }

    private static class Node  implements  Comparable<Node>{
        int n;
        int c;
        @Override
        public int compareTo(Node o ){
            return this.c - o.c;
        }

        public Node(int n, int c) {
            this.n = n;
            this.c = c;
        }
    }
    /**
     * N개의 도시가 있다. 그리고 한 도시에서 출발하여 다른 도시에 도착하는 M개의 버스가 있다.
     * 우리는 A번째 도시에서 B번째 도시까지 가는데 드는 버스 비용을 최소화 시키려고 한다
     * A번째 도시에서 B번째 도시까지 가는데 드는 최소비용을 출력하여라.
     * 도시의 번호는 1부터 N까지이다.
     */
}

