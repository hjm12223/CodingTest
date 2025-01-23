package beakjoon.DfsBfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Boj2146 {
	static int[][] arr;
	static int[][] move = new int[][] {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	static boolean[][] visited;
	static int N;
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		int cnt = 2;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == 1) {
					bfs(i, j, cnt);
					cnt++;
				}
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] > 1) {
					visited = new boolean[N][N];
					solve(i, j, arr[i][j]);
				}
			}
		}
		System.out.println(result);
	}

	private static void solve(int x, int y, int value) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {x, y, 0});
		visited[x][y] = true;
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			for (int d = 0; d < 4; d++) {
				int nx = move[d][0] + curr[0];
				int ny = move[d][1] + curr[1];
				if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
				if (!visited[nx][ny]) {
					visited[nx][ny] = true;
					if (arr[nx][ny] == 0) {
						q.offer(new int[] {nx, ny, curr[2] + 1});
					} else if (arr[nx][ny] != value) {
						result = Math.min(result, curr[2]);
						return;
					}
				}
			}
		}
	}

	private static void bfs(int x, int y, int cnt) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {x, y});
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			for (int d = 0; d < 4; d++) {
				int nx = move[d][0] + curr[0];
				int ny = move[d][1] + curr[1];
				if (nx < 0 || ny < 0 || nx >= N || ny >= N || arr[nx][ny] == 0) continue;
				if (!visited[nx][ny]) {
					visited[nx][ny] = true;
					arr[nx][ny] = cnt;
					q.offer(new int[] {nx, ny});
				}
			}
		}
	}
}
/*
다리를 만들어야한다
그러면 해당 가장자리에서 시작하는것이 가장 알맞음

가장자리에 도착했을 떄 bfs 를 시작 다리를 만들어줌
 */