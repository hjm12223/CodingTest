package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj15684 {
	static int N, M, H;
	static int result = 4;
	static int[][] ladder;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken()); // 놓을 수 있는 사다리의 수
		H = Integer.parseInt(st.nextToken()); // 높이
		ladder = new int[H + 1][N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			ladder[row][col] = 1;
		}
		for (int i = 0; i <= 3; i++) {
			dfs(i, 0, 1, 1);
			if (result <= i) {
				System.out.println(result);
				return;
			}
		}
		System.out.println(-1);
	}

	private static void dfs(int maxCnt, int cnt, int x, int y) {
		if (cnt == maxCnt) {
			if (check()) {
				result = Math.min(result, cnt);
			}
			return;
		}
		for (int i = x; i <= H; i++) {
			for (int j = (i == x ? y : 1); j < N; j++) {
				if (ladder[i][j - 1] == 0 && ladder[i][j] == 0 && ladder[i][j + 1] == 0) {
					ladder[i][j] = 1;
					dfs(maxCnt, cnt + 1, i, j + 2);
					ladder[i][j] = 0;
				}
			}
		}
	}

	private static boolean check() {
		for (int i = 1; i <= N; i++) {
			int col = i;
			int row = 1;
			while (row <= H) {
				if (ladder[row][col] == 1) {
					col++;
				} else if (col > 1 && ladder[row][col - 1] == 1) {
					col--;
				}
				row++;
			}
			if (col != i) return false;
		}
		return true;
	}
}
/*
[1, 0, 0, 0],
[0, 0, 1, 0],
[0, 1, 0, 0],
[0, 0, 0, 0],
[1, 0, 0, 1]
[0, 0, 0, 0]
자신의 인덱스 -1 == 왼쪽
자신의 인덱스에 존재할경우 == 오른쪽
dfs?
 */