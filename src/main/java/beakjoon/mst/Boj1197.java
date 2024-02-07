package beakjoon.mst;

import java.util.*;

public class Boj1197 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt(); // 노드
        int e = sc.nextInt(); // 간선
        int[][] arr = new int[e][3];
        int[] parent = new int[v+1];

        int result = 0;
        for (int i = 0;  i< e ; i++){
            arr[i][0]= sc.nextInt();
            arr[i][1]= sc.nextInt();
            arr[i][2]= sc.nextInt();
        }
        for (int i = 0; i< parent.length; i ++)
            parent[i] = i;

        for (int i = 0;  i< arr.length;i++){
            int a = arr[i][0];
            int b = arr[i][1];
            if (find(parent,a) != find(parent,b)){
                union(parent,a,b);
                result += arr[i][2];
            }
        }
        System.out.println(result);
    }

    private static void union(int[] parent, int a, int b) {
        int a_parent = find(parent, a);
        int b_parent = find(parent, b);
        if (parent[a_parent] < parent[b_parent]){
            parent[b_parent] = parent[a_parent];
        }else {
            parent[a_parent] = parent[b_parent];
        }
    }

    private static int find(int[] parent, int i) {
        System.out.println(Arrays.toString(parent));
        if (parent[i] != i){
            parent[i] = find(parent,parent[i]);
        }
        return parent[i];
    }
}
