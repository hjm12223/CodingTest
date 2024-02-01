package beakjoon.tree;

import java.util.*;

public class Boj11725 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 노드의 수
        List<LinkedList<Integer>> tree = new ArrayList<>();
        for (int i = 1 ; i <= n+1 ; i++)
            tree.add(new LinkedList<>());
        for (int i = 1; i < n ; i++){
            int parent = sc.nextInt();
            int child = sc.nextInt();
            tree.get(parent).add(child);
            tree.get(child).add(parent);
        }
        int[] parent = new int[n+1];
        dfs(tree,parent,1,0);
        for (int i  =2 ; i<= n ;i++){
            System.out.println(parent[i]);
        }
        System.out.println("tree = " + tree);
        System.out.println("parent = " + Arrays.toString(parent));
    }

    private static void dfs(List<LinkedList<Integer>> tree, int[] parents, int node, int parent ) {
        parents[node] = parent;
        for (int child : tree.get(node)) {
            if (child != parent){
                dfs(tree,parents,child,node);
            }
        }
    }
}
