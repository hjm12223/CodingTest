package beakjoon.Dp;

import java.util.Scanner;

public class Boj9095_2 {
    static int[] dp = new int[11];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int tc = sc.nextInt();
        dp[1] =1;
        dp[2] =2;
        dp[3] =4;
        for(int i = 4 ; i<= 10 ; i++){
         dp[i] = dp[i-3] + dp[i-2] + dp[i-1];
        }
        for (int i = 0 ; i< tc ; i++){
            System.out.println(dp[sc.nextInt()]);
        }
    }
}
