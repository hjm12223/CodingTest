package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj3184 {
	static int sheepCnt = 0;
	static int wolfCnt = 0;
	static int[][] move = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static boolean[][] visited;
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N][M];
		char[][] arr = new char[N][M];
		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j]) {
					visited[i][j] = true;
					bfs(arr, i, j);
				}
			}
		}
		System.out.println(sheepCnt + " " + wolfCnt);
	}

	private static void bfs(char[][] arr, int x, int y) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {x, y});
		int wolCnt = 0;
		int shCnt = 0;
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			for (int d = 0; d < 4; d++) {
				int nx = move[d][0] + curr[0];
				int ny = move[d][1] + curr[1];
				if (nx < 0 || ny < 0 || nx >= N || ny >= M || arr[nx][ny] == '#') continue;
				if (!visited[nx][ny]) {
					visited[nx][ny] = true;
					if (arr[nx][ny] == 'o') {
						shCnt++;
					} else if (arr[nx][ny] == 'v') {
						wolCnt++;
					}
					q.offer(new int[] {nx, ny});
				}
			}
		}
		if (shCnt > wolCnt) {
			sheepCnt += shCnt;
		} else {
			wolfCnt += wolCnt;
		}
	}
}
