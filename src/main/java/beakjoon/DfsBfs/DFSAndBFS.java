package beakjoon.DfsBfs;

import java.util.*;

public class DFSAndBFS {

    static List<Integer> dfsList = new ArrayList<>();
    static List<Integer> bfsList = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] strings = scan.nextLine().split(" ");

        int n = Integer.parseInt(strings[0]);
        int m = Integer.parseInt(strings[1]);
        int v = Integer.parseInt(strings[2]);

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n ; i++){
            graph.add(new ArrayList<>());
        }
        for (int i = 0 ; i <m ; i++){
            int vertex1 = scan.nextInt(); // 본인
            int vertex2 = scan.nextInt(); // 옆 노드
            graph.get(vertex1).add(vertex2);
            graph.get(vertex2).add(vertex1);
        }
        for(List<Integer> vertices : graph){
            Collections.sort(vertices);
        }


        dfs(v,graph,new boolean[n+1]);
        System.out.println("dfsList = " + dfsList);
    }

    private static void dfs(int v, List<List<Integer>> graph, boolean[] visited) {
        visited[v] = true;
        dfsList.add(v);

        for (int neighbor : graph.get(v)){
            if (!visited[neighbor]){
                visited[neighbor] = true;
                dfs(v+1,graph,visited);
                visited[neighbor] = false;
            }
        }
    }


    public static class Node{
        int start;
        int end;

        @Override
        public String toString() {
            return "Node{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
