package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1189 {
	static char[][] arr;
	static int result = 0;
	static int N, M;
	static int[] dx = new int[] {0, 1, -1, 0};

	static int[] dy = new int[] {1, 0, 0, -1};
	static boolean[][] isVisited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		isVisited = new boolean[N][M];
		arr = new char[N][M];
		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		dfs(1, K, N - 1, 0);
		System.out.println(result);
	}

	private static void dfs(int depth, int r, int x, int y) {
		if (x == 0 && y == M - 1) {
			if (depth == r) {
				result++;
			}
			return;
		}
		isVisited[x][y] = true;
		for (int d = 0; d < 4; d++) {
			int nx = dx[d] + x;
			int ny = dy[d] + y;
			if (nx < 0 || ny < 0 || nx >= N || ny >= M || arr[nx][ny] == 'T') continue;
			if (!isVisited[nx][ny]) {
				isVisited[nx][ny] = true;
				dfs(depth + 1, r, nx, ny);
				isVisited[nx][ny] = false;
			}
		}
	}
}
