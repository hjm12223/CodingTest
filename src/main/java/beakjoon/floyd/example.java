package beakjoon.floyd;

import java.util.Arrays;
import java.util.Scanner;

public class example {
    static int n,m;
    static int[][] dist;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        dist=  new int[n][n];
        for(int i = 0 ; i< n ; i++){
            for (int j = 0 ; j < n ; j++){
                if(i == j ) continue;
                dist[i][j] = 987654321;
            }
        }
        for (int i = 0 ; i< m ; i++){
            int from = sc.nextInt()-1;
            int to = sc.nextInt()-1;
            int cost = sc.nextInt();
            dist[from][to] = Math.min(dist[from][to],cost);

        }
        for (int k = 0 ; k < n ; k++){
            for (int i = 0; i< n; i++){
                for (int j = 0; j< n ; j++){
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        for (int[] ints : dist) {
            System.out.println();
            for (int anInt : ints) {
                if (anInt == 987654321) anInt =0;
                System.out.print(anInt + " ");
            }
        }
    }
}
