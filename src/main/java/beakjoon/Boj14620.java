package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14620 {
	static int[][] moves = new int[][] {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
	static int N;
	static int[][] arr;
	static int result = Integer.MAX_VALUE;
	static boolean[][] visited = new boolean[N][N];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0, 0);
		System.out.println(result);
	}

	private static void dfs(int cnt, int totalValue) {
		if (cnt == 3) {
			result = Math.min(result, totalValue);
			return;
		}
		for (int i = 1; i <= N - 1; i++) {
			for (int j = 1; j <= N - 1; j++) {
				if (check(i, j)) {
					int cost = plant(i, j, true);
					dfs(cnt + 1, totalValue + cost);
					plant(i, j, false);
				}
			}
		}

	}

	private static int plant(int x, int y, boolean plant) {
		int cost = arr[x][y];
		visited[x][y] = plant;
		for (int[] move : moves) {
			int nx = x + move[0];
			int ny = y + move[1];
			visited[nx][ny] = plant;
			if (plant) {
				cost += arr[nx][ny];
			}
		}
		return cost;
	}

	private static boolean check(int x, int y) {
		if (visited[x][y]) return false;
		for (int[] move : moves) {
			int nx = x + move[0];
			int ny = y + move[1];
			if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny]) {
				return false;
			}
		}
		return true;
	}
}

