package beakjoon.mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Boj13418 {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        int[][] arr = new int[e+1][3];
        parent = new int[v+1];
        for (int i = 0; i< v ; i++){
            parent[i] = i;
        }
        for (int i = 0 ; i <= e ; i++ ){
            String[] parsed = br.readLine().split(" ");
            arr[i][0] = Integer.parseInt(parsed[0]);
            arr[i][1] = Integer.parseInt(parsed[1]);
            arr[i][2] = Integer.parseInt(parsed[2]);
        }

        Arrays.sort(arr,(o1, o2) ->
                o1[0] - o2[0]
                );
        int result = 0;
        for (int i = 0 ; i<= e ; i++){
            if (find(arr[i][0]) != find(arr[i][1])){
                union(arr[i][0],arr[i][1]);
                result += arr[i][2];
            }
        }
        System.out.println("result = " + result);
        System.out.println("arr = " + Arrays.deepToString(arr));
    }

    private static int find(int i) {
        if (parent[i] != i){
            parent[i] = find(parent[i]);
        }
        return parent[i];
    }
    private static void union(int a , int b){
        int parent_a = find(parent[a]);
        int parent_b = find(parent[b]);
        if (parent[parent_a] < parent[parent_b]){
            parent[parent_a] = parent[parent_b];
        }else {
            parent[parent_b] = parent[parent_a];
        }
    }
}
