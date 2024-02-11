package beakjoon.dijkstra;

import java.util.*;

public class Boj1238 {
    static int n;
    static int m;

    static List<List<Node>> list = new ArrayList<>();

    static List<Integer> result=  new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
         n = sc.nextInt(); //  노ㄷ의 갯수
         m = sc.nextInt(); // 간선의 갯수
        int x = sc.nextInt(); // 도착지 마을
        int[][] arr = new int[m][3];
        for (int i = 0 ; i< m ; i++){
            arr[i][0] =sc.nextInt();
            arr[i][1] =sc.nextInt();
            arr[i][2] =sc.nextInt();
        }
        for (int i = 0; i <= n; i++)
            list.add(new ArrayList<>());
        for (int i = 0; i < arr.length;i++){
            int from = arr[i][0];
            int to = arr[i][1];
            int cost = arr[i][2];
            list.get(from).add(new Node(to,cost));
        }
        for (int i = 1; i <= n ; i++) {
            if (i==x) continue;
            result.add(runDijkstra(i,x));
        }
        for (int i = 1; i <= n ; i++) {
            if (i==x) continue;
            result.add(runDijkstra(x,i));
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0 ; i <= list.size()/ 2; i++){
            max = Math.max(max,result.get(i) + result.get(i+n-1));
        }
        System.out.println(max);
    }

    private static int runDijkstra(int start, int end) {
        int[] dist = new int[n+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[0] = dist[start] = 0;
        Queue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start,0));
        while (!pq.isEmpty()){
            Node curr = pq.poll();
            if (dist[curr.n] > curr.c) continue;
            for (int i = 0; i<list.get(curr.n).size();i++){
                int nextC = curr.c + list.get(curr.n).get(i).c;
                int nextN = list.get(curr.n).get(i).n;
                if (dist[nextN] > nextC){
                    dist[nextN] = nextC;
                    pq.offer(new Node(nextN,nextC));
                }
            }
        }
        return dist[end];
    }


    private static class Node implements Comparable<Node> {
        int n;
        int c;

        @Override
        public String toString() {
            return "Node{" +
                    "n=" + n +
                    ", c=" + c +
                    '}';
        }

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
