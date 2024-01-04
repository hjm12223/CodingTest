package programmers.greedy.levelThree;

import java.util.*;

public class IslandConnect {
    public static void main(String[] args) {
        int solution = solution(4, new int[][]{{0, 1, 1}, {0, 2, 2}, {1, 2, 5}, {1, 3, 1}, {2, 3, 8}});
        System.out.println("solution = " + solution);
    }

    public static int solution(int n, int[][] costs) {
        List<Island> islandList = new LinkedList<>();
        int totalCost = 0;
        int[] parent = new int[n];
        for (int i = 0 ; i < parent.length ; i++){
            parent[i] = i;
        }


        for (int i = 0; i < costs.length; i++) {
            Island islands = new Island(costs[i][0], costs[i][1], costs[i][2]);
            islandList.add(islands);
        }

        islandList.sort(Comparator.comparingInt(o -> o.cost));


        for (Island island : islandList) {
            if (find(parent,island.island) != find(parent,island.nextIsland)){
                union(parent, island.island, island.nextIsland);
                totalCost+= island.cost;
            }
        }

        return totalCost;
    }




    private static void union(int[] parent, int a,int b){
        int aParent = find(parent,a);
        int bParent = find(parent,b);
        if (aParent < bParent){
            parent[bParent] = parent[aParent];
        }else {
            parent[aParent] = parent[bParent] ;
        }
    }
    private static int find(int[] parent, int i) {
        if (parent[i] != i) {
            parent[i] = find(parent, parent[i]);
        }
        return parent[i];
    }
/*
 0, 1 = 1
 0, 2 = 2
 1, 3 = 1

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
