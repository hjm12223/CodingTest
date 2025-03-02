package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj18405 {
	static int N;
	static int[][] move = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static boolean[][] visited;
	static int[][] arr;
	static int s;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		visited = new boolean[N][N];
		Queue<int[]> q = new ArrayDeque<>();
		Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
		for (int i = 0; i < N; i++) {
			int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			for (int j = 0; j < N; j++) {
				arr[i] = line;
				if (line[j] != 0) {
					pq.offer(new int[] {i, j, line[j], 0});
					visited[i][j] = true;
				}
			}
		}
		while (!pq.isEmpty()) {
			q.offer(pq.poll());
		}
		st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		bfs(q);
		System.out.println(arr[x - 1][y - 1]);
	}

	private static void bfs(Queue<int[]> q) {
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			if (curr[3] >= s) continue;
			for (int d = 0; d < 4; d++) {
				int nx = move[d][0] + curr[0];
				int ny = move[d][1] + curr[1];
				if (nx < 0 || ny < 0 || nx >= N || ny >= N || arr[nx][ny] != 0) continue;
				if (!visited[nx][ny]) {
					visited[nx][ny] = true;
					arr[nx][ny] = curr[2];
					q.offer(new int[] {nx, ny, curr[2], curr[3] + 1});
				}
			}
		}
	}
}
