package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj1149_2 {
	static int N;
	static int[][] dp, arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1][3];
		dp = new int[N + 1][3];
		for (int i = 1; i <= N; i++) {
			arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		dp[1][0] = arr[1][0];
		dp[1][1] = arr[1][1];
		dp[1][2] = arr[1][2];
		for (int i = 2; i <= N; i++) {
			dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + arr[i][0];
			dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + arr[i][1];
			dp[i][2] = Math.min(dp[i - 1][1], dp[i - 1][0]) + arr[i][2];
		}
		System.out.println(Arrays.deepToString(dp));
		System.out.println(Math.min(Math.min(dp[arr.length - 1][0], dp[arr.length - 1][1]), dp[arr.length - 1][2]));
	}
}

/*
Math.min(dfs(x+1,y+1)
dfs(x+1,y-1)
dfs(x+1,y)
 */