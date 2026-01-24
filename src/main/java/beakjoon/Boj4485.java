package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Boj4485 {
	static int[][] arr;
	static int[][] moves = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static int T;
	static int INF = (1 << 30);
	static int count = 1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		while (T != 0) {
			arr = new int[T][T];
			for (int i = 0; i < T; i++) {
				arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			dijkstra(0, 0);
			T = Integer.parseInt(br.readLine());
			count++;
		}
	}

	private static void dijkstra(int x, int y) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {x, y});
		int[][] dist = new int[T][T];
		for (int i = 0; i < T; i++) {
			Arrays.fill(dist[i], INF);
		}
		dist[x][y] = arr[x][y];

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			for (int[] move : moves) {
				int nx = move[0] + curr[0];
				int ny = move[1] + curr[1];
				if (nx < 0 || ny < 0 || nx >= T || ny >= T) continue;
				int nextCost = dist[curr[0]][curr[1]] + arr[nx][ny];
				if (dist[nx][ny] > nextCost) {
					dist[nx][ny] = nextCost;
					q.offer(new int[] {nx, ny});
				}
			}
		}
		System.out.println("Problem " + count + ": " + dist[T - 1][T - 1]);
	}
}
