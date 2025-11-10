package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj14923 {
	static int[][] moves = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static int INF = 1 << 30;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());

		int sx = Integer.parseInt(st.nextToken()) - 1;
		int sy = Integer.parseInt(st.nextToken()) - 1;

		st = new StringTokenizer(br.readLine());
		int ex = Integer.parseInt(st.nextToken()) - 1;
		int ey = Integer.parseInt(st.nextToken()) - 1;

		int[][] arr = new int[N][M];
		int[][][] dist = new int[2][N][M];
		for (int i = 0; i < N; i++) {
			arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		for (int[][] d : dist)
			for (int[] row : d)
				Arrays.fill(row, INF);
		dist[0][sx][sy] = 0;

		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {sx, sy, 0});

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			for (int[] move : moves) {
				int nx = curr[0] + move[0];
				int ny = curr[1] + move[1];
				if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
				if (dist[curr[2]][nx][ny] > dist[curr[2]][curr[0]][curr[1]] + 1) {
					if (arr[nx][ny] == 1 && curr[2] == 0) {
						q.offer(new int[] {nx, ny, 1});
						dist[1][nx][ny] = dist[curr[2]][curr[0]][curr[1]] + 1;
					}
					if (arr[nx][ny] == 0) {
						q.offer(new int[] {nx, ny, curr[2]});
						dist[curr[2]][nx][ny] = dist[curr[2]][curr[0]][curr[1]] + 1;
					}
				}
			}
		}
		int result = Math.min(dist[0][ex][ey], dist[1][ex][ey]);
		System.out.println(result == INF ? -1 : result);
	}
}
