package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj17281 {
	static boolean[] visited = new boolean[9];
	static int[] player = new int[9];
	static int[][] games;
	static int answer = Integer.MIN_VALUE;

	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		games = new int[N][9];

		for (int i = 0; i < N; i++) {
			games[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		player[3] = 0;
		dfs(0);
		System.out.println(answer);
	}

	private static void dfs(int depth) {
		if (depth == 9) {
			simulate();
			return;
		}
		if (depth == 3) {
			dfs(depth + 1);
			return;
		}
		for (int i = 1; i < 9; i++) {
			if (!visited[i]) {
				visited[i] = true;
				player[depth] = i;
				dfs(depth + 1);
				visited[i] = false;
			}
		}
	}

	private static void simulate() {
		int score = 0;
		int idx = 0;
		for (int i = 0; i < N; i++) {
			int out = 0;
			boolean[] base = new boolean[3];
			while (out < 3) {
				int result = games[i][player[idx]];
				switch (result) {
					case 0:
						out++;
						break;
					case 1:
						if (base[2]) {
							score++;
							base[2] = false;
						}
						if (base[1]) {
							base[2] = true;
							base[1] = false;
						}
						if (base[0]) {
							base[1] = true;
							base[0] = false;
						}
						base[0] = true;
						break;
					case 2:
						if (base[2]) {
							score++;
							base[2] = false;
						}
						if (base[1]) {
							score++;
							base[1] = false;
						}
						if (base[0]) {
							base[2] = true;
							base[0] = false;
						}
						base[1] = true;
						break;
					case 3:
						for (int k = 0; k < base.length; k++) {
							if (base[k]) {
								score++;
								base[k] = false;
							}
						}
						base[2] = true;
						break;
					case 4:
						for (int k = 0; k < base.length; k++) {
							if (base[k]) {
								score++;
								base[k] = false;
							}
						}
						score++;
						break;
				}
				idx = (idx + 1) % 9;
			}
		}
		answer = Math.max(score, answer);
	}
}
