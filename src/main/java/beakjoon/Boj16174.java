package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj16174 {
	static int[][] moves = new int[][] {{0, 1}, {1, 0}};
	static int[][] arr;
	static int N;
	static boolean[][] visited;

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
		visited[0][0] = true;
		dfs(0, 0);
		System.out.println("Hing");

	}

	private static void dfs(int x, int y) {
		if (x == N - 1 && y == N - 1) {
			System.out.println("HaruHaru");
			System.exit(0);
			return;
		}
		for (int[] move : moves) {
			int nx = move[0] * arr[x][y] + x;
			int ny = move[1] * arr[x][y] + y;
			if (nx >= N || ny >= N) continue;
			if (!visited[nx][ny]) {
				visited[nx][ny] = true;
				dfs(nx, ny);
				visited[nx][ny] = false;
			}
		}
	}
}
