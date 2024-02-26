package programmers.kakao.Level3;

import java.util.*;

public class Taxi {
    public static void main(String[] args) {
//        System.out.println(solution(6,4,6,2, new int[][]{{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}}));
//        int solution = solution(6, 4, 5, 6, new int[][]{{2, 6, 6}, {6, 3, 7}, {4, 6, 7}, {6, 5, 11}, {2, 5, 12}, {5, 3, 20}, {2, 4, 8}, {4, 3, 9}});
        System.out.println(solution(6,4,5,6,new int[][]{{2, 6, 6}, {6, 3, 7}, {4, 6, 7}, {6, 5, 11}, {2, 5, 12}, {5, 3, 20}, {2, 4, 8}, {4, 3, 9}}));
//        System.out.println("solution = " + solution);
    }
    static int size;
    static  List<List<Node>> list = new ArrayList<>();
    static Queue<Node> q;
    static int[] dist;
    static int answer = 0;
    public static int solution(int n, int s, int a, int b, int[][] fares) {

        size = n;
        for (int i = 0 ; i <= n ; i++)
            list.add(new ArrayList<>());
        for (int i = 0 ; i < fares.length ; i++){
            int from = fares[i][0];
            int to = fares[i][1];
            int value = fares[i][2];
            list.get(from).add(new Node(to,value));
            list.get(to).add(new Node(from,value));
        }

        q = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.value - o2.value;
            }
        });
        int divisionValue = Integer.MAX_VALUE;
        for (int i = 1 ; i<= n ; i++){
            int sTOi = dijkstra(s, i);
            int iToa = dijkstra(i, a);
            int iTob = dijkstra(i, b);
            int division = sTOi + iToa + iTob;
            divisionValue = Math.min(divisionValue, division);
        }
        int aPath = dijkstra(s, a);
        int bPath = dijkstra(s, b);
        int a_bPath = dijkstra(a,b);
        int b_aPath = dijkstra(b,a);


        int sTobToa = bPath + b_aPath;
        int sToaTob = aPath + a_bPath;
        int sToaAndsTob = aPath + bPath;
        answer = Math.min(Math.min(sTobToa,Math.min(sToaTob,sToaAndsTob)),divisionValue);
        return answer;
    }

    private static int dijkstra(int start, int target) {
        dist = new int[size+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        q.offer(new Node(start,0));
        dist[start] = dist[0]  =0;
        while (!q.isEmpty()){
            Node curr = q.poll();
            int currN = curr.node;
            if (dist[currN] < curr.value) continue;
            for (int i = 0 ; i< list.get(currN).size(); i++) {
                Node node = list.get(currN).get(i);
                int nextN = node.node;
                int nextV = node.value + curr.value;
                if (dist[nextN] > nextV) {
                    dist[nextN] = nextV;
                    q.offer(new Node(nextN, nextV));
                }
            }
        }
        return dist[target];
    }

    private static class Node{
        @Override
        public String toString() {
            return "Node{" +
                    "node=" + node +
                    ", value=" + value +
                    '}';
        }

        public Node(int node, int value) {
            this.node = node;
            this.value = value;
        }

        int node;
        int value;

    }
}
