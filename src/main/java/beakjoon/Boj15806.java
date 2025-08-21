package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj15806 {
	static int[][] moves = new int[][] {{2, 1}, {-2, 1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {2, -1}, {-2, -1}};
	static int N, M, K, T;
	static boolean[][][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 방의 크기 N*N
		M = Integer.parseInt(st.nextToken()); // 곰팡이 갯수
		K = Integer.parseInt(st.nextToken()); // 청소 검사 좌표 갯수
		T = Integer.parseInt(st.nextToken()); // 청소 남은 일수
		visited = new boolean[2][N][N];
		Queue<int[]> q1 = new ArrayDeque<>(); // 짝수 일에 곰팡이가 있는 위치
		Queue<int[]> q2 = new ArrayDeque<>();  // 홀수 일에 곰팡이가 있는 위치

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			q1.offer(new int[] {x, y});
			visited[0][x][y] = true;
		}

		bfs();
		int t = T % 2;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			if (visited[t][x][y]) {
				System.out.println("YES");
				return;
			}
		}
		System.out.println("NO");
	}

	private static void bfs() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visited[0][i][j]) {
					for (int[] move : moves) {
						int ni = i + move[0];
						int nj = j + move[1];
						if (ni >= 0 && nj >= 0 && ni < N && nj < N) {
							visited[1][ni][nj] = true;
						}
					}
				}
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visited[1][i][j]) {
					for (int[] move : moves) {
						int ni = i + move[0];
						int nj = j + move[1];
						if (ni >= 0 && nj >= 0 && ni < N && nj < N) {
							visited[0][ni][nj] = true;
						}
					}
				}
			}
		}
	}
}
