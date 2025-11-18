package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2146_2 {
	static int N;
	static int[][] arr;
	static boolean[][] visited;
	static int[][] moves = new int[][] {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int markValue = 2;
		int result = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] != 0 && !visited[i][j])
					mark(i, j, markValue++);
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] != 0)
					result = Math.min(run(i, j, arr[i][j]), result);
			}
		}
		System.out.println(result - 1);
	}

	private static int run(int x, int y, int startValue) {
		visited = new boolean[N][N];
		visited[x][y] = true;
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {x, y, 0});
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			if (arr[curr[0]][curr[1]] != 0 && arr[curr[0]][curr[1]] != startValue)
				return curr[2];
			for (int[] move : moves) {
				int nx = curr[0] + move[0];
				int ny = curr[1] + move[1];
				if (nx < 0 || ny < 0 || nx >= N || ny >= N || arr[nx][ny] == startValue) continue;
				if (!visited[nx][ny]) {
					visited[nx][ny] = true;
					q.offer(new int[] {nx, ny, curr[2] + 1});
				}
			}
		}
		return Integer.MAX_VALUE;
	}

	private static void mark(int x, int y, int markValue) {
		visited[x][y] = true;
		arr[x][y] = markValue;
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {x, y});
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			for (int[] move : moves) {
				int nx = curr[0] + move[0];
				int ny = curr[1] + move[1];
				if (nx < 0 || ny < 0 || nx >= N || ny >= N || arr[nx][ny] == 0) continue;
				if (!visited[nx][ny]) {
					visited[nx][ny] = true;
					arr[nx][ny] = markValue;
					q.offer(new int[] {nx, ny});
				}
			}
		}
	}
}
