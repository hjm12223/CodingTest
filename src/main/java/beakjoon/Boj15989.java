package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj15989 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=  new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		int[][] dp = new int[10001][4];
		dp[1][1] = 1;
		dp[2][1] = 1;
		dp[3][1] = 1;
		dp[2][2] = 1;
		dp[3][2] = 1;
		dp[3][3] = 1;

		for (int i = 4; i < 10001; i++) {
			dp[i][1] = dp[i-1][1];
			dp[i][2] = dp[i-2][2] + dp[i-2][1];
			dp[i][3] = dp[i-3][3] + dp[i-3][2] + dp[i-3][1];
		}
		for (int i = 0; i < T; i++) {
			int value = Integer.parseInt(br.readLine());
			bw.write(String.valueOf(dp[value][1] + dp[value][2]  + dp[value][3]));
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}
}
