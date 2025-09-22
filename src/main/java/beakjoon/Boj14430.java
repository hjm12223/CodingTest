package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj14430 {
	static int[][] moves = new int[][] {{0, 1}, {1, 0}};

	static int N, M;
	static int[][] arr;
	static int[][] dist;
	static int INF = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dist = new int[N][M];
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < N; i++)
			Arrays.fill(dist[i], INF);
		int result = dfs(0, 0);
		System.out.println(result);
	}

	private static int dfs(int x, int y) {
		if (x >= N || y >= M) return 0;
		if (dist[x][y] != INF)
			return dist[x][y];
		int curr = arr[x][y];
		int right = dfs(x, y + 1);
		int down = dfs(x + 1, y);
		dist[x][y] = curr + Math.max(right, down);
		return dist[x][y];
	}
}
