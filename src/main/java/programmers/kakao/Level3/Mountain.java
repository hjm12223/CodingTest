package programmers.kakao.Level3;

import java.util.Arrays;

public class Mountain {
    public static void main(String[] args) {
        int solution = solution(10, new int[]{0,0,0,0,0,0,0,0,0,0, 0});
        System.out.println("solution = " + solution);
    }
    public static int solution(int n, int[] tops) {
        int mod = 10_007;
        int[][] dp = new int[n+1][2];
        dp[0][0] = 1;
        dp[1][0] = 3;
        dp[1][1] = 4;
        /*
        n = 1 일때는 경우의 수 1
        n = 2 일때는 경우의 수 3
        n = 2 일때 top 이 존재할 경우 4개

        경우의 수가 4가지 있다
        첫번째 마름모와 두번째 마름모는 해당 tops 가 없어도 사용가능하지만
        세번째 마름모는 tops 가 없으면 사용이 불가능하다 그렇기 때문에

        tops의 존재 여부를 확인해줘야한다
         */
        System.out.println("dp = " + Arrays.deepToString(dp));
        for (int i = 0; i < n; i++) {
            if (tops[i] == 1) {
                dp[i + 1][1] = (dp[i][0] * 3 + dp[i][1] * 2) % mod;
            } else {
                dp[i + 1][0] = (dp[i][0] * 2 + dp[i][1]) % mod;
            }
        }
        System.out.println("dp = " + Arrays.deepToString(dp));
        return (dp[n][0] + dp[n - 1][0]) % mod;
    }


    }
