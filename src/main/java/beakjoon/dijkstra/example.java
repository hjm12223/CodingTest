package beakjoon.dijkstra;

import java.util.*;

public class example {
    public static void main(String[] args) {
        solution(6,new int[][]{{1,2,3},{1,3,2},{1,4,5},{2,3,2},{3,4,2},{4,5,6},{2,5,8},{5,6,1}});
    }

    private static int solution(int n, int[][] graph) {
        List<List<Node>> list = new ArrayList<>();
        for (int i = 0 ; i<= n ; i++)
            list.add(new ArrayList<>());

        for (int i = 0;  i < graph.length; i++){
            int from = graph[i][0];
            int to = graph[i][1];
            int value = graph[i][2];

            list.get(from).add(new Node(to,value));
            list.get(to).add(new Node(from,value));
        }
        int[] dist = new int[n+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[0] = dist[1]  =  0;

        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.c - o2.c;
            }
        });
        pq.offer(new Node(1,0));
        while (!pq.isEmpty()){
            Node curr = pq.poll();
            if (dist[curr.n] < curr.c) continue;
            for (int i = 0 ; i < list.get(curr.n).size(); i++){
                int nextN = list.get(curr.n).get(i).n;
                int nextC = Math.max(list.get(curr.n).get(i).c +  curr.c, curr.c);

                System.out.println();
                if (dist[nextN] > nextC){
                    dist[nextN] = nextC;
                    pq.offer(new Node(nextN,nextC));
                    continue;
                }
            }
        }
        System.out.println("dist = " + Arrays.toString(dist));
        return dist[dist.length-1];
    }

    private static class Node {
        int n;
        int c;

        public Node(int n, int c) {
            this.n = n;
            this.c = c;
        }

    }
}
