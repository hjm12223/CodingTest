package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2636 {
	static int[][] arr;
	static int N, M;
	static int[][] moves = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static boolean isDone = true;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int remain = 0;

		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 1) remain++;
			}
		}
		if (remain == 0) {
			System.out.println(0);
			System.out.println(0);
			return;
		}
		int count = 0;
		while (isDone) {
			visited = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (arr[i][j] == 0 && !visited[i][j]) {
						visited[i][j] = true;
						findHole(i, j);
					}
				}
			}
			visited = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (arr[i][j] != 0 && !visited[i][j]) {
						visited[i][j] = true;
						bfs(i, j);
					}
				}
			}
			int cheese = check();
			count++;
			if (cheese != 0) {
				remain = cheese;
			}
		}
		System.out.println(count);
		System.out.println(remain);
	}

	private static void findHole(int x, int y) {
		Queue<int[]> q = new ArrayDeque<>();
		Queue<int[]> clear = new ArrayDeque<>();

		q.offer(new int[] {x, y});
		clear.offer(new int[] {x, y});

		boolean isDonut = true;
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			for (int[] move : moves) {
				int nx = move[0] + curr[0];
				int ny = move[1] + curr[1];
				if (!isValid(nx, ny)) {
					isDonut = false;
					continue;
				}
				if (!visited[nx][ny] && arr[nx][ny] == 0) {
					visited[nx][ny] = true;
					q.offer(new int[] {nx, ny});
					clear.offer(new int[] {nx, ny});
				}
			}
		}
		while (!clear.isEmpty()) {
			int[] curr = clear.poll();
			if (isDonut)
				arr[curr[0]][curr[1]] = 2;
			else
				arr[curr[0]][curr[1]] = 0;
		}

	}

	private static int check() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 2) {
					arr[i][j] = 0;
				} else if (arr[i][j] != 0) {
					cnt++;
				}
			}
		}
		if (cnt != 0) {
			return cnt;
		} else {
			isDone = false;
			return 0;
		}
	}

	private static void bfs(int x, int y) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {x, y});
		Queue<int[]> clear = new ArrayDeque<>();
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			for (int[] move : moves) {
				int nx = move[0] + curr[0];
				int ny = move[1] + curr[1];
				if (!isValid(nx, ny)) continue;
				if (!visited[nx][ny] && arr[nx][ny] != 0) {
					visited[nx][ny] = true;
					q.offer(new int[] {nx, ny});
				}
				if (arr[nx][ny] == 0) {
					clear.offer(new int[] {curr[0], curr[1]});
				}
			}
		}
		while (!clear.isEmpty()) {
			int[] curr = clear.poll();
			arr[curr[0]][curr[1]] = 0;
		}
	}

	private static boolean isValid(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < M;
	}
}
