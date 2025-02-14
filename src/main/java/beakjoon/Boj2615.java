package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj2615 {
	static final int N = 19;
	static boolean[][] visited;
	static int[][] concave;
	static int win = 0;
	static int resultX, resultY;
	static int[][] move = {{0, 1}, {1, 0}, {1, 1}, {-1, 1}}; // 우, 하, 우하, 우상

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		concave = new int[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			concave[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (concave[i][j] != 0) {
					if (dfs(i, j, concave[i][j])) {
						System.out.println(win);
						System.out.println(resultX + " " + resultY);
						return;
					}
				}
			}
		}
		System.out.println(0);
	}

	private static boolean dfs(int x, int y, int color) {
		for (int[] dir : move) {
			int nx = x - dir[0];
			int ny = y - dir[1];

			if (isValid(nx, ny) && concave[nx][ny] == color) continue;

			if (back(x, y, color, dir, 1) == 5) {
				win = color;
				resultX = x + 1;
				resultY = y + 1;
				return true;
			}
		}
		return false;
	}

	private static int back(int x, int y, int color, int[] dir, int cnt) {
		int nx = x + dir[0];
		int ny = y + dir[1];
		if (!isValid(nx, ny) || concave[nx][ny] != color) return cnt;
		return back(nx, ny, color, dir, cnt + 1);
	}

	private static boolean isValid(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < N;
	}
}
