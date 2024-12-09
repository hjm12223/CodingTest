package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2206 {
	static int[] dx = new int[] {1, 0, 0, -1};
	static int[] dy = new int[] {0, 1, -1, 0};
	static int N, M;
	static int[][] arr;
	static int INF = 987654321;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			String[] str = br.readLine().split("");
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(str[j]);
			}
		}
		bfs();
	}

	private static void bfs() {
		boolean[][][] isVisited = new boolean[2][N][M];
		int[][][] dist = new int[2][N][M];
		Queue<int[]> q = new LinkedList<>();

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < N; j++) {
				Arrays.fill(dist[i][j], INF);
			}
		}

		q.offer(new int[] {0, 0, 0});
		isVisited[0][0][0] = true;
		dist[0][0][0] = 1;

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int x = curr[0], y = curr[1], wall = curr[2];

			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];

				if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

				if (arr[nx][ny] == 1 && wall == 0 && !isVisited[1][nx][ny]) {
					isVisited[1][nx][ny] = true;
					dist[1][nx][ny] = dist[0][x][y] + 1;
					q.offer(new int[] {nx, ny, 1});
				} else if (arr[nx][ny] == 0 && !isVisited[wall][nx][ny]) {
					isVisited[wall][nx][ny] = true;
					dist[wall][nx][ny] = dist[wall][x][y] + 1;
					q.offer(new int[] {nx, ny, wall});
				}
			}
		}

		int result = Math.min(dist[0][N - 1][M - 1], dist[1][N - 1][M - 1]);
		System.out.println(result == INF ? -1 : result);
	}
}
