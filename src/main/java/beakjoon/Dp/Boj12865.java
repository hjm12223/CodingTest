package beakjoon.Dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj12865 {
	public static void main(String[] args) throws IOException {
		// 완탐시 2^100

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(st.nextToken()); // 물품의 수
		int K = Integer.parseInt(st.nextToken()); // 버틸 수 있는 무게

		int[][] arr = new int[N + 1][2];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken()); // 물건의 무게
			arr[i][1] = Integer.parseInt(st.nextToken()); // 물건의 가치
		}
		int[][] dp = new int[N + 1][K + 1];
		for (int i = 1; i <= N; i++) {
			for (int weight = 1; weight <= K; weight++) { // 무게를 확인
				if (arr[i][0] <= weight) {
					dp[i][weight] = Math.max(dp[i - 1][weight], dp[i - 1][weight - arr[i][0]] + arr[i][1]);
				} else {
					dp[i][weight] = dp[i - 1][weight];
				}
			}
		}
		bw.write(String.valueOf(dp[N][K]) + "\n");
		bw.flush();
		bw.close();
	}
}
