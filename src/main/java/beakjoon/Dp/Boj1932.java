package beakjoon.Dp;

import java.util.Arrays;
import java.util.Scanner;

public class Boj1932 {

    static int[][] arr;
    static int[][] dp;
    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        arr = new int[N][];
        dp = new int[N][];


        for (int i = 0; i < N; i++) {
            arr[i] = new int[i + 1];
            dp[i] = new int[i +1];
            for (int j = 0; j <= i; j++)
                arr[i][j] = sc.nextInt();
        }
        dp[0][0] = arr[0][0];

        for (int i = 1; i < N ; i++){
            for (int j = 1;  j < arr[i].length; j++){
                dp[i][0] = arr[i][0] + dp[i-1][0]; // 왼쪽값은 쭉 왼쪽만
                dp[i][dp[i].length-1] = arr[i][arr[i].length-1] + dp[i-1][dp[i-1].length-1]; // 오른쪽값도 쭉 오른쪽만
                if (dp[i][j] == 0) {
                    dp[i][j] = Math.max(dp[i - 1][j - 1] + arr[i][j],dp[i-1][j] + arr[i][j]);
                }
            }
        }
        int max = Integer.MIN_VALUE;
        for (int value : dp[N - 1]) {
            if (max < value) max = value;
        }
        System.out.println(max);
    }
}
/*

 */
/**
 * 시간 제한	메모리 제한	제출	정답	맞힌 사람	정답 비율
 * 2 초	128 MB	88802	51704	39092	59.568%
 * 문제
 *         7
 *       3   8
 *     8   1   0
 *   2   7   4   4
 * 4   5   2   6   5
 * 위 그림은 크기가 5인 정수 삼각형의 한 모습이다.
 *
 * 맨 위층 7부터 시작해서 아래에 있는 수 중 하나를 선택하여 아래층으로 내려올 때,
 * 이제까지 선택된 수의 합이 최대가 되는 경로를 구하는 프로그램을 작성하라.
 * 아래층에 있는 수는 현재 층에서 선택된 수의 대각선 왼쪽 또는 대각선 오른쪽에 있는 것 중에서만 선택할 수 있다.
 *
 * 삼각형의 크기는 1 이상 500 이하이다. 삼각형을 이루고 있는 각 수는 모두 정수이며, 범위는 0 이상 9999 이하이다.
 *
 * 입력
 * 첫째 줄에 삼각형의 크기 n(1 ≤ n ≤ 500)이 주어지고, 둘째 줄부터 n+1번째 줄까지 정수 삼각형이 주어진다.
 *
 * 출력
 * 첫째 줄에 합이 최대가 되는 경로에 있는 수의 합을 출력한다.
 *
 * 예제 입력 1
 * 5
 * 7
 * 3 8
 * 8 1 0
 * 2 7 4 4
 * 4 5 2 6 5
 * 예제 출력 1
 * 30
 */