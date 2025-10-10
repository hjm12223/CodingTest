package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2073_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int D = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		int[] lengths = new int[P + 1];
		int[] capacities = new int[P + 1];
		int[] dp = new int[D + 1];
		for (int i = 1; i <= P; i++) {
			st = new StringTokenizer(br.readLine());
			lengths[i] = Integer.parseInt(st.nextToken());
			capacities[i] = Integer.parseInt(st.nextToken());
		}
		dp[0] = Integer.MAX_VALUE;
		for (int i = 1; i <= P; i++) {
			for (int j = D; j >= 0; j--) {
				int length = lengths[i];
				if (j - length >= 0) {
					dp[j] = Math.max(dp[j], Math.min(dp[j - length], capacities[i]));
				}
			}
		}
		System.out.println("dp = " + Arrays.toString(dp));
	}
}
