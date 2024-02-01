package beakjoon.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Boj4803 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int index = 1;
        while (true){
            int n = sc.nextInt(); // 노드의 수
            int m = sc.nextInt(); // 간선의 수
            if (n == 0 && m == 0) break;
            List<List<Integer>> tree = new ArrayList<>();
            for (int i = 0  ; i <= n ;i++)
                tree.add(new ArrayList<>());
            for (int i = 1  ; i <= m ;i++){
                int par = sc.nextInt();
                int child = sc.nextInt();
                tree.get(par).add(child);
                tree.get(child).add(par);
            }
            int trees = countTree(tree, n);

            if (trees == 0) {
                System.out.println("Case " + index + ": No trees.");
            } else if (trees == 1) {
                System.out.println("Case " + index + ": There is one tree.");
            } else {
                System.out.println("Case " + index + ": A forest of " + trees + " trees.");
            }
            index++;
        }
    }
    public static int countTree(List<List<Integer>> tree, int n){
        boolean[] visited = new boolean[n+1];
        int trees=0;
        for (int i = 1; i<= n; i++){
            if (!visited[i]){
                if (isTree(tree,visited,i,-1)){
                    trees++;
                }
            }
        }
        return trees;
    }

    private static boolean isTree(List<List<Integer>> tree, boolean[] visited, int node, int parent) {
        visited[node] = true;
        for (int nei : tree.get(node)){
            if (!visited[nei]){
                if (!isTree(tree,visited,nei,node)) return false;
            } else if (parent != nei) {
                return false;
            }
        }
        return true;
    }

}
