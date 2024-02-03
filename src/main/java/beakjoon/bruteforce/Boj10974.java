package beakjoon.bruteforce;

import java.util.Arrays;
import java.util.Scanner;

public class Boj10974 {
    static boolean[] visited;
    public static void main(String[] args) {
        Scanner sc  = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        int[] out = new int[n];
        visited = new boolean[n];
        for (int i = 0;  i< n; i++){
            arr[i] = i+1;
        }
            permu(0, arr,arr.length, out);
    }

    private static void permu(int depth, int[] arr, int r, int[] out) {
        if (depth == r){
            for (int i : out) {
                System.out.print(i + " ");
            }
            System.out.println();
            return;
        }
        for (int i = 0 ; i < r ; i++){
            if (!visited[i]){
                out[depth] = arr[i];
                visited[i] = true;
                permu(depth+1, arr, r, out);
                visited[i] = false;
            }
        }
    }
}
