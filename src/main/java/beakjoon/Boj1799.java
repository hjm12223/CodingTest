package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj1799 {
	static int N;
	static int[][] board;
	static int result = 0;
	static boolean[] visited1, visited2;
	static int maxBlack, maxWhite = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visited1 = new boolean[2 * N - 1];
		visited2 = new boolean[2 * N - 1];

		dfs(0, 0, 0, true);
		dfs(0, 1, 0, false);
		System.out.println(maxBlack + maxWhite);
	}

	private static void dfs(int x, int y, int cnt, boolean isBlack) {
		if (x == N) {
			if (isBlack) {
				maxBlack = Math.max(cnt, maxBlack);
			} else {
				maxWhite = Math.max(cnt, maxWhite);
			}
			return;
		}
		int nextX = (y + 2 >= N) ? x + 1 : x;
		int nextY = (y + 2 >= N) ? (y % 2 == 0 ? 0 : 1) : y + 2;
		if (board[x][y] == 1 && !visited1[x + y] && !visited2[x - y + (N - 1)]) {
			visited1[x + y] = true;
			visited2[x - y + (N - 1)] = true;
			dfs(nextX, nextY, cnt + 1, isBlack);
			visited1[x + y] = false;
			visited2[x - y + (N - 1)] = false;
		}
		dfs(nextX, nextY, cnt, isBlack);

	}

}
