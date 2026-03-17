package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2194 {
	static int N, M, A, B, K;
	static int[][] moves = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static int[][] arr;
	static boolean[][] visited;
	static Queue<int[]> q = new ArrayDeque<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		visited = new boolean[N][M];

		arr = new int[N][M];

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			arr[x][y] = 1;
		}
		st = new StringTokenizer(br.readLine());
		int startX = Integer.parseInt(st.nextToken()) - 1;
		int startY = Integer.parseInt(st.nextToken()) - 1;
		q.offer(new int[] {startX, startY, 0});
		visited[startX][startY] = true;

		st = new StringTokenizer(br.readLine());
		int endX = Integer.parseInt(st.nextToken()) - 1;
		int endY = Integer.parseInt(st.nextToken()) - 1;
		System.out.println(bfs(endX, endY));
	}

	private static int bfs(int endX, int endY) {
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			if (curr[0] == endX && curr[1] == endY) {
				return curr[2];
			}
			for (int[] move : moves) {
				int nx = move[0] + curr[0];
				int ny = move[1] + curr[1];
				boolean isOkay = true;
				for (int i = nx; i < nx + A; i++) {
					for (int j = ny; j < ny + B; j++) {
						if (!isValid(i, j)) {
							isOkay = false;
							break;
						}
						if (!isOkay) break;
					}
				}
				if (isOkay) {
					if (!visited[nx][ny]) {
						q.offer(new int[] {nx, ny, curr[2] + 1});
						visited[nx][ny] = true;
					}
				}
			}
		}
		return -1;
	}

	static boolean isValid(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < M && arr[x][y] != 1;
	}
}
