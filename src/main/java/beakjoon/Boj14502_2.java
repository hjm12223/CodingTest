package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj14502_2 {
	static int N, M;
	static int[][] arr;
	static int result = Integer.MIN_VALUE;
	static List<int[]> virus = new ArrayList<>();
	static int[][] moves = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

	static int wallCnt = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int value = Integer.parseInt(st.nextToken());
				arr[i][j] = value;
				if (value == 2) virus.add(new int[] {i, j});
				if (value == 1) wallCnt++;
			}
		}

		dfs(3, 0);
		System.out.println(result);
	}

	private static void dfs(int r, int depth) {
		if (depth == r) {
			result = Math.max(bfs(), result);
			return;
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 0) {
					arr[i][j] = 1;
					dfs(r, depth + 1);
					arr[i][j] = 0;
				}
			}
		}
	}

	private static int bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][M];
		for (int[] vi : virus) {
			visited[vi[0]][vi[1]] = true;
			q.offer(vi);
		}
		int cnt = 0;
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			cnt++;
			for (int[] move : moves) {
				int nx = move[0] + curr[0];
				int ny = move[1] + curr[1];
				if (nx < 0 || ny < 0 || nx >= N || ny >= M || arr[nx][ny] != 0) continue;
				if (!visited[nx][ny]) {
					visited[nx][ny] = true;
					q.offer(new int[] {nx, ny});
				}
			}
		}
		return N * M - cnt - wallCnt - 3;
	}
}
