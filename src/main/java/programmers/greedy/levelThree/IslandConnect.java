package programmers.greedy.levelThree;

import java.util.*;

public class IslandConnect {
    public static void main(String[] args) {
        int solution = solution(4, new int[][]{{0, 1, 1}, {0, 2, 2}, {1, 2, 5}, {1, 3, 1}, {2, 3, 8}});
        System.out.println("solution = " + solution);
    }
    //크루스칼 알고리즘 사용
    public static int solution(int n, int[][] costs) {
        List<Island> islandList = new LinkedList<>(); // 섬을 담을 리스트를 선언
        int totalCost = 0; // 총 간선들의 최소 비용 선언
        int[] parent = new int[n]; // 해당 노드들의 부모를 담을 parent 선언
        for (int i = 0 ; i < parent.length ; i++){
            parent[i] = i; // parent 안에는 해당 인덱스값들을 넣음
        }


        for (int i = 0; i < costs.length; i++) {
            Island islands = new Island(costs[i][0], costs[i][1], costs[i][2]);
            islandList.add(islands);
        }

        islandList.sort((o1, o2) -> Integer.compare(o1.cost,o2.cost)); // 해당 간선들의 cost 를 기준으로 오름차순 정렬


        for (Island island : islandList) { // 섬리스트에 저장된 섬들을 꺼내줌
            if (find(parent,island.island)  != find(parent,island.nextIsland)){ // 현재 섬의 부모와, 다음섬의 부모가 같지 않다면
                 union(parent, island.island, island.nextIsland); // 해당 노드들을 union 시켜줌
                totalCost+= island.cost; // 토탈 코스트를 더해준다
            }
        }

        return totalCost;
    }




    private static void union(int[] parent, int a,int b){
        int aParent = find(parent,a); // 변수 a 의 parent 를 선언
        int bParent = find(parent,b); // b 또한 선언
        if (aParent < bParent){ // 만약 a 보다 b 의 부모가 클경우
            parent[bParent] = parent[aParent]; // 해당 인덱스의 부모를 a의 부모로 선언
        }else {
            parent[aParent] = parent[bParent] ; // b의 부모가 더 작을 경우 a 의부모를 경우 b의 부모로 선언
        }
    }
    private static int find(int[] parent, int i) {
        if (parent[i] != i) {  // 만약 parent[i] 의 값이 해당 인덱스 값이 아닐경우
            parent[i] = find(parent, parent[i]); // 해당 인덱스의 부모를 찾음
        }
        return parent[i]; // 해당 인덱스의 부모를 리턴
    }
    /**
     * 이렇게 하는 이유는 초기의 자신이 부모가 될 수 있기때문이다.
     */
/*
 0, 1 = 1
 0, 2 = 2
 1, 3 = 1
 다익스트라 알고리즘 공부
 Prim 알고리즘
 */
    /**
     * 현재 위치를 기반
     */
    public static class Island{
        int island;
        int nextIsland;
        int cost;

        @Override
        public String toString() {
            return "Island{" +
                    "island=" + island +
                    ", nextIsland=" + nextIsland +
                    ", cost=" + cost +
                    '}';
        }

        public Island(int island, int nextIsland, int cost) {
            this.island = island;
            this.nextIsland = nextIsland;
            this.cost = cost;
        }
    }

}

/**
 * Cost[i][0] 첫번째 섬
 * Cost[i][1] 두번째 섬
 * cost[i][2] 연결된 섬의 편도 비용
 */
/**
 * 섬의 개수 n은 1 이상 100 이하입니다.
 * costs의 길이는 ((n-1) * n) / 2이하입니다.
 * 임의의 i에 대해, costs[i][0] 와 costs[i] [1]에는 다리가 연결되는 두 섬의 번호가 들어있고, costs[i] [2]에는 이 두 섬을 연결하는 다리를 건설할 때 드는 비용입니다.
 * 같은 연결은 두 번 주어지지 않습니다. 또한 순서가 바뀌더라도 같은 연결로 봅니다. 즉 0과 1 사이를 연결하는 비용이 주어졌을 때, 1과 0의 비용이 주어지지 않습니다.
 * 모든 섬 사이의 다리 건설 비용이 주어지지 않습니다. 이 경우, 두 섬 사이의 건설이 불가능한 것으로 봅니다.
 * 연결할 수 없는 섬은 주어지지 않습니다.
 */
