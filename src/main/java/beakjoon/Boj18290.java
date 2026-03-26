package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj18290 {
	static int N, M, K;
	static int[][] arr;
	static int result = Integer.MIN_VALUE;
	static int[][] moves = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		arr = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		dfs(0, 0, 0);
		System.out.println(result);
	}

	private static void dfs(int idx, int depth, int value) {
		if (depth == K) {
			result = Math.max(result, value);
			return;
		}
		int x = idx / M;
		int y = idx % M;
		if (idx >= N * M) return;
		if (canSelect(x, y)) {
			visited[x][y] = true;
			dfs(idx + 1, depth + 1, value + arr[x][y]);
			visited[x][y] = false;
		}
		dfs(idx + 1, depth, value);
	}

	private static boolean canSelect(int x, int y) {
		for (int[] move : moves) {
			int nx = move[0] + x;
			int ny = move[1] + y;
			if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
			if (visited[nx][ny]) {
				return false;
			}
		}
		return true;
	}
}
