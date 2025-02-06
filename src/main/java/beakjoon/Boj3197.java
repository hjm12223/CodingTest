package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj3197 {
	static int N, M;
	static char[][] arr;
	static int maxDist = 0;
	static int[][] move = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new char[N][M];
		int[][] dist = new int[N][M];
		List<Duck> list = new ArrayList<>();
		Queue<Node> q = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			char[] line = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				arr[i][j] = line[j];
				if (line[j] == '.' || line[j] == 'L') q.offer(new Node(i, j));
				else if (line[j] == 'X') dist[i][j] = Integer.MAX_VALUE;
				if (line[j] == 'L') list.add(new Duck(i, j, 0));
			}
		}

		bfs1(q, dist);
		System.out.println(bfs(list.get(0), list.get(1), dist));
	}

	private static void bfs1(Queue<Node> q, int[][] dist) {
		while (!q.isEmpty()) {
			Node curr = q.poll();
			for (int d = 0; d < 4; d++) {
				int nx = move[d][0] + curr.x;
				int ny = move[d][1] + curr.y;
				if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
				if (arr[nx][ny] == '.') continue;
				int nextDist = dist[curr.x][curr.y];
				if (arr[nx][ny] == 'X') {
					nextDist++;
				}
				if (dist[nx][ny] > nextDist) {
					dist[nx][ny] = nextDist;
					q.offer(new Node(nx, ny));
				}
			}
		}
	}

	private static int bfs(Duck start, Duck target, int[][] dist) {
		int result = Integer.MAX_VALUE;
		Queue<Duck> q = new PriorityQueue<>((o1, o2) -> o1.maxValue - o2.maxValue);
		boolean[][] visited = new boolean[N][M];
		visited[start.x][start.y] = true;
		q.offer(start);
		while (!q.isEmpty()) {
			Duck curr = q.poll();
			if (curr.x == target.x && curr.y == target.y) {
				return curr.maxValue;
			}
			for (int d = 0; d < 4; d++) {
				int nx = move[d][0] + curr.x;
				int ny = move[d][1] + curr.y;
				if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
				if (!visited[nx][ny]) {
					visited[nx][ny] = true;
					int value = Math.max(curr.maxValue, dist[nx][ny]);
					q.offer(new Duck(nx, ny, value));
				}
			}
		}
		return result;
	}

	private static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private static class Duck {
		int x;
		int y;
		int maxValue;

		public Duck(int x, int y, int maxValue) {
			this.x = x;
			this.y = y;
			this.maxValue = maxValue;
		}

	}
}
