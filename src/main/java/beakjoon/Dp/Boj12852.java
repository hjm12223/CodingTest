package beakjoon.Dp;

import java.util.Arrays;
import java.util.Scanner;

public class Boj12852 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[n+1];
        int[] pre = new int[n+1];
        dp[1] = 0;
        for (int i = 2 ; i<= n ; i++){
            dp[i] = dp[i-1] +1;
            pre[i] = i-1;
            if (i % 2 == 0 && dp[i] > dp[i/2] +1){
                dp[i] = dp[i/2] +1;
                pre[i] = i/2;
            }
            if (i% 3 == 0 && dp[i] > dp[i/3] +1){
                dp[i] = dp[i/3] +1;
                pre[i]= i/3;
            }
        }
        int cur  = n;
        System.out.println(dp[n]);
        System.out.println(Arrays.toString(dp));
        System.out.println(Arrays.toString(pre));
        while (true) {
            System.out.print(cur+ " ");
            if (cur == 1) break;
        cur = pre[cur];
        }
    }
}
