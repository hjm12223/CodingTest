package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1965 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int[] box = new int[N + 1];
		int[] dp = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		dp[0] = 0;
		for (int i = 1; i <= N; i++) {
			box[i] = Integer.parseInt(st.nextToken());
			dp[i] = 1;
		}
		int result = 0;
		for (int i = 1; i <= N; i++) {
			int value = 0;
			for (int j = i - 1; j >= 1; j--) {
				if (box[i] > box[j]) {
					value = Math.max(value, dp[j]);
				}
			}
			dp[i] += value;
			result = Math.max(dp[i], result);
		}
		System.out.println(result);
	}
}
