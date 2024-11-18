package beakjoon.DfsBfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1012 {
	static int[] dx = new int[] {0, 1, 0, -1};
	static int[] dy = new int[] {1, 0, -1, 0};
	static Queue<Node> q = new LinkedList<>();
	static int n;
	static int m;
	static boolean[][] isVisited;
	static int[][] cabbages;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken()); // 가로
			n = Integer.parseInt(st.nextToken()); // 세로
			int k = Integer.parseInt(st.nextToken()); // 심어져 있는 배추의 수

			cabbages = new int[n][m];
			isVisited = new boolean[n][m];
			result = 0;
			Queue<Node> q = new LinkedList<>();
			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				cabbages[y][x] = 1;
				q.offer(new Node(x, y));
			}
			for (int y = 0; y < n; y++) {
				for (int x = 0; x < m; x++) {
					if (isVisited[y][x]) continue;
					bfs(x, y);
				}
			}
			System.out.println(result);
		}
	}

	private static void bfs(int x, int y) {
		if (cabbages[y][x] == 0) return;
		q.offer(new Node(x, y));
		while (!q.isEmpty()) {
			Node curr = q.poll();
			if (isVisited[curr.y][curr.x]) continue;
			isVisited[curr.y][curr.x] = true;
			for (int i = 0; i < 4; i++) {
				int nextX = dx[i] + curr.x;
				int nextY = dy[i] + curr.y;
				if (nextX >= m || nextY >= n || nextX < 0 || nextY < 0) continue;
				if (cabbages[nextY][nextX] == 1) {
					q.offer(new Node(nextX, nextY));
				}
			}
		}
		result++;
	}

	/*

 [1, 1, 0, 0, 0, 0, 0, 0, 0, 0],
 [0, 1, 0, 0, 0, 0, 0, 0, 0, 0],
 [0, 0, 0, 0, 1, 0, 0, 0, 0, 0],
 [0, 0, 0, 0, 1, 0, 0, 0, 0, 0],
 [0, 0, 1, 1, 0, 0, 0, 1, 1, 1],
 [0, 0, 0, 0, 1, 0, 0, 1, 1, 1],
 [0, 0, 0, 0, 0, 0, 0, 1, 1, 1],
 [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]]
	 */
	private static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
