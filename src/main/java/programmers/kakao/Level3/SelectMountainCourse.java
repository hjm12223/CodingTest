package programmers.kakao.Level3;

import java.util.*;

public class SelectMountainCourse {
    public static void main(String[] args) {
        int[] solution = solution(6, new int[][]{{1, 2, 3}, {2, 3, 5}, {2, 4, 2}, {2, 5, 4}, {3, 4, 4}, {4, 5, 3}, {4, 6, 1}, {5, 6, 1}}, new int[]{1, 3}, new int[]{5});
        System.out.println("solution = " + Arrays.toString(solution));

    }

    /* 양방향 다익스트라 알고리즘 간선은 이동거리 , 노드는 해당 번호의 위치

    입구를 통해 산봉우리에 도달하고 다시 해당 입구로 돌아오는 로직을 게산할때

    각 노드마다의 거리를 최소화한 값중 가는 시간이 최대로 걸리는 시간과 산봉우리의 값을 배열로 리턴해주면 됌.

    리턴값은 산봉우리에 도달할때까지의 시간중 가장 긴 시간

    제약조건 1. 1번 게이트로 들어왔으면 1번게이트로 나와야한다
        ex) 1-2-5-6-4-3-2-1 과 같은 등산코스는 코스의 처음과 끝 외에 3번 출입구를 방문하기 때문에 잘못된 등산코스입니다.
    제약조건 2. 산봉우리 또한 한번만 포함되어야 한다

    gates =  해당 산의 입구
    sumiits = 해당 산의 산봉우리

    * */
    public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        List<List<Node>> graph = new ArrayList<>();

        boolean[] isMountain = new boolean[n];

        for (int i = 0; i <= n; i++)
            graph.add(new ArrayList<>());
        for (int i = 0; i < paths.length; i++) {
            int from = paths[i][0];
            int to = paths[i][1];
            int value = paths[i][2];
            graph.get(from).add(new Node(to,value));
            graph.get(to).add(new Node(from,value));
        }
        for (int i = 0 ; i < summits.length; i++){
            isMountain[summits[i]] = true;
        }

        int[] dist = new int[n+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        PriorityQueue<Node> q = new PriorityQueue<>();
        for (int i = 0 ; i < gates.length; i++){
            dist[gates[i]] = 0;
            q.add(new Node(gates[i],0));
        }

        while (!q.isEmpty()){
            Node curr = q.poll();
            if (isMountain[curr.node]) continue;
            if (dist[curr.node] < curr.node) continue;
            for (int i = 0 ; i < graph.get(curr.node).size(); i++){
                int nextNode = graph.get(curr.node).get(i).node;
                int nextCost = Math.max(graph.get(curr.node).get(i).cost, curr.cost);
            if (dist[nextNode] > nextCost){
                dist[nextNode] = nextCost;
                q.add(new Node(nextNode,nextCost));
            }
            }
        }
        int resultCost = Integer.MAX_VALUE;
        int resultM = 0;
        for (int i = 0 ;i < summits.length; i++){
            if (dist[summits[i]] < resultCost){
                resultCost = dist[summits[i]];
                resultM = summits[i];
            }
        }
            return new int[]{resultM,resultCost};
    }


    public static class Node implements Comparable<Node> {
        @Override
        public String toString() {
            return "Node{" +
                    "node=" + node +
                    ", cost=" + cost +
                    '}';
        }

        int node;
        int cost;

        public Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}
/**
 * XX산은 n개의 지점으로 이루어져 있습니다.
 * 각 지점은 1부터 n까지 번호가 붙어있으며,
 * 출입구, 쉼터, 혹은 산봉우리입니다.
 * 각 지점은 양방향 통행이 가능한 등산로로 연결되어 있으며,
 * 서로 다른 지점을 이동할 때 이 등산로를 이용해야 합니다. 이때, 등산로별로 이동하는데 일정 시간이 소요됩니다.
 *
 * 등산코스는 방문할 지점 번호들을 순서대로 나열하여 표현할 수 있습니다.
 * 예를 들어 1-2-3-2-1 으로 표현하는 등산코스는 1번지점에서 출발하여 2번, 3번, 2번, 1번 지점을 순서대로 방문한다는 뜻입니다.
 * 등산코스를 따라 이동하는 중 쉼터 혹은 산봉우리를 방문할 때마다 휴식을 취할 수 있으며,
 * 휴식 없이 이동해야 하는 시간 중 가장 긴 시간을 해당 등산코스의 intensity라고 부르기로 합니다.
 *
 * 당신은 XX산의 출입구 중 한 곳에서 출발하여 산봉우리 중 한 곳만 방문한 뒤 다시 원래의 출입구로 돌아오는 등산코스를 정하려고 합니다.
 * 다시 말해, 등산코스에서 출입구는 처음과 끝에 한 번씩, 산봉우리는 한 번만 포함되어야 합니다.
 * 당신은 이러한 규칙을 지키면서 intensity가 최소가 되도록 등산코스를 정하려고 합니다.
 *
 * 위 그림에서 원에 적힌 숫자는 지점의 번호를 나타내며, 1, 3번 지점에 출입구, 5번 지점에 산봉우리가 있습니다.
 * 각 선분은 등산로를 나타내며,
 * 각 선분에 적힌 수는 이동 시간을 나타냅니다.
 * 예를 들어 1번 지점에서 2번 지점으로 이동할 때는 3시간이 소요됩니다.
 */