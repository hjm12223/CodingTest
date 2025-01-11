package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj1520 {
	static int[] dx = new int[] {1, 0, 0, -1};
	static int[] dy = new int[] {0, -1, 1, 0};
	static int[][] dp;
	static int N, M;
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		dp = new int[N][M];
		for (int i = 0; i < N; i++) {
			arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			Arrays.fill(dp[i], -1);
		}
		int result = start(0, 0);
		System.out.println(result);
	}

	private static int start(int x, int y) {
		if (x == N - 1 && y == M - 1) {
			return 1;
		}
		if (dp[x][y] != -1) {
			return dp[x][y];
		}
		dp[x][y] = 0;
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
			if (arr[nx][ny] < arr[x][y]) {
				dp[x][y] += start(nx, ny);
			}
		}
		return dp[x][y];
	}

}
