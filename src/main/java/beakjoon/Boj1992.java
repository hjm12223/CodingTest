package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj1992 {
	static int N;
	static char[][] board;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		board = new char[N][N];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				board[i][j] = line.charAt(j);
			}
		}

		quad(0, 0, N);

		System.out.println(sb);
	}

	private static void quad(int x, int y, int size) {
		if (check(x, y, size)) {
			sb.append(board[x][y]);
			return;
		}
		sb.append("(");
		int half = size / 2;
		quad(x, y, half);
		quad(x, y + half, half);
		quad(x + half, y, half);
		quad(x + half, y + half, half);
		sb.append(")");
	}

	static boolean check(int x, int y, int size) {

		char first = board[x][y];

		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				if (board[i][j] != first) {
					return false;
				}
			}
		}

		return true;
	}
}
