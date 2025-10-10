package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj17472_2 {
	static int[][] moves = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

	static int[][] arr;
	static int[] parent;

	static boolean[][] visited;
	static int N, M;
	static List<Node> graph = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int mask = 2;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] != 0 && !visited[i][j]) {
					visited[i][j] = true;
					bfs(i, j, mask++);
				}
			}
		}
		int cntOfIsland = mask - 2;
		parent = new int[cntOfIsland];
		for (int i = 0; i < cntOfIsland; i++) {
			parent[i] = i;
		}
		buildEdges();
		Collections.sort(graph, (o1, o2) -> o1.cost - o2.cost);
		int totalCnt = 0;
		int totalCost = 0;
		for (Node node : graph) {
			if (find(node.from) != find(node.to)) {
				union(node.from, node.to);
				totalCost += node.cost;
				totalCnt++;
			}
		}
		System.out.println(totalCnt == cntOfIsland - 1 ? totalCost : -1);
	}

	private static void union(int from, int to) {
		int x = find(from);
		int y = find(to);
		if (x != y) {
			parent[x] = y;
		}
	}

	private static void bfs(int x, int y, int mask) {
		arr[x][y] = mask;
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {x, y});
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			for (int[] move : moves) {
				int nx = move[0] + curr[0];
				int ny = move[1] + curr[1];
				if (nx < 0 || ny < 0 || nx >= N || ny >= M || arr[nx][ny] == 0) continue;
				if (!visited[nx][ny]) {
					visited[nx][ny] = true;
					arr[nx][ny] = mask;
					q.offer(new int[] {nx, ny});
				}
			}
		}
	}

	public static int find(int x) {
		if (parent[x] != x) {
			parent[x] = find(parent[x]);
		}
		return parent[x];
	}

	static void buildEdges() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] > 1) {
					int from = arr[i][j] - 2;
					for (int[] move : moves) {
						int nx = i;
						int ny = j;
						int length = 0;
						while (true) {
							nx += move[0];
							ny += move[1];
							if (nx < 0 || ny < 0 || nx >= N || ny >= M) break;
							if (arr[nx][ny] == arr[i][j]) break;
							if (arr[nx][ny] > 1) {
								if (length >= 2) {
									graph.add(new Node(from, arr[nx][ny] - 2, length));
								}
								break;
							}
							if (arr[nx][ny] == 0) length++;
						}
					}
				}
			}
		}
	}

	private static class Node {
		int from;
		int to;
		int cost;

		public Node(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}
}
