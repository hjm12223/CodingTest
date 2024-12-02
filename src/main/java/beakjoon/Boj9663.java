package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj9663 {
	static int[][] chess;
	static int N;
	static int cnt = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		chess = new int[N][N];
		dfs(0);
		System.out.println(cnt);
	}

	private static void dfs(int row) {
		if (row == N) {
			cnt++;
			return;
		}
		for (int i = 0; i < N; i++) {
			if (possible(row, i)) {
				chess[row][i] = 1;
				dfs(row + 1);
				chess[row][i] = 0;
			}
		}
	}

	private static boolean possible(int row, int col) {
		for (int i = 0; i < N; i++) {
			if (chess[i][col] == 1) return false;
		}

		for (int i = 1; row - i >= 0 && col - i >= 0; i++) { // 좌측 상향 대각선
			if (chess[row - i][col - i] == 1) return false;
		}
		for (int i = 1; row - i >= 0 && col + i < N; i++) { // 우측 상향 대각선
			if (chess[row - i][col + i] == 1) return false;
		}
		return true;
	}
}
