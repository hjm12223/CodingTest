package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2589 {
	static int N, M;
	static char[][] arr;
	static int[][] moves = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new char[N][M];

		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		int result = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 'L') {
					result = Math.max(bfs(i, j), result);
				}
			}
		}
		System.out.println(result);
	}

	private static int bfs(int x, int y) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {x, y, 0});
		int[][] dist = new int[N][M];
		boolean[][] visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		visited[x][y] = true;
		int maxCost = 0;
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			maxCost = Math.max(curr[2], maxCost);
			for (int[] move : moves) {
				int nx = move[0] + curr[0];
				int ny = move[1] + curr[1];
				int nxCost = curr[2] + 1;
				if (nx < 0 || ny < 0 || nx >= N || ny >= M || arr[nx][ny] == 'W') continue;
				if (!visited[nx][ny] && dist[nx][ny] > nxCost) {
					visited[nx][ny] = true;
					dist[nx][ny] = nxCost;
					q.offer(new int[] {nx, ny, nxCost});
				}
			}
		}
		return maxCost;
	}
}
