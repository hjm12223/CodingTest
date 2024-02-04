package beakjoon.dijkstra;

import java.util.*;

public class Boj11779 {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        int v = sc.nextInt();
        int e = sc.nextInt();
        int[][] graph = new int[e][3];
        List<Integer> set = new ArrayList<>();
        List<List<Node>> list = new ArrayList<>();
        int[] predecessor = new int[v+1]; // To store the predecessor of each node

        for (int i = 0 ; i <= v; i++)
            list.add(new ArrayList<>());

        for (int i = 0;  i< e; i++){
            graph[i][0] = sc.nextInt(); // 시작
            graph[i][1] = sc.nextInt(); // 끝
            graph[i][2] = sc.nextInt(); // 가중치
        }

        int start = sc.nextInt();
        int end = sc.nextInt();

        for (int i = 0 ; i< e ; i++){
            int from = graph[i][0];
            int to = graph[i][1];
            int cost = graph[i][2];
            list.get(from).add(new Node(to,cost));
        }
        int[] dist  = new int[v+1];
        Arrays.fill(dist,Integer.MAX_VALUE);

        dist[start] = dist[0]  = 0;


        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.offer(new Node(start,0));
        while (!pq.isEmpty()){
            Node curr = pq.poll();
            if (dist[curr.n] < curr.c) continue;
            for (int i = 0 ; i < list.get(curr.n).size(); i++){
                int nextN = list.get(curr.n).get(i).n;
                int nextC = list.get(curr.n).get(i).c +  curr.c;

                if (dist[nextN] > nextC) {
                    dist[nextN] = nextC;
                    predecessor[nextN] = curr.n;
                    pq.offer(new Node(nextN, nextC));
                }
            }
        }
        List<Integer> path = new ArrayList<>();
        int current = end;
        System.out.println("predecessor = " + Arrays.toString(predecessor));
        while (current != 0) {
            path.add(current);
            current = predecessor[current];
        }
        Collections.reverse(path);
        System.out.println(dist[end]);
        System.out.println(path.size());
        for (Integer integer : path) {
            System.out.print(integer + " ");
        }
    }

    /**
     * 첫째 줄에 출발 도시에서 도착 도시까지 가는데 드는 최소 비용을 출력한다.
     *
     * 둘째 줄에는 그러한 최소 비용을 갖는 경로에 포함되어있는 도시의 개수를 출력한다. 출발 도시와 도착 도시도 포함한다.
     *
     * 셋째 줄에는 최소 비용을 갖는 경로를 방문하는 도시 순서대로 출력한다.
     */
    private static class Node implements Comparable<Node> {
        int n;
        int c;

        public Node(int n, int c) {
            this.n = n;
            this.c = c;
        }


        @Override
        public int compareTo(Node o) {
            return this.c - o.c;
        }
    }
}
