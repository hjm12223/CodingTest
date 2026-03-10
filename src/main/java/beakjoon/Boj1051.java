package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1051 {
	static int N, M;
	static int[][] arr;
	static int result = 1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = line.charAt(j) - '0';
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				dfs(i, j, 1);
			}
		}
		System.out.println(result);
	}

	static void dfs(int x, int y, int depth) {
		int nx = x + depth;
		int ny = y + depth;
		if (nx >= N || ny >= M) return;
		if (arr[x][y] == arr[nx][y] && arr[nx][y] == arr[nx][ny] && arr[nx][ny] == arr[x][ny]
			&& arr[x][ny] == arr[x][y]) {
			int extend = depth + 1;
			result = Math.max(result, extend * extend);
		}
		dfs(x, y, depth + 1);
	}
}
