package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj7569 {
	static int N, M, H;
	static int[] dx = new int[] {0, 1, 0, -1, 0, 0};
	static int[] dy = new int[] {1, 0, -1, 0, 0, 0};
	static int[] dz = new int[] {0, 0, 0, 0, 1, -1};

	static int[][][] dist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		dist = new int[H][N][M];
		for (int h = 0; h < H; h++) {
			for (int n = 0; n < N; n++) {
				for (int m = 0; m < M; m++) {
					dist[h][n][m] = -1;
				}
			}
		}

		Queue<Node> q = new LinkedList<>();
		for (int h = 0; h < H; h++) {
			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				for (int m = 0; m < M; m++) {
					int tomato = Integer.parseInt(st.nextToken());
					if (tomato == 1) {
						q.offer(new Node(h, n, m));
						dist[h][n][m] = 0;
					} else if (tomato == -1) {
						dist[h][n][m] = -1; // 익지 않을 위치
					}
				}
			}
		}

		bfs(q);
		int result = 0;
		for (int h = 0; h < H; h++) {
			for (int n = 0; n < N; n++) {
				for (int m = 0; m < M; m++) {
					if (dist[h][n][m] == 0) {
						System.out.println(-1); // 익지 못한 토마토 존재
						return;
					}
					result = Math.max(result, dist[h][n][m]);
				}
			}
		}

		System.out.println(result);
	}

	private static void bfs(Queue<Node> q) {
		while (!q.isEmpty()) {
			Node curr = q.poll();
			for (int i = 0; i < 6; i++) {
				int nh = curr.h + dz[i];
				int nx = curr.x + dx[i];
				int ny = curr.y + dy[i];

				if (nh < 0 || nh >= H || nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				if (dist[nh][nx][ny] >= 0) continue;  // 이미 방문했거나 벽인 경우

				dist[nh][nx][ny] = dist[curr.h][curr.x][curr.y] + 1;
				q.offer(new Node(nh, nx, ny));
			}
		}
	}

	public static class Node {
		int h;
		int x;
		int y;

		public Node(int h, int x, int y) {
			this.h = h;
			this.x = x;
			this.y = y;
		}
	}
}
