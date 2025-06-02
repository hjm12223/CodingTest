package beakjoon.DfsBfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2468 {
	static int N;
	static int[][] arr;
	static int[][] moves = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		int maxHeight = 0;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int max = Integer.parseInt(st.nextToken());
				arr[i][j] = max;
				maxHeight = Math.max(maxHeight, max);
			}
		}
		int result = 0;
		for (int h = 0; h <= maxHeight; h++) {
			int cnt = 0;
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (arr[i][j] > h && !visited[i][j]) {
						visited[i][j] = true;
						bfs(i, j, h);
						cnt++;
					}
				}
			}
			result = Math.max(cnt, result);
		}
		System.out.println(result);
	}

	private static void bfs(int x, int y, int h) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {x, y});
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			for (int[] move : moves) {
				int nx = move[0] + curr[0];
				int ny = move[1] + curr[1];
				if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
				if (arr[nx][ny] > h && !visited[nx][ny]) {
					visited[nx][ny] = true;
					q.offer(new int[] {nx, ny});
				}
			}
		}
	}

	private static void prt(Object o) {
		System.out.println(o);
	}
}