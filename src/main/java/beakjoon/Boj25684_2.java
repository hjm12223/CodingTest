package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj25684_2 {
	static int N, M, H;
	static int[][] ladder;
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		if (M == 0) {
			System.out.println(0);
			return;
		}
		ladder = new int[H][N - 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken()) - 1;
			ladder[n][m] = 1;
		}
		dfs(0, 0);
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}

	private static void dfs(int depth, int cnt) {
		if (cnt > 3) return;
		if (depth == H) {
			if (check()) {
				result = Math.min(result, cnt);
			}
			return;
		}
		for (int i = 0; i < N - 1; i++) {
			if (ladder[depth][i] == 1) continue;
			if (i > 0 && ladder[depth][i - 1] == 1) continue;
			if (i < N - 2 && ladder[depth][i + 1] == 1) continue;
			ladder[depth][i] = 1;
			dfs(depth, cnt + 1);
			ladder[depth][i] = 0;
		}
		dfs(depth + 1, cnt);
	}

	private static boolean check() {
		int[] value = new int[N];
		for (int i = 0; i < N; i++) {
			value[i] = i + 1;
		}
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N - 1; j++) {
				if (ladder[i][j] == 1) {
					int temp = value[j];
					value[j] = value[j + 1];
					value[j + 1] = temp;
				}
			}
		}
		for (int i = 1; i <= N; i++) {
			if (i != value[i - 1]) return false;
		}
		return true;
	}
}
