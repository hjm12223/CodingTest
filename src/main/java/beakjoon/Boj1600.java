package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1600 {
	static int[] dx = new int[] {0, 1, -1, 0};
	static int[] dy = new int[] {1, 0, 0, -1};
	static int[] horseX = new int[] {-2, -1, 1, 2, 2, 1, -1, -2};
	static int[] horseY = new int[] {1, 2, 2, 1, -1, -2, -2, -1};
	static int K, N, M;
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine()); // 원숭이가 말처럼 행돌할 수 있는 수
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
		}
		System.out.println(bfs());
	}

	private static int bfs() {
		Queue<State> q = new LinkedList<>();
		q.offer(new State(0, 0, K, 0));
		boolean[][][] isVisited = new boolean[N][M][K + 1];
		isVisited[0][0][K] = true;
		while (!q.isEmpty()) {
			State curr = q.poll();
			if (curr.x == N - 1 && curr.y == M - 1) return curr.dist;
			int x = curr.x;
			int y = curr.y;
			if (curr.k > 0) {
				for (int h = 0; h < 8; h++) {
					int nextHorseX = x + horseX[h];
					int nextHorseY = y + horseY[h];
					if (!isValid(nextHorseX, nextHorseY)) continue;
					if (!isVisited[nextHorseX][nextHorseY][curr.k - 1]) {
						isVisited[nextHorseX][nextHorseY][curr.k - 1] = true;
						q.offer(new State(nextHorseX, nextHorseY, curr.k - 1, curr.dist + 1));
					}
				}
			}
			for (int d = 0; d < 4; d++) {
				int nextX = dx[d] + x;
				int nextY = dy[d] + y;
				if (!isValid(nextX, nextY)) continue;
				if (!isVisited[nextX][nextY][curr.k]) {
					isVisited[nextX][nextY][curr.k] = true;
					q.offer(new State(nextX, nextY, curr.k, curr.dist + 1));
				}
			}
		}
		return -1;
	}

	private static boolean isValid(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= M || arr[x][y] == 1) return false;
		return true;
	}

	private static class State {
		int x;
		int y;
		int k;
		int dist;

		public State(int x, int y, int k, int dist) {
			this.x = x;
			this.y = y;
			this.k = k;
			this.dist = dist;
		}
	}
}
