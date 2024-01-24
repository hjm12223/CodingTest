package beakjoon.Dp;

import java.util.Arrays;
import java.util.Scanner;

public class Boj9465 {
    public static void main(String[] args) {
        Scanner sc  = new Scanner(System.in);
        int n = 2;
        int testCase = sc.nextInt();

        /**
         * 경우의 수
         * dp[i-1][j+1] , dp[i-1][j-1], dp[i][j-2],
         * arr[i-1][j-1] + dp[i][j-2] + arr[i][j]
         * arr[i+1][j+1] + arr[i][j]
         */
        for (int k  = 0  ; k < testCase ; k++) {
            int column = sc.nextInt();
            int[][] arr = new int[n+1][column + 2];
            int[][] dp = new int[n+1][column + 2];
            for (int i = 1; i <= n; i++) {
                for (int j = 2; j <= column + 1; j++) {
                    arr[i][j] = sc.nextInt();
                }
                dp[1][1] = arr[1][1];
                dp[2][1] = arr[2][1];
                /*
                dp = [[0, 0, 0, 0, 0, 0, 0, 0],
                50 10 100 20 40
                30 50 70 10 60
             dp = [[0, 0, 0, 0, 0, 0, 0, 0],
             [0, 0, 50, 10, 150, 30, 190, 0],
             [0, 0, 30, 100, 110, 210, 190, 0]]

                 */

            }
            for (int j = 2; j <= column +1; j++) {
                dp[1][j] = Math.max(dp[2][j-1] , dp[2][j - 2]) + arr[1][j];
                dp[2][j] = Math.max(dp[1][j-1], dp[1][j - 2]) + arr[2][j];
            }
            System.out.println(Math.max(dp[1][column+1],dp[2][column+1]));
            }
        }
    }
