package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj17484 {
	static int[][] arr;
	static int N, M;
	static int result = Integer.MAX_VALUE;
	static int[][] moves = new int[][] {{1, 0}, {1, 1}, {1, -1}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		for (int i = 0; i < M; i++) {
			dfs(0, i, arr[0][i], -1, 1);
		}
		System.out.println(result);
	}

	private static void dfs(int x, int y, int value, int prev, int depth) {
		if (depth == N) {
			result = Math.min(value, result);
			return;
		}
		for (int i = 0; i < moves.length; i++) {
			if (i == prev) continue;
			int nx = x + moves[i][0];
			int ny = y + moves[i][1];
			if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
			dfs(nx, ny, value + arr[nx][ny], i, depth + 1);
		}
	}
}
