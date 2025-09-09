package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Boj2665_2 {
	static int N;
	static int[][] arr;

	static int[][] dist;
	static Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
	static int[][] moves = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		dist = new int[N][N];
		for (int i = 0; i < N; i++) {
			arr[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
			Arrays.fill(dist[i], (1 << 30));
		}
		pq.offer(new int[] {0, 0, 0});
		dist[0][0] = 0;
		dijkstra();
		System.out.println(dist[N - 1][N - 1]);
	}

	private static void dijkstra() {
		while (!pq.isEmpty()) {
			int[] curr = pq.poll();
			int x = curr[0];
			int y = curr[1];
			int cost = curr[2];
			for (int[] move : moves) {
				int nx = x + move[0];
				int ny = y + move[1];
				if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
				int nextCost = (arr[nx][ny] == 0) ? cost + 1 : cost;
				if (dist[nx][ny] > nextCost) {
					dist[nx][ny] = nextCost;
					pq.offer(new int[] {nx, ny, nextCost});
				}
			}
		}
	}
}
