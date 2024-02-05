package beakjoon.dijkstra;

import java.util.*;

public class Boj1504 {
    static int n;
    static List<List<Node>> list = new ArrayList<>();

    final static int checkValue = Integer.MAX_VALUE;
    static boolean[] isVisited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt(); // 노드의 수
        int m = sc.nextInt(); // 간선의 수
        int[][] arr = new int[m][3];

        for (int i= 0 ; i < m ;  i++){
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
            arr[i][2] = sc.nextInt();
        }
        int first = sc.nextInt();
        int second = sc.nextInt();

        isVisited = new boolean[n+1];

        isVisited[first] = true;
        isVisited[second] = true;

        for (int i = 0 ; i<= n ; i++)
            list.add(new ArrayList<>());

        for (int i = 0 ; i < arr.length; i++){
            int from = arr[i][0];
            int to = arr[i][1];
            int cost = arr[i][2];
            list.get(from).add(new Node(to,cost));
            list.get(to).add(new Node(from,cost));
        }

        long ans1=  0;
        long ans2 = 0;
        ans1 +=dijkstra(1,first);
        ans1 +=dijkstra(first,second);
        ans1 +=dijkstra(second,n);

        ans2 +=dijkstra(1,second);
        ans2 +=dijkstra(second,first);
        ans2 +=dijkstra(first,n);

        if (ans1 >= checkValue && ans2>= checkValue) System.out.println(-1);
        else {
            System.out.println(Math.min(ans1, ans2));
        }
    }

    private static long dijkstra(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start,0));
        int[] dist = new int[n+1];
        Arrays.fill(dist,checkValue);
        dist[0] = dist[start] = 0;

        while (!pq.isEmpty()){
            Node curr = pq.poll();
            if (dist[curr.n] < curr.c) continue;
            for (int i = 0 ; i< list.get(curr.n).size();i++){
                int nextC = list.get(curr.n).get(i).c + curr.c;
                int nextN = list.get(curr.n).get(i).n;
                if (dist[nextN] > nextC ){
                    dist[nextN] = nextC;
                    pq.offer(new Node(nextN,nextC));
                }
            }
        }
        return dist[end];
    }

    private static class Node implements Comparable<Node> {
        @Override
        public String toString() {
            return "Node{" +
                    "n=" + n +
                    ", c=" + c +
                    '}';
        }

        int n;
        int c;
        @Override
        public int compareTo(Node o){
            return this.c - o.c;
        }

        public Node(int n, int c) {
            this.n = n;
            this.c = c;
        }
    }
}
