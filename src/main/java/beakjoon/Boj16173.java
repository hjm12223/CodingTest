package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj16173 {
	static int[][] arr;
	static int N;
	static int[][] moves = new int[][] {{0, 1}, {1, 0}};
	static boolean isComplete = false;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		dfs(0, 0);
		System.out.println(isComplete ? "HaruHaru" : "Hing");
	}

	private static void dfs(int x, int y) {
		if (x == N - 1 && y == N - 1) {
			isComplete = true;
			return;
		}
		for (int[] move : moves) {
			int nx = x + move[0] * arr[x][y];
			int ny = y + move[1] * arr[x][y];
			if (nx >= N || ny >= N) continue;
			if (!visited[nx][ny]) {
				visited[nx][ny] = true;
				dfs(nx, ny);
				visited[nx][ny] = false;
			}
		}
	}
}
