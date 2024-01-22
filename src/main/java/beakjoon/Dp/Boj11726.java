package beakjoon.Dp;

import java.util.Arrays;
import java.util.Scanner;

public class Boj11726 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2 ; i < n; i++){
            dp[i] = (dp[i-1] + dp[i-2]) % 10_007;
        }
        System.out.println(dp[n-1]);
    }
}
