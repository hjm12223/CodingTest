package beakjoon.DfsBfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14500 {
	// 상하좌우 이동을 위한 배열
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int N, M;
	static int[][] board;

	static boolean[][] visited;
	static int maxValue;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board = new int[N][M];
		visited = new boolean[N][M];

		// 보드 입력 받기
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = true;
				dfs(i, j, board[i][j], 1);
				visited[i][j] = false;
			}
		}
		System.out.println(maxValue);
	}

	private static void dfs(int x, int y, int sum, int depth) {
		if (depth == 4) {
			maxValue = Math.max(sum, maxValue);
		}
		for (int i = 0; i < 4; i++) {
			int nextX = dx[i] + x;
			int nextY = dy[i] + y;
			if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M || visited[nextX][nextY]) continue;
			if (depth == 2) {
				visited[nextX][nextY] = true;
				dfs(x, y, sum + board[nextX][nextY], depth + 1);
				visited[nextX][nextY] = false;
			}
			visited[nextX][nextY] = true;
			dfs(nextX, nextY, sum + board[nextX][nextY], depth + 1);
			visited[nextX][nextY] = false;
		}
	}
}

