package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1106 {
	static final int max = 1_001 * 1_001;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int C = par(st.nextToken()); // 목표값을 만들기 위해서 최소로 드는 코스트를 구하는 문제
		int result = Integer.MAX_VALUE;
		int N = par(st.nextToken());
		int[] people = new int[N + 1];
		int[] cost = new int[N + 1];
		int[] dp = new int[max + 1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			cost[i] = par(st.nextToken());
			people[i] = par(st.nextToken());
		}
		for (int i = 0; i < N; i++) { // 도시
			for (int j = cost[i]; j <= max; j++) { // 비용
				dp[j] = Math.max(dp[j], dp[j - cost[i]] + people[i]);
				if (dp[j] >= C) {
					result = Math.min(j, result);
				}
			}
		}
		System.out.println(result);
	}

	private static int par(String st) {
		return Integer.parseInt(st);
	}
}
