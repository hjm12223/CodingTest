package beakjoon.Dp;

import java.util.Arrays;
import java.util.Scanner;

public class Boj10844 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[][] dp = new long[n+1][10];

        long mod = 1_000_000_000;

        for (int i =1 ; i < 10; i++){
            dp[1][i] = 1;
        }
        for (int i = 2 ; i<= n ; i++){
            for(int j = 0 ; j <= 9; j++){
                if (j == 0) dp[i][j] = (dp[i-1][j+1] % mod );
                else if (j == 9) dp[i][j] = (dp[i-1][j-1] % mod);
                else dp[i][j] =  (dp[i-1][j-1] + dp[i-1][j+1]) % mod;
            }
        }
        long sum = 0;
        for (int i = 0 ; i < 10 ; i++){
            sum += dp[n][i];
        }
        System.out.println("dp = " + Arrays.deepToString(dp));

        System.out.println(sum);
    }
}
