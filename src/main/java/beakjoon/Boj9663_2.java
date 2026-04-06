package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj9663_2 {
	static boolean[][] visited;
	static int N;
	static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		visited = new boolean[N][N];
		backtracking(0);
		System.out.println(result);
	}

	private static void backtracking(int row) {
		if (row == N) {
			result++;
			return;
		}
		for (int i = 0; i < N; i++) {
			if (check(row, i)) {
				visited[row][i] = true;
				backtracking(row + 1);
				visited[row][i] = false;
			}
		}
	}

	private static boolean check(int row, int col) {
		for (int i = 0; i <= row; i++) {
			if (visited[i][col]) return false;
		}
		for (int i = 1; row - i >= 0 && col - i >= 0; i++) {
			if (visited[row - i][col - i]) return false;
		}
		for (int i = 1; row - i >= 0 && col + i < N; i++) {
			if (visited[row - i][col + i]) return false;
		}
		return true;
	}
}
