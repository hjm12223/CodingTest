package beakjoon.dijkstra;

import java.util.*;

public class Boj17835 {
    static int n;
    static List<List<Node>> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc  = new Scanner(System.in);
        n = sc.nextInt(); // 노드의 수
        int m = sc.nextInt(); // 간선의 수
        int k = sc.nextInt(); // 면접장의 수
        int[][] arr =new int[m][3];
        for (int i = 0; i< m ; i++){
            arr[i][0] = sc.nextInt();
            arr[i][1]= sc.nextInt();
            arr[i][2]= sc.nextInt();
        }
        for (int i = 0 ; i<= n ; i++)
            list.add(new ArrayList<>());

        for (int i = 0; i < m ; i++){
            int from = arr[i][0];
            int to = arr[i][1];
            int cost = arr[i][2];
            list.get(from).add(new Node(to,cost));
        }
        List<Integer> city = new ArrayList<>();
        sc.nextLine();
        String[] strings = sc.nextLine().split(" ");
        for (int i = 0; i< strings.length; i++){
            city.add(Integer.parseInt(strings[i]));
        }
        System.out.println("city = " + city);
        for (int i = 0 ; i< city.size(); i++){
            int place = city.get(i);
            for (int j = 1 ; j<= n ; j++){
                if (city.contains(j)) continue;
                System.out.println("j = " + j);
                System.out.println("place = " + place);
                int dijkstra = dijkstra(j, place);
                System.out.println("dijkstra = " + dijkstra);
                System.out.println();
            }
        }
    }

    private static int dijkstra(int start, int end) {
        Queue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start,0));
        int[] dist = new int[n+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[start] = dist[0] = 0;
        while (!pq.isEmpty()){
            Node curr = pq.poll();
            if (dist[curr.n] < curr.c) continue;
            for (int i = 0; i< list.get(curr.n).size() ; i++){
                int nextC = list.get(curr.n).get(i).c + curr.c;
                int nextN = list.get(curr.n).get(i).n;
                if (dist[nextN] > nextC){
                    dist[nextN] = nextC;
                    pq.offer(new Node(nextN,nextC));
                }
            }
        }
        return dist[end];

    }

    private static class Node implements Comparable<Node>{
        public Node(int n, int c) {
            this.n = n;
            this.c = c;
        }

        int n;

        int c;
        @Override
        public int compareTo(Node o){
            return this.c - o.c;
        }
    }
    /**
     * 마포구에는 모든 대학생이 입사를 희망하는 굴지의 대기업 ㈜승범이네 본사가 자리를 잡고 있다.
     * 승범이는 ㈜승범이네의 사장인데, 일을 못 하는 직원들에게 화가 난 나머지 전 직원을 해고하고 신입사원을 뽑으려 한다.
     * 1차 서류전형이 끝난 뒤 합격자들은 면접을 준비하게 되었다.
     *
     * 면접자들은 서로 다른 N개의 도시에 거주한다.
     * 승범이는 면접자들의 편의를 위해 거주 중인 N개 도시 중 K개의 도시에 면접장을 배치했다.
     * 도시끼리는 단방향 도로로 연결되며, 거리는 서로 다를 수 있다.
     * 어떤 두 도시 사이에는 도로가 없을 수도, 여러 개가 있을 수도 있다.
     * 또한 어떤 도시에서든 적어도 하나의 면접장까지 갈 수 있는 경로가 항상 존재한다.
     *
     * 모든 면접자는 본인의 도시에서 출발하여 가장 가까운 면접장으로 찾아갈 예정이다. 즉,
     * 아래에서 언급되는 '면접장까지의 거리'란 그 도시에서 도달 가능한 가장 가까운 면접장까지의 최단 거리를 뜻한다.
     *
     * 속초 출신 승범이는 지방의 서러움을 알기에 각 도시에서 면접장까지의 거리 중, 그 거리가 가장 먼 도시에서 오는 면접자에게 교통비를 주려고 한다.
     *
     * 승범이를 위해 면접장까지의 거리가 가장 먼 도시와 그 거리를 구해보도록 하자.
     */
}
