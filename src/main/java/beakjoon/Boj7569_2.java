package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj7569_2 {
	static int N, M, H;
	static int[][][] arr;
	static boolean[][][] visited;
	static int[][] moves = {{1, 0, 0}, {-1, 0, 0}, {0, 1, 0}, {0, -1, 0}, {0, 0, 1}, {0, 0, -1}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		arr = new int[H][N][M];
		visited = new boolean[H][N][M];
		Queue<int[]> q = new ArrayDeque<>();

		for (int h = 0; h < H; h++) {
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					int value = Integer.parseInt(st.nextToken());
					arr[h][i][j] = value;
					if (value == 1) {
						q.offer(new int[] {h, i, j, 0});
						visited[h][i][j] = true;
					}
				}
			}
		}

		int days = bfs(q);
		for (int h = 0; h < H; h++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (arr[h][i][j] == 0) {
						System.out.println(-1);
						return;
					}
				}
			}
		}
		System.out.println(days);
	}

	private static int bfs(Queue<int[]> q) {
		int maxDay = 0;
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int h = curr[0], x = curr[1], y = curr[2], day = curr[3];
			maxDay = Math.max(maxDay, day);

			for (int[] move : moves) {
				int nh = h + move[0];
				int nx = x + move[1];
				int ny = y + move[2];
				if (nh < 0 || nx < 0 || ny < 0 || nh >= H || nx >= N || ny >= M) continue;
				if (!visited[nh][nx][ny] && arr[nh][nx][ny] == 0) {
					visited[nh][nx][ny] = true;
					arr[nh][nx][ny] = 1;
					q.offer(new int[] {nh, nx, ny, day + 1});
				}
			}
		}
		return maxDay;
	}
}
