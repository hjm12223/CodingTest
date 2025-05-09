package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Boj3055 {
	static int[][] moves = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static int N, M;
	static boolean[][] visited;
	static char[][] arr;
	static int result = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new char[N][M];
		visited = new boolean[N][M];
		int sx = 0;
		int sy = 0;
		ArrayDeque<Node> q = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				char c = line.charAt(j);
				arr[i][j] = c;
				if (c == '*') {
					q.offer(new Node(i, j, c, 0));
					visited[i][j] = true;
				} else if (c == 'S') {
					sx = i;
					sy = j;
					visited[i][j] = true;
				}
			}
		}
		q.addLast(new Node(sx, sy, 'S', 0));
		bfs(q);
		System.out.println(result == Integer.MIN_VALUE ? "KAKTUS" : result);
	}

	private static void bfs(ArrayDeque<Node> q) {
		while (!q.isEmpty()) {
			Node curr = q.poll();
			if (curr.name == 'S' && arr[curr.x][curr.y] == 'D') {
				result = Math.max(curr.dist, result);
			}
			for (int[] move : moves) {
				int nx = curr.x + move[0];
				int ny = curr.y + move[1];
				if (nx < 0 || ny < 0 || nx >= N || ny >= M || arr[nx][ny] == 'X') continue;
				if (curr.name == '*' && arr[nx][ny] == 'D') continue;
				if (!visited[nx][ny]) {
					visited[nx][ny] = true;
					q.offer(new Node(nx, ny, curr.name, curr.dist + 1));
				}
			}
		}

	}

	private static class Node {
		int x;
		int y;
		char name;
		int dist;

		public Node(int x, int y, char name, int dist) {
			this.x = x;
			this.y = y;
			this.name = name;
			this.dist = dist;
		}
	}
}
