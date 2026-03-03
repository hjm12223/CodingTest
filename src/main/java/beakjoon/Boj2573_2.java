package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2573_2 {
	static int[][] arr;
	static int N, M;
	static boolean[][] visited;
	static int[][] moves = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int years = 0;
		while (true) {
			visited = new boolean[N][M];
			// 확인하기
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (!visited[i][j] && arr[i][j] != 0) {
						visited[i][j] = true;
						check(i, j);
						cnt++;
					}
				}
			}
			if (cnt > 1) {
				System.out.println(years);
				return;
			}

			if (cnt == 0) {
				System.out.println(0);
				return;
			}
			// 빙하를 녹이기
			int[][] melt = new int[N][M];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (arr[i][j] != 0) {
						int seaCnt = 0;

						for (int[] move : moves) {
							int nx = i + move[0];
							int ny = j + move[1];

							if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
							if (arr[nx][ny] == 0) seaCnt++;
						}

						melt[i][j] = seaCnt;
					}
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					arr[i][j] = Math.max(0, arr[i][j] - melt[i][j]);
				}
			}
			years++;
		}
	}

	private static void check(int sx, int sy) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {sx, sy});

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			for (int[] move : moves) {
				int nx = move[0] + curr[0];
				int ny = move[1] + curr[1];
				if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
				if (!visited[nx][ny] && arr[nx][ny] != 0) {
					visited[nx][ny] = true;
					q.offer(new int[] {nx, ny});
				}
			}
		}
	}
	/*
	1. 빙산을 녹인다
	2. 빙산이 분리되었는지 확인한다
		- 빙산이 한개라면 BFS를 한번 더 돌려본다

	빙산을 분리 되었는지 확인하는 로직이 필요.

	 */
}
