package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj10472 {
	static int N = 3;
	static int[][] moves = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static char[][] arr;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			result = Integer.MAX_VALUE;
			arr = new char[N][N];
			for (int i = 0; i < N; i++) {
				arr[i] = br.readLine().toCharArray();
			}
			dfs(0, 0);

			System.out.println(result);
		}

	}

	private static void dfs(int idx, int cnt) {
		if (idx == 9) {
			if (check()) {
				result = Math.min(result, cnt);
			}
			return;
		}
		int row = idx % N;
		int col = idx / N;
		dfs(idx + 1, cnt);
		char value = arr[col][row] == '.' ? '*' : '.';
		arr[col][row] = value;
		run(col, row);
		dfs(idx + 1, cnt + 1);
		value = arr[col][row] == '.' ? '*' : '.';
		arr[col][row] = value;
		run(col, row);
	}

	private static void run(int col, int row) {
		for (int[] move : moves) {
			int nx = move[0] + col;
			int ny = move[1] + row;
			if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
			char c = arr[nx][ny] == '.' ? '*' : '.';
			arr[nx][ny] = c;
		}
	}

	private static boolean check() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == '*') return false;
			}
		}
		return true;
	}
}
