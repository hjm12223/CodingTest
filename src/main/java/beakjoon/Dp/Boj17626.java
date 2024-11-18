package beakjoon.Dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj17626 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int[] dp = new int[N + 1];
		// 초기값 설정
		dp[0] = 0;
		dp[1] = 1;

		// 1부터 n까지 dp 배열 채우기
		for (int i = 2; i <= N; i++) {
			int min = Integer.MAX_VALUE;
			dp[i] = i;  // 최악의 경우 모든 제곱수가 1일 때 i개의 제곱수가 필요
			for (int k = 1; k * k <= i; k++) {
				int temp = i - k * k;
				min = Math.min(min, dp[temp]);
			}
			dp[i] = min + 1;
		}

		System.out.println(dp[N]);
	}
}
