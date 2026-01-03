package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj2615_2 {
	static final int N = 19;
	static int[][] moves = new int[][] {{0, 1}, {1, 0}, {1, 1}, {-1, 1}};
	static int[][] omok;
	static int rx, ry, result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		omok = new int[N][N];

		for (int i = 0; i < N; i++) {
			omok[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (omok[i][j] != 0) {
					if (dfs(i, j, omok[i][j])) {
						System.out.println(result);
						System.out.println(rx + " " + ry);
						return;
					}
				}
			}
		}
		System.out.println(0);
	}

	private static boolean dfs(int x, int y, int color) {
		for (int[] move : moves) {
			int nx = x - move[0];
			int ny = y - move[1];
			if (isValid(nx, ny) && omok[nx][ny] == color) continue;
			if (backTracking(x, y, color, move, 1) == 5) {
				result = color;
				rx = x + 1;
				ry = y + 1;
				return true;
			}
		}
		return false;
	}

	private static int backTracking(int x, int y, int color, int[] move, int cnt) {
		int nx = x + move[0];
		int ny = y + move[1];
		if (!isValid(nx, ny) || omok[nx][ny] != color) return cnt;
		return backTracking(nx, ny, color, move, cnt + 1);
	}

	private static boolean isValid(int nx, int ny) {
		return nx >= 0 && ny >= 0 && nx < N && ny < N;
	}

}
