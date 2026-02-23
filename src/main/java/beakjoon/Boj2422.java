package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2422 {
	static boolean[][] isBad;
	static int[] selected;
	static int N, result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		result = 0;
		int M = Integer.parseInt(st.nextToken());
		isBad = new boolean[N + 1][N + 1];
		selected = new int[3];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			isBad[a][b] = true;
			isBad[b][a] = true;
		}
		dfs(1, 0);
		System.out.println(result);
	}

	private static void dfs(int start, int depth) {
		if (depth == 3) {
			int a = selected[0];
			int b = selected[1];
			int c = selected[2];
			if (!isBad[a][b] && !isBad[a][c] && !isBad[b][c]) result++;
			return;
		}
		for (int i = start; i <= N; i++) {
			selected[depth] = i;
			dfs(i + 1, depth + 1);
		}
	}
}
