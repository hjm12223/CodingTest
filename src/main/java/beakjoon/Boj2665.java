package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2665 {
	static int N;
	static int[][] arr;
	static int[][] moves = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static boolean[][][] visited;
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		visited = new boolean[N + 1][N][N];//[벽을 부셨는가][next x][next y]

		for (int i = 0; i < N; i++) {
			arr[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
		}
		bfs();
		System.out.println(result);
	}

	private static void bfs() {
		Queue<int[]> q = new ArrayDeque<>();

		q.offer(new int[] {0, 0, 0});

		visited[0][0][0] = true;

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			if (curr[2] > N) continue;
			if (curr[0] == N - 1 && curr[1] == N - 1) {
				result = Math.min(result, curr[2]);
				continue;
			}
			for (int[] move : moves) {
				int nx = curr[0] + move[0];
				int ny = curr[1] + move[1];
				if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
				if (!visited[curr[2]][nx][ny]) {
					visited[curr[2]][nx][ny] = true;
					if (arr[nx][ny] == 0) {
						q.offer(new int[] {nx, ny, curr[2] + 1});
					} else {
						q.offer(new int[] {nx, ny, curr[2]});
					}
				}
			}
		}
	}
}
