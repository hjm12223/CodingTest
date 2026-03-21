package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj14500_2 {
	static int[][] arr;
	static int[][] moves = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static int N, M;
	static int result = 0;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j]) {
					visited[i][j] = true;
					dfs(i, j, 0, 0);
					visited[i][j] = false;
				}
			}
		}
		System.out.println(result);
	}

	private static void dfs(int x, int y, int depth, int value) {
		if (depth == 4) {
			result = Math.max(value, result);
			return;
		}
		for (int[] move : moves) {
			int nx = move[0] + x;
			int ny = move[1] + y;
			if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny]) continue;
			if (depth == 2) {
				visited[nx][ny] = true;
				dfs(x, y, depth + 1, value + arr[nx][ny]);
				visited[nx][ny] = false;
			}
			visited[nx][ny] = true;
			dfs(nx, ny, depth + 1, value + arr[nx][ny]);
			visited[nx][ny] = false;

		}
	}
}
