package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj3109 {
	static int N, M;
	static char[][] arr;
	static int[][] moves = new int[][] {{-1, 1}, {0, 1}, {1, 1}}; // 우, 우상, 우하
	static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new char[N][M];

		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		for (int i = 0; i < N; i++) {
			dfs(i, 0);
		}
		System.out.println(result);
	}

	private static boolean dfs(int x, int y) {
		if (y == M - 1) {
			result++;
			return true;
		}
		for (int[] move : moves) {
			int nx = x + move[0];
			int ny = y + move[1];
			if (nx < 0 || nx >= N || ny >= M) continue;
			if (arr[nx][ny] == '.') {
				arr[nx][ny] = 'x';
				if (dfs(nx, ny)) return true;
			}
		}
		return false;
	}
}