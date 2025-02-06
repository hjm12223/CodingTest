package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj16930 {
	static int N, M, K;
	static Node target;
	static int[][] move = new int[][] {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
	static char[][] arr;
	static int[][] dist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new char[N][M];
		dist = new int[N][M];
		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine().toCharArray();
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		st = new StringTokenizer(br.readLine());
		Queue<Node> q = new LinkedList<>();

		Node start = new Node(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 0);
		q.offer(start);
		dist[start.x][start.y] = 0;
		target = new Node(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 0);
		bfs(q);
		System.out.println(dist[target.x][target.y] == Integer.MAX_VALUE ? -1 : dist[target.x][target.y]);
	}

	private static void bfs(Queue<Node> q) {
		while (!q.isEmpty()) {
			Node curr = q.poll();
			if (curr.x == target.x && curr.y == target.y) break;
			for (int d = 0; d < 4; d++) {
				for (int k = 1; k <= K; k++) {
					int nx = curr.x + move[d][0] * k;
					int ny = curr.y + move[d][1] * k;
					if (!isValid(nx, ny)) break;
					if (dist[nx][ny] <= curr.dist) break;
					if (dist[nx][ny] > curr.dist + 1) {
						dist[nx][ny] = curr.dist + 1;
						q.offer(new Node(nx, ny, curr.dist + 1));
					}
				}
			}
		}
	}

	private static boolean isValid(int nx, int ny) {
		if (nx < 0 || ny < 0 || nx >= N || ny >= M || arr[nx][ny] == '#') return false;
		return true;
	}

	private static class Node {
		int x;
		int y;
		int dist;

		public Node(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
}