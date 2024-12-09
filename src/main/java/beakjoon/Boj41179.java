package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj41179 {
	static final int INF = 987654321;
	static int N, M;
	static char[][] arr;
	static int[] dx = new int[] {1, 0, 0, -1};
	static int[] dy = new int[] {0, 1, -1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new char[N][M];
		Queue<int[]> q = new LinkedList<>();
		Queue<int[]> fire = new LinkedList<>();
		int[][] jiDist = new int[N][M];
		int[][] fireDist = new int[N][M];

		boolean[][] jiVisited = new boolean[N][M];
		boolean[][] fireVisited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			Arrays.fill(jiDist[i], INF);
			Arrays.fill(fireDist[i], INF);
		}
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				char item = str.charAt(j);
				if (item == 'J') {
					q.offer(new int[] {i, j});
					jiDist[i][j] = 0;
					jiVisited[i][j] = true;
				} else if (item == 'F') {
					fire.offer(new int[] {i, j});
					fireDist[i][j] = 0;
					fireVisited[i][j] = true;
				}
				arr[i][j] = item;
			}
		}
		fireBfs(fire, fireVisited, fireDist);
		bfs(q, jiDist, jiVisited, fireDist);

	}

	private static void fireBfs(Queue<int[]> q, boolean[][] isVisited, int[][] dist) {
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int x = curr[0];
			int y = curr[1];
			for (int d = 0; d < 4; d++) {
				int nx = dx[d] + x;
				int ny = dy[d] + y;
				if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
				if (arr[nx][ny] == '#') continue;
				if (!isVisited[nx][ny] && dist[nx][ny] > dist[x][y] + 1) {
					isVisited[nx][ny] = true;
					dist[nx][ny] = dist[x][y] + 1;
					q.offer(new int[] {nx, ny});
				}
			}
		}
	}

	private static void bfs(Queue<int[]> q, int[][] dist, boolean[][] isVisited, int[][] fireDist) {
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int x = curr[0];
			int y = curr[1];
			for (int d = 0; d < 4; d++) {
				int nx = dx[d] + x;
				int ny = dy[d] + y;
				if (fireDist[x][y] <= dist[x][y]) continue;
				if (nx < 0 || ny < 0 || nx >= N || ny >= M) { // 탈출 성공
					System.out.println(dist[x][y] + 1);
					return;
				}
				if (arr[nx][ny] == '#') continue;
				if (!isVisited[nx][ny]) {
					isVisited[nx][ny] = true;
					dist[nx][ny] = dist[x][y] + 1;
					q.offer(new int[] {nx, ny});
				}
			}
		}
		System.out.println("IMPOSSIBLE");
	}
}
