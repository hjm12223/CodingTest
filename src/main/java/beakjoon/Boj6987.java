package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj6987 {
	static int[][] game = new int[15][2];
	static int[][] board = new int[6][3];
	static boolean isPossible = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int idx = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = i + 1; j < 6; j++) {
				game[idx][0] = i;
				game[idx][1] = j;
				idx++;
			}
		}
		for (int t = 0; t < 4; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			isPossible = false;
			for (int i = 0; i < 6; i++) {
				board[i][0] = Integer.parseInt(st.nextToken());
				board[i][1] = Integer.parseInt(st.nextToken());
				board[i][2] = Integer.parseInt(st.nextToken());
			}
			dfs(0);
			sb.append(isPossible ? 1 : 0).append(" ");
		}
		System.out.println(sb);
	}

	private static void dfs(int depth) {
		if (isPossible) return;
		if (depth == 15) {
			if (check()) isPossible = true;
			return;
		}
		int a = game[depth][0];
		int b = game[depth][1];

		if (board[a][0] > 0 && board[b][2] > 0) {
			board[a][0]--;
			board[b][2]--;
			dfs(depth + 1);
			board[a][0]++;
			board[b][2]++;
		}

		if (board[a][1] > 0 && board[b][1] > 0) {
			board[a][1]--;
			board[b][1]--;
			dfs(depth + 1);
			board[a][1]++;
			board[b][1]++;
		}

		if (board[a][2] > 0 && board[b][0] > 0) {
			board[a][2]--;
			board[b][0]--;
			dfs(depth + 1);
			board[a][2]++;
			board[b][0]++;
		}
	}

	private static boolean check() {
		for (int i = 0; i < 6; i++) {
			if (board[i][0] != 0) return false;
			if (board[i][1] != 0) return false;
			if (board[i][2] != 0) return false;
		}
		return true;
	}
}
