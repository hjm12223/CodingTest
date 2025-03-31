package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj18353 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[] dp = new int[N];
		int maxLength = 0;
		for (int k = 0; k < N; k++) {
			dp[k] = 1;
			for (int i = 0; i < k; i++) {
				if (arr[i] > arr[k]) {
					dp[k] = Math.max(dp[k], dp[i] + 1);
				}
			}
			maxLength = Math.max(maxLength, dp[k]);
		}
		System.out.println(N - maxLength);
	}
}