package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj16498 {
	static int[][] moves = new int[][] {{-2, -1}, {-2, 1}, {0, -2}, {0, 2}, {2, -1}, {2, 1}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());

		boolean[][] visited = new boolean[N][N];
		Queue<int[]> q = new ArrayDeque<>();

		st = new StringTokenizer(br.readLine());

		int sx = Integer.parseInt(st.nextToken());
		int sy = Integer.parseInt(st.nextToken());
		visited[sx][sy] = true;
		q.offer(new int[] {sx, sy, 0});

		int gx = Integer.parseInt(st.nextToken());
		int gy = Integer.parseInt(st.nextToken());
		
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			if (curr[0] == gx && curr[1] == gy) {
				System.out.println(curr[2]);
				return;
			}
			for (int[] move : moves) {
				int nx = curr[0] + move[0];
				int ny = curr[1] + move[1];
				if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
				if (!visited[nx][ny]) {
					q.offer(new int[] {nx, ny, curr[2] + 1});
					visited[nx][ny] = true;
				}
			}
		}
		System.out.println(-1);
	}
}
