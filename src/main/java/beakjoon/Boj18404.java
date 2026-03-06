package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj18404 {
	static int[][] moves = new int[][] {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};
	static int[][] dist;
	static boolean[][] visited;

	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		dist = new int[N + 1][N + 1];
		visited = new boolean[N + 1][N + 1];

		int M = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N + 1; i++) {
			Arrays.fill(dist[i], (1 << 10));
		}
		st = new StringTokenizer(br.readLine());
		int startX = Integer.parseInt(st.nextToken());
		int startY = Integer.parseInt(st.nextToken());
		bfs(startX, startY);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			sb.append(dist[x][y]).append(" ");
		}
		System.out.println(sb);
	}

	private static int bfs(int startX, int startY) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {startX, startY, 0});
		visited[startX][startY] = true;
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int cx = curr[0];
			int cy = curr[1];
			dist[cx][cy] = Math.min(dist[cx][cy], curr[2]);
			for (int[] move : moves) {
				int nx = cx + move[0];
				int ny = cy + move[1];
				if (nx <= 0 || ny <= 0 || nx > N || ny > N) continue;
				if (!visited[nx][ny]) {
					q.offer(new int[] {nx, ny, curr[2] + 1});
					visited[nx][ny] = true;
				}
			}
		}
		return -1;
	}
}
