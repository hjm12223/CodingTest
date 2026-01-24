package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Boj12026_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		char[] arr = br.readLine().toCharArray();
		Map<Character, Character> map = new HashMap<>();
		char[] orders = new char[] {'B', 'O', 'J'};
		for (int i = 0; i < orders.length; i++) {
			map.put(orders[i], orders[(i + 1) % orders.length]);
		}
		int[] dp = new int[N];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		for (int i = 0; i < N; i++) {
			if (dp[i] == Integer.MAX_VALUE) continue;
			for (int j = i + 1; j < N; j++) {
				if (arr[j] == map.get(arr[i])) {
					int cost = (j - i) * (j - i);
					dp[j] = Math.min(dp[j], dp[i] + cost);
				}
			}
		}
		System.out.println(dp[N - 1] == Integer.MAX_VALUE ? -1 : dp[N - 1]);
	}
}