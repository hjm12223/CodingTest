package beakjoon.Dp;

import java.util.Arrays;
import java.util.Scanner;

public class Boj9095 {
    static int[] arr = new int[]{1, 2, 3};
    static int[] result;
    static int n, cnt;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();
        for (int i = 0; i < testCase; i++) {
            n = sc.nextInt();
            for (int j = 0; j < n; j++) {
                result = new int[j];
                permu(0);
            }
        }

    }

    private static void permu(int depth) {
            if (depth == result.length){
                int sum = 0;
                for (int i = 0 ; i< result.length;i++){
                    sum += result[i];
                    System.out.println("result = " + Arrays.toString(result));
                }
                if (sum == n) cnt++;
                return;
            }

            for (int i = 0 ; i < arr.length; i++){
                result[depth] = arr[i];
                permu(depth+1);
            }
    }
}
/*
 3 -> 1 + 1 + 1 , 1 + 2, 2 + 1 , 3
 */