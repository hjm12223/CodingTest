package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj17086 {
	static int[][] arr;
	static int[][] moves = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}

		Queue<int[]> q = new ArrayDeque<>();
		int[][] dist = new int[N][M];
		for (int i = 0; i < N; i++)
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		int result = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 1) {
					q.offer(new int[] {i, j});
					dist[i][j] = 0;
				}
			}
		}
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			for (int[] move : moves) {
				int nx = move[0] + curr[0];
				int ny = move[1] + curr[1];
				if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
				int nxCost = dist[curr[0]][curr[1]] + 1;
				if (dist[nx][ny] > nxCost) {
					dist[nx][ny] = nxCost;
					result = Math.max(result, nxCost);
					q.offer(new int[] {nx, ny});
				}
			}
		}

		System.out.println(result);
	}

}