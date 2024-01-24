package beakjoon.Dp;

import java.util.Arrays;
import java.util.Scanner;

public class Boj11057 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[][] dp = new long[n+1][10];
        int mod = 10_007;

        for (int i = 0 ; i<= 9 ; i++){
            dp[1][i] = 1;
        }
        for (int i = 2 ; i<= n ; i++){
            for (int j = 0 ; j <= 9 ; j++){
                for (int k = 0 ; k<=j ;k++) {
                    dp[i][j] = (dp[i][j] + dp[i-1][k]) % mod;
                }
            }
        }
        long result = 0;
        for (int i = 0; i <= 9; i++) {
            result = (result + dp[n][i]) % mod;
        }

        System.out.println(result);
    }
    /*
           0 1 2 3 4 5 6 7 8 9
        1  1 1 1 1 1 1 1 1 1 1
        2  1 2 3 4 5 6 7 8 9 10
        3  1 3 6 10 15 21 28 36 45 55

     */
}
