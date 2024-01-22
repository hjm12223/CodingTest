package beakjoon.Dp;

import java.util.*;

public class Boj1463 {
    static Integer[] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        dp = new Integer[N + 1];
        dp[0] = dp[1] = 0;
        System.out.println(recur(N));
        System.out.println(Arrays.toString(dp));
    }

    private static int recur(int n) {
        if (dp[n] == null) {
            if (n % 6 == 0) {
                dp[n] = Math.min(recur(n - 1), Math.min(recur(n / 3), recur(n / 2))) + 1;
            }
            // 3으로만 나눠지는 경우
            else if (n % 3 == 0) {
                dp[n] = Math.min(recur(n / 3), recur(n - 1)) + 1;
            }
            // 2로만 나눠지는 경우
            else if (n % 2 == 0) {
                dp[n] = Math.min(recur(n / 2), recur(n - 1)) + 1;
            }
            // 2와 3으로 나누어지지 않는 경우
            else {
                dp[n] = recur(n - 1) + 1;
            }
        }
        return dp[n];
    }
}
/**
 * X가 3으로 나누어 떨어지면, 3으로 나눈다.
 * X가 2로 나누어 떨어지면, 2로 나눈다.
 * 1을 뺀다.
 */
