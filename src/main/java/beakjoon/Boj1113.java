package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1113 {
	static int[][] arr;
	static boolean[][] visited;
	static int[][] moves = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		arr = new int[N + 2][M + 2];
		visited = new boolean[N + 2][M + 2];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			String[] line = st.nextToken().split("");
			for (int j = 1; j <= M; j++) {
				arr[i][j] = Integer.parseInt(line[j - 1]);
			}
		}

		int total = 0;
		for (int h = 2; h <= 9; h++) {
			visited = new boolean[N + 2][M + 2];
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					if (!visited[i][j] && arr[i][j] < h) {
						total += bfs(i, j, h);
					}
				}
			}
		}
		System.out.println(total);
	}

	private static int bfs(int x, int y, int height) {
		Queue<int[]> q = new ArrayDeque<>();
		List<int[]> areas = new ArrayList<>();
		boolean isLeak = false;

		q.offer(new int[] {x, y});
		visited[x][y] = true;
		areas.add(new int[] {x, y});

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			for (int[] move : moves) {
				int nx = move[0] + curr[0];
				int ny = move[1] + curr[1];
				if (arr[nx][ny] == 0) {
					isLeak = true;
					continue;
				}
				if (!visited[nx][ny] && arr[nx][ny] < height) {
					visited[nx][ny] = true;
					q.offer(new int[] {nx, ny});
					areas.add(new int[] {nx, ny});
				}
			}
		}
		if (isLeak) return 0;
		int water = 0;
		for (int[] area : areas) {
			water += height - arr[area[0]][area[1]];
			arr[area[0]][area[1]] = height;
		}
		return water;
	}
}
