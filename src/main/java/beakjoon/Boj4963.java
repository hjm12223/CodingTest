package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj4963 {
	static boolean[][] visited;
	static int[][] move = new int[][] {
		{0, 1}, {1, 0}, {-1, 0}, {0, -1},
		{-1, -1}, {1, 1}, {1, -1}, {-1, 1}}; // 좌상 , 우하, 좌하, 우상
	static int N, M;
	static int[][] arr;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			if (N == 0 && M == 0) break;
			arr = new int[N][M];
			visited = new boolean[N][M];
			result = 0;
			for (int i = 0; i < N; i++) {
				arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (arr[i][j] == 1 && !visited[i][j]) {
						bfs(i, j);
					}
				}
			}
			bw.write(String.valueOf(result));
			bw.newLine();
		}
		bw.flush();
		bw.close();

	}

	private static void bfs(int x, int y) {
		if (visited[x][y]) return;
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {x, y});
		visited[x][y] = true;
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			for (int d = 0; d < 8; d++) {
				int nx = curr[0] + move[d][0];
				int ny = curr[1] + move[d][1];
				if (nx < 0 || nx >= N || ny < 0 || ny >= M || arr[nx][ny] == 0) continue;
				if (!visited[nx][ny]) {
					visited[nx][ny] = true;
					q.offer(new int[] {nx, ny});
				}
			}
		}
		result++;
	}
}
