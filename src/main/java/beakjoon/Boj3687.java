package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Boj3687 {
	static String INF = "100000000000000000000000";

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		String[] dp = new String[101];
		Arrays.fill(dp, INF);
		dp[2] = "1";
		dp[3] = "7";
		dp[4] = "4";
		dp[5] = "2";
		dp[6] = "6";
		dp[7] = "8";
		int[] makeStick = new int[] {6, 2, 5, 5, 4, 5, 6, 3, 7, 6};
		makeDP(dp, makeStick);
		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			StringBuilder sb = new StringBuilder();
			if (N % 2 == 0) {
				int mok = N / 2;
				sb.append("1".repeat(Math.max(0, mok)));
			} else {
				sb.append("7");
				int mok = (N - 3) / 2;
				sb.append("1".repeat(Math.max(0, mok)));
			}
			bw.write(dp[N] + " " + sb + "\n");
		}
		bw.flush();
		bw.close();
	}

	private static void makeDP(String[] dp, int[] makeStick) {
		for (int i = 2; i <= 100; i++) {
			for (int j = 0; j < 10; j++) {
				int cost = makeStick[j];
				if (i - cost < 2 && i != cost) continue;
				if (i - cost >= 2 && dp[i - cost].equals(INF)) continue;
				String candidate = dp[i - cost] + j;
				if (candidate.charAt(0) == '0') continue;
				if (compare(candidate, dp[i])) {
					dp[i] = candidate;
				}
			}
		}
	}

	private static boolean compare(String a, String b) {
		if (a.length() != b.length()) return a.length() < b.length();
		return a.compareTo(b) < 0;
	}
}
