package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj11967 {
	static List<Node>[][] graph;
	static int N;
	static int[][] move = new int[][] {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		graph = new List[N + 1][N + 1];

		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= N; j++) {
				graph[i][j] = new ArrayList<>();
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[x][y].add(new Node(a, b));
		}
		System.out.println(bfs());
	}

	private static int bfs() {
		int result = 1;
		boolean[][] visited = new boolean[N + 1][N + 1];
		boolean[][] light = new boolean[N + 1][N + 1];
		visited[1][1] = true;
		light[1][1] = true;

		Queue<Node> q = new ArrayDeque<>();
		q.offer(new Node(1, 1));

		while (!q.isEmpty()) {
			Node curr = q.poll();
			for (Node nextNode : graph[curr.x][curr.y]) {
				if (!light[nextNode.x][nextNode.y]) {
					light[nextNode.x][nextNode.y] = true;
					result++;
					if (isValid(nextNode.x, nextNode.y) && canVisit(nextNode.x, nextNode.y, visited)) {
						q.offer(new Node(nextNode.x, nextNode.y));
						visited[nextNode.x][nextNode.y] = true;
					}
				}
			}
			for (int d = 0; d < 4; d++) {
				int nx = move[d][0] + curr.x;
				int ny = move[d][1] + curr.y;
				if (isValid(nx, ny) && !visited[nx][ny] && light[nx][ny]) {
					q.offer(new Node(nx, ny));
					visited[nx][ny] = true;
				}
			}
		}
		return result;
	}

	private static boolean canVisit(int x, int y, boolean[][] visited) {
		for (int d = 0; d < 4; d++) {
			int nx = move[d][0] + x;
			int ny = move[d][1] + y;
			if (isValid(nx, ny) && visited[nx][ny]) {
				return true;
			}
		}
		return false;
	}

	private static boolean isValid(int x, int y) {
		return x >= 0 && y >= 0 && x <= N && y <= N;
	}

	private static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}